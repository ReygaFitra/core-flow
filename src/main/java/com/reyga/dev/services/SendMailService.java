package com.reyga.dev.services;

import com.reyga.dev.dto.responses.MailResponse;
import com.reyga.dev.exceptions.AppFaultException;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface SendMailService {
    MailResponse sendMail(String from, String to, String subject, String MsgBody, String[] cc, String[] bcc) throws AppFaultException;
    MailResponse sendMailWithAttachments(String from, String to, String subject, String MsgBody, String[] cc, String[] bcc, List<File> attachments) throws AppFaultException;
    MailResponse sendMailWithAttachmentsInputStream(String from, String to, String subject, String MsgBody, String[] cc, String[] bcc, Map<String, InputStream> attachmentsInputStream) throws AppFaultException;
}
