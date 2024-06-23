package com.temelio_demo.demo.Daos;


import com.temelio_demo.demo.Entities.EmailLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailLogDao extends JpaRepository<EmailLog, Long> {
}
