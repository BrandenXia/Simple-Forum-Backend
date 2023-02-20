package com.simpleforum.simpleforum.util;

import lombok.Getter;

public class ResponseUtils {
    public static Response createResponse() {
        return new Response();
    }

    public static class Response {
        @Getter
        private String status;

        @Getter
        private int code;

        @Getter
        private Object data;

        @Getter
        private String message;

        public Response() {
        }

        public Response(String status, int code, Object data, String message) {
            this.status = status;
            this.code = code;
            this.data = data;
            this.message = message;
        }

        public Response success() {
            this.status = "success";
            return this;
        }

        public Response error() {
            this.status = "error";
            return this;
        }

        public Response setCode(int code) {
            this.code = code;
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
