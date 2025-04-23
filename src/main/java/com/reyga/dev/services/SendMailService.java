package com.reyga.dev.services;

import com.reyga.dev.dto.content.SendMailContentDto;
import com.reyga.dev.dto.responses.MailResponse;
import com.reyga.dev.exceptions.AppFaultException;

import java.io.InputStream;
import java.util.Map;

public interface SendMailService {
    MailResponse sendMail(SendMailContentDto contentDto, String to, String subject, String MsgBody, String[] cc, String[] bcc) throws AppFaultException;
    MailResponse sendMailWithAttachmentsInputStream(SendMailContentDto contentDto, String to, String subject, String MsgBody, String[] cc, String[] bcc, Map<String, InputStream> attachmentsInputStream) throws AppFaultException;
}
