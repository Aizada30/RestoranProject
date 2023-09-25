package global.service;

import global.dto.request.ChequeRequest;
import global.dto.response.ChequeResponse;
import global.dto.response.SimpleResponse;

import java.time.LocalDate;
import java.util.List;

/**
 * Abdyrazakova Aizada
 */
public interface ChequeService {
    List<ChequeResponse> findAll();

    SimpleResponse save(ChequeRequest request);

    ChequeResponse findById(Long id);

    SimpleResponse update(Long id,ChequeRequest request);
    SimpleResponse delete(Long id);

    SimpleResponse totalSum(Long id,LocalDate date);

    SimpleResponse avg(LocalDate date);
}
