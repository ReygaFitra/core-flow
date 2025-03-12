package com.reyga.dev.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.File;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MailResponse {
    private boolean isSuccess;
    private String subject;
    private String message;
    private String receiver;
    private String[] cc;
    private String[] bcc;
    private boolean isSendAttachment;
    private List<File> attachments;
    private Map<String, InputStream> attachmentInputStreams;
    private Timestamp timestamp;

    public MailResponse() {
    }

    public MailResponse(boolean isSuccess, String subject, String message, String receiver, String[] cc, String[] bcc, boolean isSendAttachment, List<File> attachments, Map<String, InputStream> attachmentInputStreams, Timestamp timestamp) {
        this.isSuccess = isSuccess;
        this.subject = subject;
        this.message = message;
        this.receiver = receiver;
        this.cc = cc;
        this.bcc = bcc;
        this.isSendAttachment = isSendAttachment;
        this.attachments = attachments;
        this.attachmentInputStreams = attachmentInputStreams;
        this.timestamp = timestamp;
    }

    private MailResponse(Builder builder) {
        setSuccess(builder.isSuccess);
        setSubject(builder.subject);
        setMessage(builder.message);
        setReceiver(builder.receiver);
        setCc(builder.cc);
        setBcc(builder.bcc);
        setSendAttachment(builder.isSendAttachment);
        setAttachments(builder.attachments);
        setAttachmentInputStreams(builder.attachmentInputStreams);
        setTimestamp(builder.timestamp);
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    public boolean isSendAttachment() {
        return isSendAttachment;
    }

    public void setSendAttachment(boolean sendAttachment) {
        isSendAttachment = sendAttachment;
    }

    public List<File> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<File> attachments) {
        this.attachments = attachments;
    }

    public Map<String, InputStream> getAttachmentInputStreams() {
        return attachmentInputStreams;
    }

    public void setAttachmentInputStreams(Map<String, InputStream> attachmentInputStreams) {
        this.attachmentInputStreams = attachmentInputStreams;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "MailResponse{" +
                "isSuccess=" + isSuccess +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", receiver='" + receiver + '\'' +
                ", cc=" + Arrays.toString(cc) +
                ", bcc=" + Arrays.toString(bcc) +
                ", isSendAttachment=" + isSendAttachment +
                ", attachments=" + attachments +
                ", attachmentInputStreams=" + attachmentInputStreams +
                ", timestamp=" + timestamp +
                '}';
    }

    public static final class Builder {
        private boolean isSuccess;
        private String subject;
        private String message;
        private String receiver;
        private String[] cc;
        private String[] bcc;
        private boolean isSendAttachment;
        private List<File> attachments;
        private Map<String, InputStream> attachmentInputStreams;
        private Timestamp timestamp;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder isSuccess(boolean val) {
            isSuccess = val;
            return this;
        }

        public Builder subject(String val) {
            subject = val;
            return this;
        }

        public Builder message(String val) {
            message = val;
            return this;
        }

        public Builder receiver(String val) {
            receiver = val;
            return this;
        }

        public Builder cc(String[] val) {
            cc = val;
            return this;
        }

        public Builder bcc(String[] val) {
            bcc = val;
            return this;
        }

        public Builder isSendAttachment(boolean val) {
            isSendAttachment = val;
            return this;
        }

        public Builder attachments(List<File> val) {
            attachments = val;
            return this;
        }

        public Builder attachmentInputStreams(Map<String, InputStream> val) {
            attachmentInputStreams = val;
            return this;
        }

        public Builder timestamp(Timestamp val) {
            timestamp = val;
            return this;
        }

        public MailResponse build() {
            return new MailResponse(this);
        }
    }
}
