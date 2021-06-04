package com.meli.challenge.web.dto;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Clase Usada para estandarizar las respuestas presentadas en los servicios expuestos.
 *
 * @Autor Andrés F. Ceballos
 * @Since 2021-06-02
 * @Version 1.0
 */
@Data
public class Response<T> {

    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    protected boolean state;
    protected String dateTime;
    protected String message;
    protected T data;

    public Response() {
        this(true, new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()), "", null);
    }

    public Response(String message, T data) {
        this(true, new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()), message, data);
    }

    public Response(String message) {
        this(true, new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()), message, null);
    }

    public Response(String message, boolean isSuccess) {
        this(isSuccess, new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()), message, null);
    }

    public Response(Throwable ex) {
        this(false, new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()), ex.getMessage(), null);
    }

    public Response(T data) {
        this(true, new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime()), "", data);
    }

    public Response(boolean state, String dateTime, String message, T data) {
        this.state = state;
        this.dateTime = dateTime;
        this.message = message;
        this.data = data;
    }

}
