package com.temelio_demo.demo.Dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendEmailDto {
    private String foundationId;
    private List<String> nonProfitIds;
    private String body;
}
