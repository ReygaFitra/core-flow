package com.reyga.dev.services;

import com.reyga.dev.dto.content.SendMailContentDto;
import com.reyga.dev.dto.responses.MailResponse;
import com.reyga.dev.exceptions.AppFaultException;
import com.reyga.dev.utils.CommonLogger;
import com.reyga.dev.utils.DateUtil;
import jakarta.mail.MessagingException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class SendMailServiceImpl implements SendMailService {

    private final JavaMailSender mailSender;
    private final CommonLogger logger = new CommonLogger();

    public SendMailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public MailResponse sendMail(String from, String to, String subject, String MsgBody, String[] cc, String[] bcc) throws AppFaultException {
        SendMailContentDto contentDto = new SendMailContentDto();

        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(MsgBody, true);
            if (cc != null && cc.length > 0) {
                messageHelper.setCc(cc);
            }
            if (bcc != null && bcc.length > 0) {
                messageHelper.setBcc(bcc);
            }
        };

        try {
            this.mailSender.send(preparator);

            contentDto.setSuccess(true);
            contentDto.setSender(from);
            contentDto.setSubject(subject);
            contentDto.setMessage(MsgBody);
            contentDto.setReceiver(from);
            contentDto.setCc(cc);
            contentDto.setBcc(bcc);
            contentDto.setSendAttachment(false);

        } catch (Exception e) {
            contentDto.setSuccess(false);
            if (e instanceof MailException) {
                logger.exception("Send Mail Service", null, e);
                throw new AppFaultException("10", "Error While Sending Mail", null, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                logger.exception("Send Mail Service", "Global Error", e);
                throw new AppFaultException("99", "GENERAL ERROR", null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return MailResponse.Builder.newBuilder()
                .isSuccess(contentDto.isSuccess())
                .sender(contentDto.getSender())
                .subject(contentDto.getSubject())
                .message(contentDto.getMessage())
                .receiver(contentDto.getReceiver())
                .cc(contentDto.getCc())
                .bcc(contentDto.getBcc())
                .isSendAttachment(contentDto.isSendAttachment())
                .timestamp(DateUtil.getTimestamp(LocalDateTime.now()))
                .build();
    };

    @Override
    public MailResponse sendMailWithAttachments(String from, String to, String subject, String MsgBody, String[] cc, String[] bcc, List<File> attachments) throws AppFaultException {
        SendMailContentDto contentDto = new SendMailContentDto();

        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(MsgBody, true);
            if (cc != null && cc.length > 0) {
                messageHelper.setCc(cc);
            }
            if (bcc != null && bcc.length > 0) {
                messageHelper.setBcc(bcc);
            }
            if (attachments != null && !attachments.isEmpty()) {
                attachments.forEach(attachment -> {
                    try {
                        FileSystemResource file = new FileSystemResource(attachment);
                        messageHelper.addAttachment(attachment.getName(), file);
                    } catch (MessagingException e) {
                        logger.error("Error while sending attachment mail : ", e.getMessage());
                        throw new RuntimeException(e);
                    }
                });
            }
        };

        try {
            this.mailSender.send(preparator);

            contentDto.setSuccess(true);
            contentDto.setSender(from);
            contentDto.setSubject(subject);
            contentDto.setMessage(MsgBody);
            contentDto.setReceiver(from);
            contentDto.setCc(cc);
            contentDto.setBcc(bcc);
            contentDto.setSendAttachment(true);
            contentDto.setAttachments(attachments);

        } catch (Exception e) {
            contentDto.setSuccess(false);
            if (e instanceof MailException) {
                logger.exception("Send Mail Service", null, e);
                throw new AppFaultException("10", "Error While Sending Mail", null, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                logger.exception("Send Mail Service", "Global Error", e);
                throw new AppFaultException("99", "GENERAL ERROR", null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return MailResponse.Builder.newBuilder()
                .isSuccess(contentDto.isSuccess())
                .sender(contentDto.getSender())
                .subject(contentDto.getSubject())
                .message(contentDto.getMessage())
                .receiver(contentDto.getReceiver())
                .cc(contentDto.getCc())
                .bcc(contentDto.getBcc())
                .isSendAttachment(contentDto.isSendAttachment())
                .attachments(contentDto.getAttachments())
                .timestamp(DateUtil.getTimestamp(LocalDateTime.now()))
                .build();
    }

    @Override
    public MailResponse sendMailWithAttachmentsInputStream(String from, String to, String subject, String MsgBody, String[] cc, String[] bcc, Map<String, InputStream> attachmentsInputStream) throws AppFaultException {
        SendMailContentDto contentDto = new SendMailContentDto();

        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(MsgBody, true);
            if (cc != null && cc.length > 0) {
                messageHelper.setCc(cc);
            }
            if (bcc != null && bcc.length > 0) {
                messageHelper.setBcc(bcc);
            }
            if (attachmentsInputStream != null && !attachmentsInputStream.isEmpty()) {
                attachmentsInputStream.forEach((fileName, attachment) -> {
                    try {
                        messageHelper.addAttachment(fileName, new InputStreamResource(attachment));
                    } catch (MessagingException e) {
                        logger.error("Error while sending attachment mail : ", e.getMessage());
                        throw new RuntimeException(e);
                    }
                });
            }
        };

        try {
            this.mailSender.send(preparator);

            contentDto.setSuccess(true);
            contentDto.setSender(from);
            contentDto.setSubject(subject);
            contentDto.setMessage(MsgBody);
            contentDto.setReceiver(from);
            contentDto.setCc(cc);
            contentDto.setBcc(bcc);
            contentDto.setSendAttachment(true);
            contentDto.setAttachmentInputStreams(attachmentsInputStream);

        } catch (Exception e) {
            contentDto.setSuccess(false);
            if (e instanceof MailException) {
                logger.exception("Send Mail Service", null, e);
                throw new AppFaultException("10", "Error While Sending Mail", null, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                logger.exception("Send Mail Service", "Global Error", e);
                throw new AppFaultException("99", "GENERAL ERROR", null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return MailResponse.Builder.newBuilder()
                .isSuccess(contentDto.isSuccess())
                .sender(contentDto.getSender())
                .subject(contentDto.getSubject())
                .message(contentDto.getMessage())
                .receiver(contentDto.getReceiver())
                .cc(contentDto.getCc())
                .bcc(contentDto.getBcc())
                .isSendAttachment(contentDto.isSendAttachment())
                .attachmentInputStreams(contentDto.getAttachmentInputStreams())
                .timestamp(DateUtil.getTimestamp(LocalDateTime.now()))
                .build();
    }
}
