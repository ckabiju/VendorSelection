package com.ntt.service.impl;
import com.ntt.hibernate.Entity.FileContent;
import com.ntt.hibernate.Entity.StockDetails;
import com.ntt.hibernate.dao.FileContentDao;
import com.ntt.hibernate.dao.StockDetailsDao;
import com.ntt.service.AsyncService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AsyncServiceImp implements  AsyncService {

    private final FileContentDao fileContentRepository;

    private final StockDetailsDao stockDetailsRepository;

    public AsyncServiceImp(FileContentDao fileContentRepository, StockDetailsDao stockDetailsRepository) {
        this.fileContentRepository = fileContentRepository;
        this.stockDetailsRepository = stockDetailsRepository;
    }

    public void saveStockDetails(StockDetails stockDetails){
        stockDetailsRepository.save(stockDetails);;

   }

    public void saveFileContent(FileContent fileContent){
        fileContentRepository.save(fileContent);;
    }

}
