package global.api;

import global.dto.request.ChequeRequest;
import global.dto.response.ChequeResponse;
import global.dto.response.SimpleResponse;
import global.service.ChequeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Abdyrazakova Aizada
 */
@RestController
@RequestMapping("/api/cheque")
@RequiredArgsConstructor
@Tag(name = "CHEQUE-API")
public class ChequeApi {
    private final ChequeService chequeService;

    @PreAuthorize("hasAuthority('WAITER')")
    @PostMapping("/save")
    public SimpleResponse save( @RequestBody ChequeRequest chequeRequest){
        return chequeService.save(chequeRequest);
    }

    @PreAuthorize("hasAuthority('WAITER')")
    @GetMapping("/getAll")
    public List<ChequeResponse> getAll(){
        return chequeService.findAll();
    }
}
