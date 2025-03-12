package com.reyga.dev.services.base;

import com.reyga.dev.exceptions.AppFaultException;
import com.reyga.dev.utils.CommonLogger;
import jakarta.mail.internet.InternetAddress;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public abstract class BaseSendMailService<RES, CONTENT> {

    protected final CommonLogger logger = new CommonLogger();

    protected RES execute(String to, String subject, String MsgBody, String[] cc, String[] bcc, List<File> attachments, Map<String, InputStream> attachmentsInputStream) throws AppFaultException {
        CONTENT constructedContentDto = preProcess();

        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(new InternetAddress(to));
            messageHelper.setSubject(subject);
            messageHelper.setText(MsgBody, true);

            if (cc != null && cc.length > 0) {
                messageHelper.setCc(cc);
            }
            if (bcc != null && bcc.length > 0) {
                messageHelper.setBcc(bcc);
            }

            if (attachments != null && !attachments.isEmpty()) {

                attachmentsProcess(messageHelper, attachments);

            }

            if (attachmentsInputStream != null && !attachmentsInputStream.isEmpty()) {

                attachmentsInputStreamProcess(messageHelper, attachmentsInputStream);

            }
        };

        try {

            sendingMailProcess(constructedContentDto, preparator, to, subject, MsgBody, cc, bcc, attachments, attachmentsInputStream);

        } catch (Exception e) {

            errorHandlingProcess(constructedContentDto, e);

        }

        return responseProcess(constructedContentDto);

    }

    protected abstract CONTENT preProcess();

    protected abstract void attachmentsProcess(MimeMessageHelper messageHelper, List<File> attachments);

    protected abstract void attachmentsInputStreamProcess(MimeMessageHelper messageHelper, Map<String, InputStream> attachmentsInputStream);

    protected abstract void sendingMailProcess(CONTENT contentDto, MimeMessagePreparator preparator, String to, String subject, String MsgBody, String[] cc, String[] bcc, List<File> attachments, Map<String, InputStream> attachmentsInputStream);

    protected abstract void errorHandlingProcess(CONTENT contentDto, Exception e) throws AppFaultException;

    protected abstract RES responseProcess(CONTENT contentDto);
}
