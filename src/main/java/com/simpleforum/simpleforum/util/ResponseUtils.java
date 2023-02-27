package com.simpleforum.simpleforum.util;

import lombok.Getter;

import java.sql.Timestamp;

public class ResponseUtils {
    public static Response createResponse() {
        return new Response();
    }

    public static class Response {
        @Getter
        private final Timestamp timestamp;

        @Getter
        private int status;

        @Getter
        private String message;

        @Getter
        private Object data;

        @Getter
        private String error;

        public Response() {
            this.timestamp = new Timestamp(System.currentTimeMillis());
        }

        public Response success() {
            this.status = 200;
            return this;
        }

        public Response error(int code, String error) {
            this.status = code;
            this.error = error;
            return this;
        }

        public Response setData(Object data) {
            this.data = data;
            return this;
        }

        public Response setMessage(String message) {
            this.message = message;
            return this;
        }
    }

}
