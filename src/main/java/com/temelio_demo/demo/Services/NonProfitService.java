package com.temelio_demo.demo.Services;


import com.temelio_demo.demo.Daos.NonProfitDao;
import com.temelio_demo.demo.Entities.NonProfit;
import com.temelio_demo.demo.Exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class NonProfitService {
    private final NonProfitDao nonProfitDao;

    public NonProfitService(NonProfitDao nonProfitDao) {
        this.nonProfitDao = nonProfitDao;
    }

    public NonProfit getNonProfitByEmail(String email) {
       return nonProfitDao.findNonProfitEntityByEmail(email);
    }

    public NonProfit getNonProfitById(Long id) {
        return nonProfitDao.findById(id).orElse(null);
    }

    public NonProfit getNonProfitByNonProfitId(String npid) {
        return nonProfitDao.findNonProfitByNpid(npid);
    }

    public List<NonProfit> getAll() {
        return nonProfitDao.findAll();
    }

    public NonProfit createNonProfit(String email,String name,String address) throws CustomException {
        NonProfit nonProfit=getNonProfitByEmail(email);
        if(Objects.nonNull(nonProfit)){
            throw new CustomException(HttpStatus.BAD_REQUEST,"Non profit already exists","Non profit already exists");
        }
        nonProfit=new NonProfit(email,name,address,UUID.randomUUID().toString());
        return nonProfitDao.save(nonProfit);
    }
}





