package com.reyga.dev.services.base;

import com.reyga.dev.dto.content.base.BaseContentDto;
import com.reyga.dev.enumeration.ActivityKey;
import com.reyga.dev.exceptions.AppFaultException;
import com.reyga.dev.utils.ActivityConstruction;
import com.reyga.dev.utils.CommonLogger;
import jakarta.mail.internet.InternetAddress;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

public abstract class BaseSendMailService<RES, CONTENT extends BaseContentDto> extends BaseActivityProcess<CONTENT> {

    private final static String SEND_MAIL_SERVICE = "sendMail";
    private final static String SEND_MAIL_SERVICE_ATTACHMENT_STREAM = "sendMailWithAttachmentsInputStream";

    public BaseSendMailService(ActivityConstruction activityConstruction, CommonLogger logger) {
        super(activityConstruction, logger);
    }

    protected RES execute(String serviceName, CONTENT contentDto, String to, String subject, String MsgBody, String[] cc, String[] bcc, Map<String, InputStream> attachmentsInputStream) {
        return construct(contentDto, UUID.randomUUID().toString(), serviceName, (processId) -> {
            CONTENT constructedContentDto = preProcess(contentDto);

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

                if (SEND_MAIL_SERVICE_ATTACHMENT_STREAM.equals(activityConstruction.getActivityValue(constructedContentDto.getActivityLog(), ActivityKey.SERVICE_NAME.getKeyName()))) {

                    attachmentsInputStreamProcess(constructedContentDto, messageHelper, attachmentsInputStream);

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

    protected abstract CONTENT preProcess(CONTENT contentDto);

    protected abstract void attachmentsInputStreamProcess(CONTENT contentDto, MimeMessageHelper messageHelper, Map<String, InputStream> attachmentsInputStream);

    protected abstract void sendingMailProcess(CONTENT contentDto, MimeMessagePreparator preparator, String to, String subject, String MsgBody, String[] cc, String[] bcc, Map<String, InputStream> attachmentsInputStream) throws AppFaultException;

    protected abstract RES responseProcess(CONTENT contentDto);
}
