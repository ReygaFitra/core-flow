package com.reyga.dev.dto.responses;

import java.sql.Timestamp;

public class ReportDetailsDto {
    private String id;
    private String fileName;
    private String fileType;
    private String filePath;
    private long fileSize;
    private Timestamp dateCreated;
    private boolean isStoredInObjectStorage;

    public ReportDetailsDto() {
    }

    public ReportDetailsDto(String id, String fileName, String fileType, String filePath, long fileSize, Timestamp dateCreated, boolean isStoredInObjectStorage) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.dateCreated = dateCreated;
        this.isStoredInObjectStorage = isStoredInObjectStorage;
    }

    public ReportDetailsDto(String id, String fileName, String fileType, long fileSize, Timestamp dateCreated, boolean isStoredInObjectStorage) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.dateCreated = dateCreated;
        this.isStoredInObjectStorage = isStoredInObjectStorage;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private ReportDetailsDto(Builder builder) {
        setId(builder.id);
        setFileName(builder.fileName);
        setFileType(builder.fileType);
        setFilePath(builder.filePath);
        setFileSize(builder.fileSize);
        setDateCreated(builder.dateCreated);
        setStoredInObjectStorage(builder.isStoredInObjectStorage);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isStoredInObjectStorage() {
        return isStoredInObjectStorage;
    }

    public void setStoredInObjectStorage(boolean isStoredInObjectStorage) {
        this.isStoredInObjectStorage = isStoredInObjectStorage;
    }

    @Override
    public String toString() {
        return "ReportDetailsDto{" +
                "id='" + id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", dateCreated=" + dateCreated +
                ", isStoredInObjectStorage=" + isStoredInObjectStorage +
                '}';
    }


    public static final class Builder {
        private String id;
        private String fileName;
        private String fileType;
        private String filePath;
        private long fileSize;
        private Timestamp dateCreated;
        private boolean isStoredInObjectStorage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder fileName(String val) {
            fileName = val;
            return this;
        }

        public Builder fileType(String val) {
            fileType = val;
            return this;
        }

        public Builder filePath(String val) {
            filePath = val;
            return this;
        }

        public Builder fileSize(long val) {
            fileSize = val;
            return this;
        }

        public Builder dateCreated(Timestamp val) {
            dateCreated = val;
            return this;
        }

        public Builder isStoredInObjectStorage(boolean val) {
            isStoredInObjectStorage = val;
            return this;
        }

        public ReportDetailsDto build() {
            return new ReportDetailsDto(this);
        }
    }
}
