package com.reyga.dev.enumeration;

public enum ReportType {
    CSV("csv"),
    XLSX("xlsx"),
    HTML("html"),
    XML("xml"),
    DOC("doc"),
    PDF("pdf");

    private final String type;

    public String getType() {
        return type;
    }

    ReportType(String type) {
        this.type = type;
    }
}
