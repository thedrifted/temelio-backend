package com.temelio_demo.demo.Services;


import com.temelio_demo.demo.Daos.FoundationDao;
import com.temelio_demo.demo.Entities.Foundation;
import com.temelio_demo.demo.Exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class FoundationService {
    private final FoundationDao foundationDao;

    public FoundationService(FoundationDao foundationDao) {
        this.foundationDao = foundationDao;
    }

    public Foundation getFoundationByEmail(String  email) {
        return  foundationDao.findFoundationByEmail(email);
    }

    public Foundation getFoundationById(Long id) {
        return  foundationDao.findById(id).orElse(null);
    }

    public List<Foundation> getAllFoundations() {
        return foundationDao.findAll();
    }

    public Foundation getFoundationByFoundationId(String fid) {
        return  foundationDao.findFoundationByFid(fid);
    }

    public Foundation createFoundation(String email) throws CustomException {
        Foundation foundation=getFoundationByEmail(email);
        if(Objects.nonNull(foundation)){
            throw new CustomException(HttpStatus.BAD_REQUEST,"Foundation already exists","Foundation already exists");
        }
        foundation=new Foundation(email,UUID.randomUUID().toString());
        return foundationDao.save(foundation);

    }
}
