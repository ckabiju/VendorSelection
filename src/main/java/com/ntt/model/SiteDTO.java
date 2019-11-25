package com.ntt.model;
import  java.io.Serializable;

public class SiteDTO implements  Serializable {

    private String siteName;
    private String npi;
    public String getSiteName() {
        return siteName;
    }
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getNpi() {
        return npi;
    }
    public void setNpi(String npi) {
        this.npi = npi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SiteDTO)) {
            return false;
        }
        SiteDTO obj = (SiteDTO) o;
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
                ", siteName='" + getSiteName() + "'" +
                ", npi='" + getNpi() + "'" +
                "}";
    }
}
