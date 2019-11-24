package com.ntt.service;

import com.ntt.hibernate.Entity.FileContent;
import com.ntt.hibernate.Entity.StockDetails;

public interface AsyncService {

    public void saveStockDetails(StockDetails stockDetails);
    public void saveFileContent(FileContent stockDetails);
}
