package com.ntt.hibernate.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * A StockDetails.
 */
@Entity
@Table(name = "stock_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StockDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "npi")
    private String npi;

    @Column(name = "ndc")
    private String ndc;

    @Column(name = "units")
    private String units;

    @Column(name = "units_cost")
    private String unitsCost;

    @Type(type = "uuid-char")
    @Column(name = "guid", length = 36)
    private UUID guid;

    @Column(name = "site_name")
    private String siteName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNpi() {
        return npi;
    }

    public StockDetails npi(String npi) {
        this.npi = npi;
        return this;
    }

    public void setNpi(String npi) {
        this.npi = npi;
    }

    public String getNdc() {
        return ndc;
    }

    public StockDetails ndc(String ndc) {
        this.ndc = ndc;
        return this;
    }

    public void setNdc(String ndc) {
        this.ndc = ndc;
    }

    public String getUnits() {
        return units;
    }

    public StockDetails units(String units) {
        this.units = units;
        return this;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getUnitsCost() {
        return unitsCost;
    }

    public StockDetails unitsCost(String unitsCost) {
        this.unitsCost = unitsCost;
        return this;
    }

    public void setUnitsCost(String unitsCost) {
        this.unitsCost = unitsCost;
    }

    public UUID getGuid() {
        return guid;
    }

    public StockDetails guid(UUID guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public String getSiteName() {
        return siteName;
    }

    public StockDetails siteName(String siteName) {
        this.siteName = siteName;
        return this;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StockDetails)) {
            return false;
        }
        StockDetails obj = (StockDetails) o;
        return  (id != null && id.equals(obj.id) )&&
                (null != ndc && null != obj.ndc) && ndc.equals(obj.ndc) &&
                (null != unitsCost && null != obj.unitsCost) && unitsCost.equals(obj.unitsCost) &&
                (null != units && null != obj.units) && units.equals(obj.units);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "StockDetails{" +
            "id=" + getId() +
            ", npi='" + getNpi() + "'" +
            ", ndc='" + getNdc() + "'" +
            ", units='" + getUnits() + "'" +
            ", unitsCost='" + getUnitsCost() + "'" +
            ", guid='" + getGuid() + "'" +
            ", siteName='" + getSiteName() + "'" +
            "}";
    }
}
