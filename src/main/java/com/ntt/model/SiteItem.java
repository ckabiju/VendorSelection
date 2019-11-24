package com.ntt.model;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A SiteItem.
 */

public class SiteItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String ndc;

    private Integer totalUnits;


    private BigDecimal unitCost;

    
    private BigDecimal totalValue;

   

    public String getNdc() {
        return ndc;
    }

    public SiteItem ndc(String ndc) {
        this.ndc = ndc;
        return this;
    }

    public void setNdc(String ndc) {
        this.ndc = ndc;
    }

    public Integer getTotalUnits() {
        return totalUnits;
    }

    public SiteItem totalUnits(Integer totalUnits) {
        this.totalUnits = totalUnits;
        return this;
    }

    public void setTotalUnits(Integer totalUnits) {
        this.totalUnits = totalUnits;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public SiteItem unitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
        return this;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public SiteItem totalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
        return this;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SiteItem)) {
            return false;
        }
        SiteItem obj = (SiteItem) o;
        return  (null != ndc && null != obj.ndc) && ndc.contentEquals(obj.ndc) &&
        		(null != totalUnits  && null != obj.totalUnits) && totalUnits.equals(obj.totalUnits) &&
        		(null != unitCost && null != obj.unitCost) && unitCost.equals(obj.unitCost) &&
        		(null != totalValue && null != obj.totalValue) &&totalValue.equals(obj.totalValue);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SiteItem{" +
            
            ", ndc='" + getNdc() + "'" +
            ", totalUnits=" + getTotalUnits() +
            ", unitCost=" + getUnitCost() +
            ", totalValue=" + getTotalValue() +
            "}";
    }
}
