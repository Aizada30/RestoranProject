package global.service.impl;

import global.dto.request.StopListRequest;
import global.dto.response.SimpleResponse;
import global.dto.response.StopListPagination;
import global.dto.response.StopListResponse;
import global.entity.Menu;
import global.entity.StopList;
import global.exceptionGlobal.BadRequest;
import global.exceptionGlobal.NotFoundException;
import global.repo.MenuRepo;
import global.repo.RestaurantRepo;
import global.repo.StopListRepo;
import global.repo.dao.StopListJDBCTemplate;
import global.service.StopListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StopListServiceImpl implements StopListService {

    private final StopListRepo stopListRepo;
    private final StopListJDBCTemplate stopListJDBCTemplate;
    private final MenuRepo menuRepo;
    private final RestaurantRepo restaurantRepo;

    @Override
    public SimpleResponse saveStopList(Long menuId, StopListRequest stopListRequest) {
        Menu menu = menuRepo.findById(menuId).orElseThrow(
                () -> new NotFoundException("Not found")
        );
        if (stopListRepo.existsStopListByDateAndMenuId(stopListRequest.date(), menuId)) {
            throw new BadRequest("A stop list already exists for this menu and date.");
        }
        StopList stopList = StopList
                .builder()
                .date(stopListRequest.date())
                .menu(menu)
                .reason(stopListRequest.reason())
                .build();
        stopListRepo.save(stopList);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("StopList with id: %s successfully saved", stopList.getId())
        );


    }

    @Override
    public StopListResponse getStopListById(Long stopListId) {
        if (!stopListRepo.existsById(stopListId)) {
            log.error(String.format("Stop List with id: %s is exists", stopListId));
            throw new NotFoundException(String.format("Stop List with id: %s is exists", stopListId));
        }
        return stopListJDBCTemplate.getStopListById(stopListId);
    }

    @Override
    public List<StopListResponse> getAllStopLists(Long restId) {
        if (!restaurantRepo.existsById(restId)) {
            log.error(String.format("Restaurant with id: %s not found", restId));
            throw new NotFoundException(String.format("Restaurant with id: %s not found", restId));
        }
        log.info("Successfully gedet all stopLists with restaurant id");
        return stopListJDBCTemplate.getAllStopLists(restId);
    }

    @Override
    public SimpleResponse deleteStopList(Long stopListId) {
        if (!stopListRepo.existsById(stopListId)) {
            log.error(String.format("StopList with id: %s not found", stopListId));
            throw new NotFoundException(String.format("StopList with id: %s not found", stopListId));
        }
        stopListRepo.deleteById(stopListId);
        log.info(String.format("StopList with id: %s successfully deleted", stopListId));
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("StopList with id: %s successfully deleted", stopListId)
        );
    }

    @Override
    public SimpleResponse updateStopList(Long stopListId, StopListRequest stopListRequest) {
        StopList stopList = stopListRepo.findById(stopListId).orElseThrow(
                () -> {
                    log.error(String.format("StopList with id: %s not found", stopListId));
                    return new NotFoundException(String.format("StopList with id: %s not found", stopListId));
                }
        );
        stopList.setDate(stopListRequest.date());
        stopList.setReason(stopListRequest.reason());
        stopListRepo.save(stopList);
        log.info("Successfully updated");
        return new SimpleResponse(
                HttpStatus.OK,
                "Successfully update"
        );
    }

    @Override
    public StopListPagination getAll(int page, int pageSize) {
        return stopListJDBCTemplate.getAll(page, pageSize);
    }
}