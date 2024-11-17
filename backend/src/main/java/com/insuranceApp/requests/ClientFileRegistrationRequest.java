package com.insuranceApp.requests;

public record ClientFileRegistrationRequest(
        String fileName,
        Long fileSize,
        String fileFormat,
        String fileContent
) {
}
