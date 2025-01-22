package com.blubin.commonservice.constants;

public final class ApiResponseConstants {

    public enum HttpResponseStatus {
        OK("200", "Ok"),
        CREATED("201", "Created"),
        NO_CONTENT("204", "No content"),
        BAD_REQUEST("400", "Bad request"),
        UNAUTHORIZED("401", "Unauthorized"),
        FORBIDDEN("403", "Forbidden"),
        NOT_FOUND("404", "Not found"),
        INTERNAL_SERVER_ERROR("500", "Internal server error");

        private final String code;
        private final String message;

        HttpResponseStatus(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
