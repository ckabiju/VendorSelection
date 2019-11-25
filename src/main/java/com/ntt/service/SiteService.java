package com.ntt.service;

import com.ntt.hibernate.Entity.StockDetails;
import com.ntt.model.DriverSite;
import com.ntt.hibernate.Entity.Site;
import com.ntt.model.SiteDTO;

import java.util.Optional;

/**
 * Service Interface for managing {@link StockDetails}.
 */
public interface SiteService {


    /**
     * Save a site.
     *
     * @param site the entity to save.
     * @return the persisted entity.
     */
    SiteDTO save(SiteDTO site);
    /**
     * Get Site.
     *
     * @param npi the id of the entity.
     * @return the entity.
     */
    Optional<SiteDTO> findOneBySiteNPI(String npi);

    public void saveStockDetails(StockDetails stockDetails);

}
