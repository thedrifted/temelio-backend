package com.temelio_demo.demo.Controllers;

import com.temelio_demo.demo.Entities.NonProfit;
import com.temelio_demo.demo.Exceptions.CustomException;
import com.temelio_demo.demo.Managers.NonProfitManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@Slf4j
@RequestMapping(path = "/v1/non-profits")
public class nonProfitController {

    private final NonProfitManager nonProfitManager;

    public nonProfitController(NonProfitManager nonProfitManager) {
        this.nonProfitManager = nonProfitManager;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Object> createNonProfits(@RequestBody NonProfit nonProfit) throws CustomException {
            nonProfitManager.createNonProfitOrganization(nonProfit);
            return ResponseEntity.ok(null);
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<Object> getAllNonProfit() {
        return ResponseEntity.ok(nonProfitManager.getAllNonProfitOrganization());
    }
}
