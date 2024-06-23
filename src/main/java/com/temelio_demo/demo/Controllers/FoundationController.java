package com.temelio_demo.demo.Controllers;


import com.temelio_demo.demo.Entities.Foundation;
import com.temelio_demo.demo.Entities.NonProfit;
import com.temelio_demo.demo.Exceptions.CustomException;
import com.temelio_demo.demo.Managers.FoundationManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(path = "/v1/foundation")
public class FoundationController {

    private final FoundationManager foundationManager;

    public FoundationController(FoundationManager foundationManager) {
        this.foundationManager = foundationManager;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Object> createFoundation(@RequestBody Foundation foundation) throws CustomException {
        foundationManager.createFoundation(foundation);
        return ResponseEntity.ok(null);
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<Object> getAllFoundation() throws CustomException {
        return ResponseEntity.ok(foundationManager.getAllFoundations());
    }

}
