package com.reyga.dev.dto.responses;

public class ReportResponseDto extends BaseResponseDto {
    private ReportDetailsDto documentData;

    public ReportResponseDto() {
    }

    public ReportResponseDto(String status, String code, String message) {
        super(status, code, message);
    }

    public ReportResponseDto(ReportDetailsDto documentData) {
        this.documentData = documentData;
    }

    public ReportResponseDto(String status, String code, String message, ReportDetailsDto documentData) {
        super(status, code, message);
        this.documentData = documentData;
    }

    public ReportDetailsDto getDocumentData() {
        return documentData;
    }

    public void setDocumentData(ReportDetailsDto documentData) {
        this.documentData = documentData;
    }
}
