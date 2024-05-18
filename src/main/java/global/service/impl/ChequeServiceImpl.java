package global.service.impl;


import global.dto.request.ChequeRequest;
import global.dto.response.ChequeResponse;
import global.dto.response.ChequeResponsePagination;
import global.dto.response.SimpleResponse;
import global.entity.Cheque;
import global.entity.Menu;
import global.entity.User;
import global.exceptionGlobal.BadRequest;
import global.exceptionGlobal.NotFoundException;
import global.repo.ChequeRepo;
import global.repo.MenuRepo;
import global.repo.UserRepo;
import global.repo.dao.ChequesJDBCTemplate;
import global.repo.dao.MenuJDBCTemplate;
import global.service.ChequeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChequeServiceImpl implements ChequeService {

    private final ChequeRepo chequeRepo;
    private final ChequesJDBCTemplate chequesJDBCTemplate;
    private final UserRepo userRepo;
    private final MenuRepo menuRepo;

    @Override
    public ChequeResponsePagination findAll(int currentPage, int pageSize) {
        return chequesJDBCTemplate.getAllCheques(currentPage, pageSize);
    }

    @Override
    public SimpleResponse save(ChequeRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepo.findByEmail(email).orElseThrow(() -> {
            log.error("User with this email: %s not found".formatted(email));
            return new NotFoundException("User with this email: %s not found".formatted(email));
        });

        if (user.getRestaurant() == null) {
            throw new BadRequest("This user is not working in this restaurant");
        }

        Cheque cheque = new Cheque();
        cheque.setCreatedAt(LocalDate.now());
        cheque.setUser(user);
        List<Menu> menuItems = new ArrayList<>();
        for (Long menuItemId : request.menuiesIdList()) {
            Menu menu = menuRepo.findById(menuItemId).orElseThrow(() -> new NotFoundException("Not found"));
            if (menu.getStopList() != null) {
                throw new BadRequest("This product is already in stopList");
            }
            menuItems.add(menu);
        }
        cheque.setMenuList(menuItems);
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Menu m : menuItems) {
            totalPrice = totalPrice.add(m.getPrice());
        }
        cheque.setPriceAvg(totalPrice);
        chequeRepo.save(cheque);
        log.info("Cheque is successfully saved");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Cheque successfully saved")
                .build();
    }

    @Override
    public ChequeResponse findById(Long id) {
        return chequesJDBCTemplate.getById(id);
    }

    @Override
    public SimpleResponse update(Long chequeId, ChequeRequest request) {
        Cheque cheque = chequeRepo.findById(chequeId).orElseThrow(
                () -> {
                    log.error(String.format("Cheque with id: %s not found", chequeId));
                    return new NotFoundException(String.format("Cheque with id: %s not found", chequeId));
                }
        );
        List<Menu> menuList = new ArrayList<>();
        for (Long menuId : request.menuiesIdList()) {
            Menu menu = menuRepo.findById(menuId).orElseThrow(() -> new NotFoundException("Not found"));
            if (menu.getStopList() != null) {
                throw new BadRequest("This product is already in stopList");
            }
            menuList.add(menu);
        }
        cheque.setMenuList(menuList);
        chequeRepo.save(cheque);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Cheque with id: %s successfully updated", chequeId)
        );
    }

    @Override
    public SimpleResponse delete(Long id) {
        chequeRepo.deleteChequeById(id).orElseThrow(() -> {
            log.error(String.format("Cheque with id: %s not found", id));
            return new NotFoundException(String.format("Cheque with id: %s not found", id));
        });
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Cheque with id: %s is successfully deleted",id)
        );
    }

    @Override
    public BigDecimal waitersPrice(Long userId) {
        return chequeRepo.getTopByCreatedAt(userId);
    }

    @Override
    public BigDecimal dayPrice(Long restaurantId) {
        return chequeRepo.avg(restaurantId);
    }
}