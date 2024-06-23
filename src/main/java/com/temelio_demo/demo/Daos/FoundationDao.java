package com.temelio_demo.demo.Daos;

import com.temelio_demo.demo.Entities.Foundation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoundationDao extends JpaRepository<Foundation, Long> {
    Foundation findFoundationByEmail(String email);
    Foundation findFoundationByFid(String foundationId);
}
