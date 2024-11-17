package com.insuranceApp.requests;
public record InsuranceClientFileUpdateRequest(
        String file
) {
    public byte[] getDecodedFile(){
        return java.util.Base64.getDecoder().decode(this.file);
    }
}
