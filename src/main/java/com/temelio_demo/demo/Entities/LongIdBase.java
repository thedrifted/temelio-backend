package com.temelio_demo.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class LongIdBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @JsonIgnore
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @JsonIgnore
    private  LocalDateTime updatedAt;

    @PrePersist
    void handleDates(){
        if(createdAt == null){
            setCreatedAt(LocalDateTime.now());
        }
        setUpdatedAt(LocalDateTime.now());
    }
    @PreUpdate
    void handleUpdatedDate(){
        setUpdatedAt(LocalDateTime.now());
    }
}
