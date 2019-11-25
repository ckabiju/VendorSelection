package com.ntt.service.impl;

import com.ntt.hibernate.Entity.StockDetails;
import com.ntt.hibernate.dao.SiteDao;
import com.ntt.hibernate.dao.StockDetailsDao;
import com.ntt.model.SiteDTO;
import com.ntt.service.SiteService;
import com.ntt.hibernate.Entity.Site;
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
	private final SiteDao siteRepository;

    public SiteServiceImpl(StockDetailsDao stockDetailsRepository,SiteDao siteRepository) {
        this.stockDetailsRepository = stockDetailsRepository;
        this.siteRepository = siteRepository;
    }

	/**
	 * Save a stockDetails.
	 *
	 * @param stockDetails the entity to save.
	 * @return the persisted entity.
	 */
    public void saveStockDetails(StockDetails stockDetails){

    	stockDetailsRepository.save(stockDetails);
	}

	/**
	 * Save a site.
	 *
	 * @param site the entity to save.
	 * @return the persisted entity.
	 */
	public SiteDTO save(SiteDTO site){
		Site siteRecord = siteRepository.save(convertSiteDTOToSite(site));
		return  convertSiteToSiteDTO(siteRecord);
	}


	/**
	 * Creates SiteDTO object from Site object
	 *
	 * @param site
	 * @return siteDTo Entity
	 */
	private SiteDTO convertSiteToSiteDTO(Site site){

		SiteDTO siteDTO = new SiteDTO();
		siteDTO.setNpi(site.getNpi());
		siteDTO.setSiteName(site.getSiteName());
		return siteDTO;
	}


	/**
	 * Creates Site object from SiteDTO object
	 *
	 * @param siteDTO .
	 * @return site Entity
	 */
	private Site convertSiteDTOToSite(SiteDTO siteDTO){

		Site site = new Site();
		site.setNpi(siteDTO.getNpi());
		site.setSiteName(siteDTO.getSiteName());
		return site;
	}

	/**
	 * Fetches the Site by NPI
	 *
	 * @param npi .
	 * @return siteDTO
	 */
	// Fetching Site From DB
	@Override
	@Transactional(readOnly = true)
	public Optional<SiteDTO> findOneBySiteNPI(String npi) {
		Optional<Site> site = siteRepository.findFirstByOrderByNpiAsc(npi);
		if(site.isPresent()){
			SiteDTO siteDTO = new SiteDTO();
			siteDTO.setSiteName(site.get().getSiteName());
			siteDTO.setNpi(site.get().getNpi());
			return Optional.of(siteDTO);
		}
		return Optional.empty();

	}

}
