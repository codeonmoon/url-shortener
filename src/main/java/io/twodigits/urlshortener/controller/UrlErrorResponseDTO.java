package io.twodigits.urlshortener.controller;

public class UrlErrorResponseDTO {

    private String status;
    private String error;

    public UrlErrorResponseDTO() {
    }

    public UrlErrorResponseDTO(String status, String error) {
        this.status = status;
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "UrlErrorResponseDTO{" +
                "status='" + status + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
