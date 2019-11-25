package com.ntt.hibernate.dao;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import com.ntt.hibernate.Entity.Site;
import java.util.Optional;

/**
 * Spring Data  repository for the Site entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SiteDao extends JpaRepository<Site, Long> {
    @Query(value="SELECT * FROM site WHERE npi = ?1 LIMIT 1", nativeQuery = true)
    Optional<Site> findFirstByOrderByNpiAsc(String npi);

}
