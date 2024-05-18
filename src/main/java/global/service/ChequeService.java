package global.service;

import global.dto.request.ChequeRequest;
import global.dto.response.ChequeResponse;
import global.dto.response.ChequeResponsePagination;
import global.dto.response.SimpleResponse;
import java.math.BigDecimal;

public interface ChequeService {
    ChequeResponsePagination findAll(int currentPage, int pageSize);

    SimpleResponse save(ChequeRequest request);

    ChequeResponse findById(Long id);

    SimpleResponse update(Long id, ChequeRequest request);

    SimpleResponse delete(Long id);
    BigDecimal waitersPrice(Long userId);
    BigDecimal dayPrice(Long restaurantId);
}
