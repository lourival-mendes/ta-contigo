package br.com.tacontigo.exceptions;

import java.time.LocalDateTime;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import jakarta.ws.rs.core.Response;

public class GlobalExceptionHandler {

    @ServerExceptionMapper
    public RestResponse<ErrorResponse> handleUsuarioException(UsuarioException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getCode(),
                "Erro ao utilizar o recurso Usuário.",
                ex.getMessage(),
                LocalDateTime.now(),
                null);
        return RestResponse.status(Response.Status.BAD_REQUEST, errorResponse);
    }

    @ServerExceptionMapper
    public RestResponse<ErrorResponse> handleQuestaoException(QuestaoException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getCode(),
                "Erro ao utilizar o recurso Questão.",
                ex.getMessage(),
                LocalDateTime.now(),
                null);
        return RestResponse.status(Response.Status.BAD_REQUEST, errorResponse);
    }

    @ServerExceptionMapper
    public RestResponse<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "SVC-001",
                "Erro interno do servidor.",
                ex.getMessage(),
                LocalDateTime.now(),
                null);
        return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR, errorResponse);
    }
}