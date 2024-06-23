package com.temelio_demo.demo.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "email_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailLog extends LongIdBase {
    @Column(name="foundation_id")
    private Long foundationId;
    @Column(name="non_profit_id")
    private Long nonProfitId;
    private String body;
    private String subject;
}
