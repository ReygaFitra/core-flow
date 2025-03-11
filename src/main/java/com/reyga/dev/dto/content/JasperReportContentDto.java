package com.reyga.dev.dto.content;

import java.nio.file.Path;
import java.sql.Timestamp;

public class JasperReportContentDto {
    private String reportId;
    private String reportFileName;
    private Path reportPath;
    private long reportSize;
    private Timestamp dateCreated;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportFileName() {
        return reportFileName;
    }

    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
    }

    public Path getReportPath() {
        return reportPath;
    }

    public void setReportPath(Path reportPath) {
        this.reportPath = reportPath;
    }

    public long getReportSize() {
        return reportSize;
    }

    public void setReportSize(long reportSize) {
        this.reportSize = reportSize;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }
}
