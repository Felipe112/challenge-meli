package com.meli.challenge.domain.mutant.exceptions;

import java.io.IOException;

/**
 * Clase de para el manejo de excepciones del negocio, su objetivo es controlar y presentar los errores.
 *
 * @Autor Andrés F. Ceballos
 * @Since 2021-05-31
 * @Version 1.0
 */
public class BusinessException extends IOException {
    private static final long serialVersionUID = -1741800479810649707L;
    private final String description;

    public BusinessException(String description) {
        super(description);
        this.description = description;
    }

}
