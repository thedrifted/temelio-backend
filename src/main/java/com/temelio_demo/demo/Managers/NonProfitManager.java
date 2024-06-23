package com.temelio_demo.demo.Managers;

import com.temelio_demo.demo.Entities.NonProfit;
import com.temelio_demo.demo.Exceptions.CustomException;
import com.temelio_demo.demo.Services.NonProfitService;
import com.temelio_demo.demo.Utils.EmailValidatorUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NonProfitManager {
    private final NonProfitService nonProfitService;

    public NonProfitManager(NonProfitService nonProfitService) {
        this.nonProfitService = nonProfitService;
    }


    public void createNonProfitOrganization(NonProfit nonProfit) throws CustomException {
        String email = nonProfit.getEmail();
        boolean isEmailValid= EmailValidatorUtils.isEmailValid(email);
        if(!isEmailValid){
            throw new CustomException(HttpStatus.BAD_REQUEST,"Email is not valid","Email is not valid");
        }

        nonProfitService.createNonProfit(nonProfit.getEmail(),nonProfit.getName(),nonProfit.getAddress());

    }

    public List<NonProfit> getAllNonProfitOrganization(){
        return nonProfitService.getAll();
    }
}
