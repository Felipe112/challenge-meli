package com.meli.challenge.web;

import com.meli.challenge.domain.mutant.business.IMutantBusiness;
import com.meli.challenge.domain.mutant.exceptions.BusinessException;
import com.meli.challenge.domain.mutant.model.Stat;
import com.meli.challenge.web.config.AbstractRestController;
import com.meli.challenge.web.dto.Response;
import com.meli.challenge.web.dto.SequenceDTO;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase con la que exponen los servicios rest.
 *
 * @Autor Andrés F. Ceballos
 * @Since 2021-06-02
 * @Version 1.0
 */
@RestController
@AllArgsConstructor
@RequestMapping("api/v1")
public class MutantController extends AbstractRestController implements IMutantController {

    private final IMutantBusiness business;

    /**
     * Servicio de tipo POST, con el que se capturan las secuencias de ADN a evaluar.
     *
     * @param sequence lista de secuencias
     * @return Response<Boolean></> estructura estandar de respuesta.
     * @throws BusinessException
     */
    @Override
    @PostMapping("/mutant")
    public ResponseEntity<Response<Boolean>> mutant(@RequestBody @NonNull SequenceDTO sequence) throws BusinessException {
        Response<Boolean> response = new Response<>();
        boolean result = business.isMutant(sequence.getDna().toArray(new String[0]));
        response.setData(result);
        response.setState(result);
        return new ResponseEntity<>(response, result ? HttpStatus.OK : HttpStatus.FORBIDDEN);
    }

    /**
     * Servicio de consulta de tipo GET, con el cual consultan un reporte los registros evaluados.
     *
     * @return Response<Stat>
     */
    @Override
    @GetMapping("/stats")
    public ResponseEntity<Response<Stat>> stats() {
        Response<Stat> response = new Response<>();
        response.setData(business.getStats());
        response.setState(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
