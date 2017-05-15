/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bart Jansen
 */
@Entity
@Table(name = "extension")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Extension.findAll", query = "SELECT e FROM Extension e"),
    @NamedQuery(name = "Extension.findByExtensionId", query = "SELECT e FROM Extension e WHERE e.extensionId = :extensionId"),    
    @NamedQuery(name = "Extension.findByExtensionCode", query = "SELECT e FROM Extension e WHERE e.extensionCode = :extensionCode"),
    @NamedQuery(name = "Extension.findByExtensionDescription", query = "SELECT e FROM Extension e WHERE e.extensionDescription = :extensionDescription"),
    @NamedQuery(name = "Extension.findByExtensionDateCreated", query = "SELECT e FROM Extension e WHERE e.extensionDateCreated = :extensionDateCreated"),
    @NamedQuery(name = "Extension.findByItemsoortId", query = "SELECT e FROM Extension e WHERE e.itemsoort.itemsoortId = :itemsoortid"),
    @NamedQuery(name = "Extension.findByExtensionDateModified", query = "SELECT e FROM Extension e WHERE e.extensionDateModified = :extensionDateModified")})
public class Extension implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "extension_id")
    private Integer extensionId;
    @Column(name = "extension_code")
    private String extensionCode;
    @Column(name = "extension_description")
    private String extensionDescription;
    @Column(name = "extension_date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date extensionDateCreated;
    @Column(name = "extension_date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date extensionDateModified;
    @ManyToOne(optional=false)
    @JoinColumn(name="itemsoort_id",referencedColumnName="itemsoort_id")
    private Itemsoort itemsoort;
    
    public Extension() {
    }

    public Extension(Integer extensionId) {
        this.extensionId = extensionId;
    }

    public Integer getExtensionId() {
        return extensionId;
    }

    public void setExtensionId(Integer extensionId) {
        this.extensionId = extensionId;
    }

    public String getExtensionCode() {
        return extensionCode;
    }

    public void setExtensionCode(String extensionCode) {
        this.extensionCode = extensionCode;
    }

    public String getExtensionDescription() {
        return extensionDescription;
    }

    public void setExtensionDescription(String extensionDescription) {
        this.extensionDescription = extensionDescription;
    }

    public Date getExtensionDateCreated() {
        return extensionDateCreated;
    }

    public void setExtensionDateCreated(Date extensionDateCreated) {
        this.extensionDateCreated = extensionDateCreated;
    }

    public Date getExtensionDateModified() {
        return extensionDateModified;
    }

    public void setExtensionDateModified(Date extensionDateModified) {
        this.extensionDateModified = extensionDateModified;
    }

    public Itemsoort getItemsoort() {
        return itemsoort;
    }

    public void setItemsoort(Itemsoort itemsoort) {
        this.itemsoort = itemsoort;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (extensionId != null ? extensionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Extension)) {
            return false;
        }
        Extension other = (Extension) object;
        if ((this.extensionId == null && other.extensionId != null) || (this.extensionId != null && !this.extensionId.equals(other.extensionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.Extension[ extensionId=" + extensionId + " ]";
    }
    
}
