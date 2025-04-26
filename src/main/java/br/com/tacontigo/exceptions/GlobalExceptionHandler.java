package br.com.tacontigo.exceptions;

import java.time.LocalDateTime;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import jakarta.ws.rs.core.Response;

public class GlobalExceptionHandler {

    @ServerExceptionMapper
    public RestResponse<ErrorResponse> handleUsuarioCriarException(UsuarioCriarException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getCode(),
                ex.getMessage(),
                null,
                LocalDateTime.now(),
                null
        );
        return RestResponse.status(Response.Status.BAD_REQUEST, errorResponse);
    }

    @ServerExceptionMapper
    public RestResponse<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "GEN-001",
                "Erro interno do servidor.",
                ex.getMessage(),
                LocalDateTime.now(),
                null
        );
        return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR, errorResponse);
    }
}