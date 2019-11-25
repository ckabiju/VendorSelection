package com.ntt.model;

import java.io.Serializable;
import com.ntt.hibernate.Entity.Site;

/**
 * A DriverSite.
 */

public class DriverSite implements Serializable {

    private static final long serialVersionUID = 1L;
    private Site site;
    private InventorySummary inventorySummary;

    public Site getSite() {
        return site;
    }

    public DriverSite site(Site site) {
        this.site = site;
        return this;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public InventorySummary getInventorySummary() {
        return inventorySummary;
    }

    public void setInventorySummary(InventorySummary inventorySummary) {
        this.inventorySummary = inventorySummary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DriverSite)) {
            return false;
        }
        DriverSite obj= (DriverSite)o;
        return (null != site && null != obj.site) && site.equals(obj.site) &&
        		(null != inventorySummary && null != obj.inventorySummary) && inventorySummary.equals(obj.inventorySummary);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DriverSite{" +
            
            "}";
    }
}
