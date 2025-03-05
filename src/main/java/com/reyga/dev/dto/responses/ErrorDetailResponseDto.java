package com.reyga.dev.dto.responses;

import java.sql.Timestamp;

public class ErrorDetailResponseDto {
    private String business;
    private String additionalInfo;
    private Timestamp timestamp;

    public ErrorDetailResponseDto() {
    }

    public ErrorDetailResponseDto(String business, String additionalInfo, Timestamp timestamp) {
        this.business = business;
        this.additionalInfo = additionalInfo;
        this.timestamp = timestamp;
    }

    private ErrorDetailResponseDto(Builder builder) {
        setBusiness(builder.business);
        setAdditionalInfo(builder.additionalInfo);
        setTimestamp(builder.timestamp);
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public static final class Builder {
        private String business;
        private String additionalInfo;
        private Timestamp timestamp;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder business(String val) {
            business = val;
            return this;
        }

        public Builder additionalInfo(String val) {
            additionalInfo = val;
            return this;
        }

        public Builder timestamp(Timestamp val) {
            timestamp = val;
            return this;
        }

        public ErrorDetailResponseDto build() {
            return new ErrorDetailResponseDto(this);
        }
    }
}
