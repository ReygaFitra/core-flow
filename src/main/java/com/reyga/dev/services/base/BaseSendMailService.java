package com.reyga.dev.services.base;

import com.reyga.dev.enumeration.ActivityKey;
import com.reyga.dev.enumeration.ActivityType;
import com.reyga.dev.exceptions.AppFaultException;
import com.reyga.dev.utils.CommonLogger;
import jakarta.mail.internet.InternetAddress;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

public abstract class BaseSendMailService<RES, CONTENT> extends BaseActivityProcess<RES> {

    private final static String SEND_MAIL_SERVICE = "sendMail";
    private final static String SEND_MAIL_SERVICE_ATTACHMENT_STREAM = "sendMailWithAttachmentsInputStream";

    public BaseSendMailService(CommonLogger logger) {
        super(logger);
    }

    protected RES execute(String serviceName, String to, String subject, String MsgBody, String[] cc, String[] bcc, Map<String, InputStream> attachmentsInputStream) {
        return process(UUID.randomUUID().toString(), serviceName, (processId) -> {
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

                if (SEND_MAIL_SERVICE_ATTACHMENT_STREAM.equals(ActivityType.INSTANCE.getActivityValue(ActivityKey.SERVICE_NAME.getKeyName()))) {

                    attachmentsInputStreamProcess(messageHelper, attachmentsInputStream);

                }
            };

            try {
                sendingMailProcess(constructedContentDto, preparator, to, subject, MsgBody, cc, bcc, attachmentsInputStream);
            } catch (AppFaultException e) {
                logger.error(e.getMessage());
            }

            return responseProcess(constructedContentDto);
        });

    }

    protected abstract CONTENT preProcess();


    protected abstract void attachmentsInputStreamProcess(MimeMessageHelper messageHelper, Map<String, InputStream> attachmentsInputStream);

    protected abstract void sendingMailProcess(CONTENT contentDto, MimeMessagePreparator preparator, String to, String subject, String MsgBody, String[] cc, String[] bcc, Map<String, InputStream> attachmentsInputStream) throws AppFaultException;

    protected abstract RES responseProcess(CONTENT contentDto);
}
