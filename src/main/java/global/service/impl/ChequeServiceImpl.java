package global.service.impl;


import global.dto.request.ChequeRequest;
import global.dto.response.ChequeResponse;
import global.dto.response.MenuResponse;
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

/**
 * Abdyrazakova Aizada
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChequeServiceImpl implements ChequeService {

    private final ChequeRepo chequeRepo;
    private final ChequesJDBCTemplate chequesJDBCTemplate;
    private final UserRepo userRepo;
    private final MenuRepo menuRepo;
    private final MenuJDBCTemplate menuJDBCTemplate;

    @Override
    public List<ChequeResponse> findAll() {
        return chequesJDBCTemplate.getAllCheques();
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
            if(menu.getStopList()!=null){
                throw new BadRequest("This product is already in stopList");
            }
            menuItems.add(menu);
        }
        cheque.setMenuList(menuItems);
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Menu m:menuItems){
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
        return null;
    }

    @Override
    public SimpleResponse update(Long id, ChequeRequest request) {
        return null;
    }

    @Override
    public SimpleResponse delete(Long id) {
        return null;
    }

    @Override
    public SimpleResponse totalSum(Long id, LocalDate date) {
        return null;
    }

    @Override
    public SimpleResponse avg(LocalDate date) {
        return null;
    }
}

