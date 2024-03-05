package com.work.szs.scrap.adapter.out.client.config;

import lombok.Getter;

@Getter
public class ClientApiRes<T> {
    private String status;
    private T data;
    private Error errors;

    @Getter
    public static class Error {
        private String code;
        private String message;
        private String validations;
    }
}