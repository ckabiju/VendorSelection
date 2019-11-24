package com.ntt.service.impl;

import com.ntt.hibernate.Entity.StockDetails;
import com.ntt.hibernate.dao.StockDetailsDao;
import com.ntt.service.SiteService;
import com.ntt.model.DriverSite;
import com.ntt.model.InventorySummary;
import com.ntt.model.Site;
import com.ntt.model.SiteItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
 * Service Implementation for managing {@link StockDetails}.
 */
@Service
@Transactional
public class SiteServiceImpl implements SiteService {

    private final Logger log = LoggerFactory.getLogger(SiteServiceImpl.class);

    private final StockDetailsDao stockDetailsRepository;

    public SiteServiceImpl(StockDetailsDao stockDetailsRepository) {
        this.stockDetailsRepository = stockDetailsRepository;
    }

    public void saveStockDetails(StockDetails stockDetails){
		stockDetailsRepository.save(stockDetails);
	}

	/**
	 * Save Sites.
	 *
	 * @param sites the id of the entity.
	 * @return the entity.
	 */
    @Override
    public DriverSite saveDriverSite(DriverSite sites) {
			
		DriverSite driverSite = new DriverSite();
		if( null != sites) {
			
			Site site = sites.getSite();
			InventorySummary inventorySummary = sites.getInventorySummary();
			StockDetails highestUnitCostSiteItem = getStockDetails(inventorySummary.getHighestUnitCostSiteItem(),site);
			StockDetails highestInventoryValueSiteItem = getStockDetails(inventorySummary.getHighestInventoryValueSiteItem(),site);
			StockDetails highestInventoryUnitsSiteItem = getStockDetails(inventorySummary.getHighestInventoryUnitsSiteItem(),site);
			
			StockDetails lowestUnitCostSiteItem = getStockDetails(inventorySummary.getLowestUnitCostSiteItem(),site);
			StockDetails lowestInventoryValueSiteItem = getStockDetails(inventorySummary.getLowestInventoryValueSiteItem(),site);
			StockDetails lowestInventoryUnitsSiteItem = getStockDetails(inventorySummary.getLowestInventoryUnitsSiteItem(),site);
			
			stockDetailsRepository.save(highestUnitCostSiteItem);
			stockDetailsRepository.save(highestInventoryValueSiteItem);
			stockDetailsRepository.save(highestInventoryUnitsSiteItem);
			
			stockDetailsRepository.save(lowestUnitCostSiteItem);
			stockDetailsRepository.save(lowestInventoryValueSiteItem);
			stockDetailsRepository.save(lowestInventoryUnitsSiteItem);
		}
		return sites;
	}

	/*
	 *  Creating Stock Detail record
	 */
	private StockDetails getStockDetails(SiteItem siteItem, Site site) {
		
		StockDetails stockDetaild = new StockDetails();
		stockDetaild.setNdc(siteItem.getNdc());
		stockDetaild.setNpi(site.getNpi());
		stockDetaild.setSiteName(site.getSiteName());
		stockDetaild.setUnits(siteItem.getTotalUnits().toString());
		stockDetaild.setUnitsCost(siteItem.getUnitCost().toString());
		//stockDetaild.setTotalValue(siteItem.getTotalValue());
		
		return stockDetaild;

	}

	// Fetching Site From DB
	@Override
	public Optional<Site> findOneBySite(String npi) {
		// TODO Auto-generated method stub
		Optional<StockDetails> stockDetails = stockDetailsRepository.findFirstByOrderByNpiAsc(npi);
		
		Site site = new Site();
		site.setNpi(stockDetails.get().getNpi());
		site.setSiteName(stockDetails.get().getSiteName());
		return Optional.of(site);
			
	}

}
