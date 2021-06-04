package com.meli.challenge.web;

import com.meli.challenge.domain.mutant.exceptions.BusinessException;
import com.meli.challenge.domain.mutant.model.Stat;
import com.meli.challenge.web.dto.Response;
import com.meli.challenge.web.dto.SequenceDTO;
import io.swagger.annotations.Api;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Api(value = "api/v1")
public interface IMutantController {

    ResponseEntity<Response<Boolean>> mutant(@RequestBody @NonNull SequenceDTO sequence) throws BusinessException;

    ResponseEntity<Response<Stat>> stats();

}
