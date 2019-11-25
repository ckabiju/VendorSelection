package com.ntt.hibernate.Entity;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Site.
 */
@Entity
@Table(name = "site")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Site implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "site_name")
    private String siteName;

    @Column(name = "npi")
    private String npi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public Site siteName(String siteName) {
        this.siteName = siteName;
        return this;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getNpi() {
        return npi;
    }

    public Site npi(String npi) {
        this.npi = npi;
        return this;
    }

    public void setNpi(String npi) {
        this.npi = npi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Site)) {
            return false;
        }
        Site obj = (Site) o;
        return  (null != npi && null != obj.npi) && npi.equals(obj.npi) &&
                (null != siteName && null !=obj.siteName) && siteName.equals(obj.siteName);
    }

    @Override
    public int hashCode() {
        int hashCode = 13;
        hashCode += 7*npi.hashCode();
        hashCode += 7*siteName.hashCode();
        return hashCode;
    }

    @Override
    public String toString() {
        return "Site{" +
                "id=" + getId() +
                ", siteName='" + getSiteName() + "'" +
                ", npi='" + getNpi() + "'" +
                "}";
    }
}
