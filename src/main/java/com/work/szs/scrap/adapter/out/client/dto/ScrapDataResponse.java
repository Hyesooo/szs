package com.work.szs.scrap.adapter.out.client.dto;

import lombok.Getter;

@Getter
public class ScrapDataResponse<T> {
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