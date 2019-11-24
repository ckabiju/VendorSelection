package com.ntt.model;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A InventorySummary.
 */

public class InventorySummary implements Serializable {

    private static final long serialVersionUID = 1L;
      
    private Integer uniqueDrugCount;

    private BigDecimal totalInventoryValue;

    private SiteItem highestInventoryUnitsSiteItem;

    private SiteItem highestInventoryValueSiteItem;

    private SiteItem lowestUnitCostSiteItem;

    private SiteItem lowestInventoryUnitsSiteItem;

    private SiteItem lowestInventoryValueSiteItem;

    private SiteItem highestUnitCostSiteItem;

    public Integer getUniqueDrugCount() {
        return uniqueDrugCount;
    }

    public InventorySummary uniqueDrugCount(Integer uniqueDrugCount) {
        this.uniqueDrugCount = uniqueDrugCount;
        return this;
    }

    public void setUniqueDrugCount(Integer uniqueDrugCount) {
        this.uniqueDrugCount = uniqueDrugCount;
    }

    public BigDecimal getTotalInventoryValue() {
        return totalInventoryValue;
    }

    public InventorySummary totalInventoryValue(BigDecimal totalInventoryValue) {
        this.totalInventoryValue = totalInventoryValue;
        return this;
    }

    public void setTotalInventoryValue(BigDecimal totalInventoryValue) {
        this.totalInventoryValue = totalInventoryValue;
    }

    public SiteItem getHighestInventoryUnitsSiteItem() {
        return highestInventoryUnitsSiteItem;
    }

    public InventorySummary highestInventoryUnitsSiteItem(SiteItem siteItem) {
        this.highestInventoryUnitsSiteItem = siteItem;
        return this;
    }

    public void setHighestInventoryUnitsSiteItem(SiteItem siteItem) {
        this.highestInventoryUnitsSiteItem = siteItem;
    }

    public SiteItem getHighestInventoryValueSiteItem() {
        return highestInventoryValueSiteItem;
    }

    public InventorySummary highestInventoryValueSiteItem(SiteItem siteItem) {
        this.highestInventoryValueSiteItem = siteItem;
        return this;
    }

    public void setHighestInventoryValueSiteItem(SiteItem siteItem) {
        this.highestInventoryValueSiteItem = siteItem;
    }

    public SiteItem getLowestUnitCostSiteItem() {
        return lowestUnitCostSiteItem;
    }

    public InventorySummary lowestUnitCostSiteItem(SiteItem siteItem) {
        this.lowestUnitCostSiteItem = siteItem;
        return this;
    }

    public void setLowestUnitCostSiteItem(SiteItem siteItem) {
        this.lowestUnitCostSiteItem = siteItem;
    }

    public SiteItem getLowestInventoryUnitsSiteItem() {
        return lowestInventoryUnitsSiteItem;
    }

    public InventorySummary lowestInventoryUnitsSiteItem(SiteItem siteItem) {
        this.lowestInventoryUnitsSiteItem = siteItem;
        return this;
    }

    public void setLowestInventoryUnitsSiteItem(SiteItem siteItem) {
        this.lowestInventoryUnitsSiteItem = siteItem;
    }

    public SiteItem getLowestInventoryValueSiteItem() {
        return lowestInventoryValueSiteItem;
    }

    public InventorySummary lowestInventoryValueSiteItem(SiteItem siteItem) {
        this.lowestInventoryValueSiteItem = siteItem;
        return this;
    }

    public void setLowestInventoryValueSiteItem(SiteItem siteItem) {
        this.lowestInventoryValueSiteItem = siteItem;
    }

    public SiteItem getHighestUnitCostSiteItem() {
        return highestUnitCostSiteItem;
    }

    public InventorySummary highestUnitCostSiteItem(SiteItem siteItem) {
        this.highestUnitCostSiteItem = siteItem;
        return this;
    }

    public void setHighestUnitCostSiteItem(SiteItem siteItem) {
        this.highestUnitCostSiteItem = siteItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InventorySummary)) {
            return false;
        }
        InventorySummary obj = (InventorySummary)o;
        return  (null !=uniqueDrugCount && null != obj.uniqueDrugCount) &&  uniqueDrugCount.equals(obj.uniqueDrugCount) 
        		&& (null != totalInventoryValue && null != obj.totalInventoryValue) && totalInventoryValue.equals(obj.totalInventoryValue)
        		&&  (null != highestInventoryUnitsSiteItem && null != obj.highestInventoryUnitsSiteItem ) && 
        		     highestInventoryUnitsSiteItem.equals(obj.highestInventoryUnitsSiteItem)
        		&&  ( null !=highestInventoryValueSiteItem && null != obj.highestInventoryValueSiteItem ) &&
        			highestInventoryValueSiteItem.equals(obj.highestInventoryValueSiteItem)
        		&&  (null != lowestUnitCostSiteItem && null !=obj.lowestUnitCostSiteItem ) &&
        			lowestUnitCostSiteItem.equals(obj.lowestUnitCostSiteItem)
        		&& (null != lowestInventoryUnitsSiteItem && null != obj.lowestInventoryUnitsSiteItem ) &&
        			lowestInventoryUnitsSiteItem.equals(obj.lowestInventoryUnitsSiteItem)
        		&& (null != lowestInventoryValueSiteItem && null != obj.lowestInventoryValueSiteItem) &&
        			lowestInventoryValueSiteItem.equals(obj.lowestInventoryValueSiteItem)
        		&&  (null != highestUnitCostSiteItem && null != obj.highestUnitCostSiteItem) &&
        			highestUnitCostSiteItem.equals(obj.highestUnitCostSiteItem);
                    
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "InventorySummary{" +
            
            ", uniqueDrugCount=" + getUniqueDrugCount() +
            ", totalInventoryValue=" + getTotalInventoryValue() +
            "}";
    }
}
