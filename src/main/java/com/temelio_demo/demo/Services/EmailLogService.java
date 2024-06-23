package com.temelio_demo.demo.Services;


import com.temelio_demo.demo.Daos.EmailLogDao;
import com.temelio_demo.demo.Entities.EmailLog;
import com.temelio_demo.demo.Entities.Foundation;
import com.temelio_demo.demo.Entities.NonProfit;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailLogService {
    private final EmailLogDao emailLogDao;

    public EmailLogService(EmailLogDao emailLogDao) {
        this.emailLogDao = emailLogDao;
    }

    public List<EmailLog> findAllEmailLogs(){
        return emailLogDao.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public void createdEmailLog(NonProfit nonProfit, Foundation foundation,String body,String subject){
        body=body.replace("{name}",nonProfit.getName());
        body=body.replace("{address}",nonProfit.getAddress());
        EmailLog emailLog = new EmailLog(foundation.getId(),nonProfit.getId(),body,subject);
        emailLogDao.save(emailLog);
    }
}
