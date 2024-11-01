package br.com.fiap.isolutions.util;

import jakarta.ws.rs.core.Response;

public class ErrorResponse {

    public static Response createErrorResponse(Response.Status status, String message) {
        return Response.status(status).entity(new ErrorMessage(message)).build();
    }

    public static class ErrorMessage {
        private String message;

        public ErrorMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
