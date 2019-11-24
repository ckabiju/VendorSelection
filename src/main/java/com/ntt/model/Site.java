package com.ntt.model;

import java.io.Serializable;

/**
 * A Site.
 */

public class Site implements Serializable {

    private static final long serialVersionUID = 1L;

    
    private String npi;
    private String siteName;

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
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

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
        return 31;
    }

    @Override
    public String toString() {
        return "Site{" +
            ", npi='" + getNpi() + "'" +
            ", siteName='" + getSiteName() + "'" +
            "}";
    }
}
