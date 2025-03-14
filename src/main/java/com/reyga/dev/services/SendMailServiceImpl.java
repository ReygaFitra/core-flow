package com.reyga.dev.services;

import com.reyga.dev.dto.content.SendMailContentDto;
import com.reyga.dev.dto.responses.MailResponse;
import com.reyga.dev.exceptions.AppFaultException;
import com.reyga.dev.services.base.BaseSendMailService;
import com.reyga.dev.utils.CommonLogger;
import jakarta.mail.MessagingException;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.*;

@Service
public class SendMailServiceImpl extends BaseSendMailService<MailResponse, SendMailContentDto> implements SendMailService {

    private final JavaMailSender mailSender;

    public SendMailServiceImpl(CommonLogger logger, JavaMailSender mailSender) {
        super(logger);
        this.mailSender = mailSender;
    }

    @Override
    public MailResponse sendMail(String to, String subject, String MsgBody, String[] cc, String[] bcc) throws AppFaultException {
        return this.execute(new Object() {}.getClass().getEnclosingMethod().getName(), to, subject, MsgBody, cc, bcc, null);
    }

    @Override
    public MailResponse sendMailWithAttachmentsInputStream(String to, String subject, String MsgBody, String[] cc, String[] bcc, Map<String, InputStream> attachmentsInputStream) throws AppFaultException {
        return this.execute(new Object() {}.getClass().getEnclosingMethod().getName(), to, subject, MsgBody, cc, bcc, attachmentsInputStream);
    }

    @Override
    protected SendMailContentDto preProcess() {
        return subProcess(UUID.randomUUID().toString(), new Object() {}.getClass().getEnclosingMethod().getName(), (processId) -> {
            return new SendMailContentDto();
        });
    }

    @Override
    protected void attachmentsInputStreamProcess(MimeMessageHelper messageHelper, Map<String, InputStream> attachmentsInputStream) {
        subProcess(UUID.randomUUID().toString(), new Object() {}.getClass().getEnclosingMethod().getName(), (processId) -> {
            attachmentsInputStream.forEach((fileName, attachment) -> {
                try {
                    byte[] bytes = attachment.readAllBytes();
                    ByteArrayDataSource dataSource = new ByteArrayDataSource(bytes, MediaType.APPLICATION_OCTET_STREAM_VALUE);
                    messageHelper.addAttachment(fileName, dataSource);
                } catch (MessagingException e) {
                    logger.error("Error while sending attachment mail : ", e.getMessage());
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            return null;
        });
    }

    @Override
    protected void sendingMailProcess(SendMailContentDto contentDto, MimeMessagePreparator preparator, String to, String subject, String MsgBody, String[] cc, String[] bcc, Map<String, InputStream> attachmentsInputStream) throws AppFaultException {
        subProcess(UUID.randomUUID().toString(), new Object() {}.getClass().getEnclosingMethod().getName(), (processId) -> {
            try {
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
            } catch (Exception e) {
                contentDto.setSuccess(false);
                logger.exception("Send Mail Service", e.getMessage(), e);

                try {
                    throw new AppFaultException("10", "Error While Sending Mail", null, HttpStatus.INTERNAL_SERVER_ERROR);
                } catch (AppFaultException ex) {
                    logger.error(ex.getMessage());
                }
            }
            return null;
        });
    }

    @Override
    protected MailResponse responseProcess(SendMailContentDto contentDto) {
        return subProcess(UUID.randomUUID().toString(), new Object() {}.getClass().getEnclosingMethod().getName(), (processId) -> {
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
        });
    }
}
