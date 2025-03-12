package com.reyga.dev.services;

import com.reyga.dev.dto.content.SendMailContentDto;
import com.reyga.dev.dto.responses.MailResponse;
import com.reyga.dev.exceptions.AppFaultException;
import com.reyga.dev.services.base.BaseSendMailService;
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
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class SendMailServiceImplSample extends BaseSendMailService<MailResponse, SendMailContentDto> implements SendMailService {

    private final JavaMailSender mailSender;

    public SendMailServiceImplSample(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public MailResponse sendMail(String to, String subject, String MsgBody, String[] cc, String[] bcc) throws AppFaultException {
        return this.execute(to, subject, MsgBody, cc, bcc, null, null);
    }

    @Override
    public MailResponse sendMailWithAttachments(String to, String subject, String MsgBody, String[] cc, String[] bcc, List<File> attachments) throws AppFaultException {
        return this.execute(to, subject, MsgBody, cc, bcc, attachments, null);
    }

    @Override
    public MailResponse sendMailWithAttachmentsInputStream(String to, String subject, String MsgBody, String[] cc, String[] bcc, Map<String, InputStream> attachmentsInputStream) throws AppFaultException {
        return this.execute(to, subject, MsgBody, cc, bcc, null, attachmentsInputStream);
    }

    @Override
    protected SendMailContentDto preProcess() {
        return new SendMailContentDto();
    }

    @Override
    protected void attachmentsProcess(MimeMessageHelper messageHelper, List<File> attachments) {
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

    @Override
    protected void attachmentsInputStreamProcess(MimeMessageHelper messageHelper, Map<String, InputStream> attachmentsInputStream) {
        attachmentsInputStream.forEach((fileName, attachment) -> {
            try {
                messageHelper.addAttachment(fileName, new InputStreamResource(attachment));
            } catch (MessagingException e) {
                logger.error("Error while sending attachment mail : ", e.getMessage());
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    protected void sendingMailProcess(SendMailContentDto contentDto, MimeMessagePreparator preparator, String to, String subject, String MsgBody, String[] cc, String[] bcc, List<File> attachments, Map<String, InputStream> attachmentsInputStream) {
        mailSender.send(preparator);
        contentDto.setSuccess(true);
        contentDto.setSubject(subject);
        contentDto.setMessage(MsgBody);
        contentDto.setReceiver(to);
        contentDto.setCc(cc);
        contentDto.setBcc(bcc);
        if ((attachments != null && !attachments.isEmpty()) || (attachmentsInputStream != null && !attachmentsInputStream.isEmpty())) {
            contentDto.setSendAttachment(true);
        }
        contentDto.setAttachments(attachments);
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
        return MailResponse.Builder.newBuilder()
                .isSuccess(contentDto.isSuccess())
                .subject(contentDto.getSubject())
                .message(contentDto.getMessage())
                .receiver(contentDto.getReceiver())
                .cc(contentDto.getCc())
                .bcc(contentDto.getBcc())
                .isSendAttachment(contentDto.isSendAttachment())
                .attachments(contentDto.getAttachments())
                .attachmentInputStreams(contentDto.getAttachmentInputStreams())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
