package com.ntt.service;

import com.ntt.hibernate.Entity.StockDetails;
import com.ntt.model.DriverSite;
import com.ntt.model.Site;
import java.util.Optional;

/**
 * Service Interface for managing {@link StockDetails}.
 */
public interface SiteService {

     /**
     * Save Driver Sites.
     *
     * @param sites to be created.
     * @return the entityDTO.
     */
    DriverSite saveDriverSite(DriverSite sites);

    /**
     * Get Site.
     *
     * @param npi the id of the entity.
     * @return the entity.
     */
    Optional<Site> findOneBySite(String npi);

    public void saveStockDetails(StockDetails stockDetails);

}
