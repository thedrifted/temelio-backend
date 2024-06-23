package com.temelio_demo.demo.Managers;


import com.temelio_demo.demo.Dtos.AllEmailDataDto;
import com.temelio_demo.demo.Dtos.SendEmailDto;
import com.temelio_demo.demo.Entities.EmailLog;
import com.temelio_demo.demo.Entities.Foundation;
import com.temelio_demo.demo.Entities.NonProfit;
import com.temelio_demo.demo.Exceptions.CustomException;
import com.temelio_demo.demo.Handlers.ResendHandler;
import com.temelio_demo.demo.Services.EmailLogService;
import com.temelio_demo.demo.Services.FoundationService;
import com.temelio_demo.demo.Services.NonProfitService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmailManager {

    final EmailLogService emailLogService;

    final FoundationService foundationService;

    final NonProfitService nonProfitService;

    public EmailManager(EmailLogService emailLogService, FoundationService foundationService, NonProfitService nonProfitService) {
        this.emailLogService = emailLogService;
        this.foundationService = foundationService;
        this.nonProfitService = nonProfitService;
    }

    public void sendEmails(SendEmailDto sendEmailDto) throws CustomException {
        String from=sendEmailDto.getFoundationId();
        if(StringUtils.isBlank(from)){
            throw new CustomException(HttpStatus.BAD_REQUEST,"Foundation id is blank","Foundation id is blank");
        }

        Foundation foundation=foundationService.getFoundationByFoundationId(from);
        if(Objects.isNull(foundation)){
            throw new CustomException(HttpStatus.BAD_REQUEST,"Foundation id is invalid","Foundation id is invalid");
        }

        List<String> nonProfitIds=sendEmailDto.getNonProfitIds();
        List<NonProfit> nonProfitList=nonProfitIds.stream().map(nonProfitId->{
            NonProfit nonProfit=nonProfitService.getNonProfitByNonProfitId(nonProfitId);
            if(Objects.isNull(nonProfit)){
                try {
                    throw new CustomException(HttpStatus.BAD_REQUEST,"Non profit id is invalid","Non profit id is invalid");
                } catch (CustomException e) {
                    throw new RuntimeException(e);
                }
            }
            return nonProfit;
        }).toList();


        nonProfitList.forEach(nonProfit ->{
            String body="Sending money to nonprofit {name} at address {address}".replace("{name}",nonProfit.getName());
            body=body.replace("{address}",nonProfit.getAddress());
            String subject="Donation";
            ResendHandler.sendEmail(body,subject,foundation.getEmail(),nonProfit.getEmail());
            emailLogService.createdEmailLog(nonProfit,foundation,"Sending money to nonprofit {name} at address {address}","Donation");
        } );
    }

    public Object getAllEmails(){
        List<EmailLog> emailLogsList= emailLogService.findAllEmailLogs();
        return emailLogsList.stream().map(emailLog -> {
            NonProfit nonProfit=nonProfitService.getNonProfitById(emailLog.getNonProfitId());
            return AllEmailDataDto.builder().nonProfitEmail(nonProfit.getEmail())
                    .nonProfitAddress(nonProfit.getAddress()).nonProfitName(nonProfit.getName()).sentDate(nonProfit.getCreatedAt()).build();
        }).toList();
    }
}
