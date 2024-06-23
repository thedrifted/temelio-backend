package com.temelio_demo.demo.Managers;

import com.temelio_demo.demo.Entities.Foundation;
import com.temelio_demo.demo.Exceptions.CustomException;
import com.temelio_demo.demo.Services.FoundationService;
import com.temelio_demo.demo.Utils.EmailValidatorUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FoundationManager {

    private final FoundationService foundationService;

    public FoundationManager(FoundationService foundationService) {
        this.foundationService = foundationService;
    }

    public void createFoundation(Foundation foundation) throws CustomException {
        String email = foundation.getEmail();
        boolean isEmailValid= EmailValidatorUtils.isEmailValid(email);
        if(!isEmailValid){
            throw  new CustomException(HttpStatus.BAD_REQUEST,"Email is not valid","Email is not valid");
        }
        foundationService.createFoundation(email);
    }

    public List<Foundation> getAllFoundations(){
        return foundationService.getAllFoundations();
    }
}
