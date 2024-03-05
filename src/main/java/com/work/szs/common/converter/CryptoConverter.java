package com.work.szs.common.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;

@Component
@Converter
@RequiredArgsConstructor
public class CryptoConverter implements AttributeConverter<String, String> {
    private final TextEncryptor textEncryptor;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute == null) return null;
        return textEncryptor.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return textEncryptor.decrypt(dbData);
    }
}