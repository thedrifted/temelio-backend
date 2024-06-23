package com.temelio_demo.demo.Controllers;


import com.temelio_demo.demo.Dtos.SendEmailDto;
import com.temelio_demo.demo.Exceptions.CustomException;
import com.temelio_demo.demo.Managers.EmailManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(path = "/v1/email")
public class EmailController {

    private final EmailManager emailManager;

    public EmailController(EmailManager emailManager) {
        this.emailManager = emailManager;
    }

    @PostMapping(path = "/send")
    public ResponseEntity<Object> sendEmail(@RequestBody SendEmailDto sendEmailDto) throws CustomException {
        emailManager.sendEmails(sendEmailDto);
        return ResponseEntity.ok(null);
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<Object> getAllEmails() throws CustomException {
        return ResponseEntity.ok(emailManager.getAllEmails());
    }
}
