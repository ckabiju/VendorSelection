package com.ntt.controller;

import com.ntt.hibernate.Entity.StockDetails;
import com.ntt.service.SiteService;
import com.ntt.model.DriverSite;
import com.ntt.model.Site;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import com.ntt.controller.Utils.ResponseUtil;

/**
 * REST controller for managing {@link com.ntt.hibernate.Entity.StockDetails}.
 */
@RestController
@RequestMapping("/api")
public class SiteController {

    private final Logger log = LoggerFactory.getLogger(SiteController.class);
    private final SiteService siteService;

    public SiteController(SiteService stockDetailsService) {
        this.siteService = stockDetailsService;
    }

       /**
     * {@code POST  /sites} : Create a new DriverSite.
     *
     * @param sites the DriverSite to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stockDetails, or with status {@code 400 (Bad Request)} if the stockDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sites")
    public ResponseEntity<DriverSite> createSites(@RequestBody DriverSite sites) throws URISyntaxException {
        log.debug("REST request to save StockDetails : {}", sites);
        DriverSite result = siteService.saveDriverSite(sites);

        return ResponseEntity.created(new URI("/api/sites/")).body(result);
    }

    /**
     * {@code GET  /stock-details/:id} : get the "id" stockDetails.
     *
     * @param npi the id of the stockDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stockDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sites/{npi}")
    public ResponseEntity<Site> getSite(@PathVariable String npi) {
        log.debug("REST request to get StockDetails : {}", npi);
        Optional<Site> stockDetails = siteService.findOneBySite(npi);
        return ResponseUtil.wrapOrNotFound(stockDetails);
    }

}
