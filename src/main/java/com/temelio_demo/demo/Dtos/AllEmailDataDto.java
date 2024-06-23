package com.temelio_demo.demo.Dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AllEmailDataDto {
    private String nonProfitName;
    private String nonProfitEmail;
    private String nonProfitAddress;
    private LocalDateTime sentDate;
}
