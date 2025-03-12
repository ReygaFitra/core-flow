package com.reyga.dev.services;

import com.reyga.dev.dto.content.SendMailContentDto;
import com.reyga.dev.dto.responses.MailResponse;
import com.reyga.dev.exceptions.AppFaultException;
import com.reyga.dev.services.base.BaseSendMailService;
import jakarta.mail.MessagingException;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class SendMailServiceImpl extends BaseSendMailService<MailResponse, SendMailContentDto> implements SendMailService {

    private final JavaMailSender mailSender;

    public SendMailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public MailResponse sendMail(String to, String subject, String MsgBody, String[] cc, String[] bcc) throws AppFaultException {
        return this.execute(to, subject, MsgBody, cc, bcc, null);
    }

    @Override
    public MailResponse sendMailWithAttachmentsInputStream(String to, String subject, String MsgBody, String[] cc, String[] bcc, Map<String, InputStream> attachmentsInputStream) throws AppFaultException {
        return this.execute(to, subject, MsgBody, cc, bcc, attachmentsInputStream);
    }

    @Override
    protected SendMailContentDto preProcess() {
        return new SendMailContentDto();
    }

    @Override
    protected void attachmentsInputStreamProcess(MimeMessageHelper messageHelper, Map<String, InputStream> attachmentsInputStream) {
        attachmentsInputStream.forEach((fileName, attachment) -> {
            try {
                byte[] bytes = attachment.readAllBytes();
                ByteArrayDataSource dataSource = new ByteArrayDataSource(bytes, "application/octet-stream");
                messageHelper.addAttachment(fileName, dataSource);
            } catch (MessagingException e) {
                logger.error("Error while sending attachment mail : ", e.getMessage());
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    protected void sendingMailProcess(SendMailContentDto contentDto, MimeMessagePreparator preparator, String to, String subject, String MsgBody, String[] cc, String[] bcc, Map<String, InputStream> attachmentsInputStream) {
        mailSender.send(preparator);

        contentDto.setSuccess(true);
        contentDto.setSubject(subject);
        contentDto.setMessage(MsgBody);
        contentDto.setReceiver(to);
        contentDto.setCc(cc);
        contentDto.setBcc(bcc);

        if (attachmentsInputStream != null && !attachmentsInputStream.isEmpty())
            contentDto.setSendAttachment(true);

        contentDto.setAttachmentInputStreams(attachmentsInputStream);
    }

    @Override
    protected void errorHandlingProcess(SendMailContentDto contentDto, Exception e) throws AppFaultException {
        contentDto.setSuccess(false);
        logger.exception("Send Mail Service", e.getMessage(), e);

        throw new AppFaultException("10", "Error While Sending Mail", null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected MailResponse responseProcess(SendMailContentDto contentDto) {
        List<String> attachmentNames = (contentDto.getAttachmentInputStreams() != null)
                ? new ArrayList<>(contentDto.getAttachmentInputStreams().keySet())
                : Collections.emptyList();
        return MailResponse.Builder.newBuilder()
                .isSuccess(contentDto.isSuccess())
                .subject(contentDto.getSubject())
                .message(contentDto.getMessage())
                .receiver(contentDto.getReceiver())
                .cc(contentDto.getCc())
                .bcc(contentDto.getBcc())
                .isSendAttachment(contentDto.isSendAttachment())
                .attachmentInfo(attachmentNames)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
