package com.reyga.dev.dto.content;

import java.io.File;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class SendMailContentDto {
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
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

    public void setSendAttachment(boolean sendAttachment) {
        isSendAttachment = sendAttachment;
    }
}
