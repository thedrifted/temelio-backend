package com.temelio_demo.demo.Handlers;

import com.resend.*;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class ResendHandler {
    private static String key=null;

    public static void sendEmail(String body,String subject, String from,String to){
        log.info("Sending email body:{} to:{} from:{} with a subject:{}",body,to,from,subject);
        if(StringUtils.isBlank(key)){
            return;
        }
        Resend resend = new Resend(key);
        CreateEmailOptions params = CreateEmailOptions.builder()
                .from(from)
                .to(to)
                .subject(subject)
                .html(body)
                .build();

        try {
            resend.emails().send(params);
        } catch (ResendException e) {
            log.error("Error while sending email",e);
        }

    }
}
