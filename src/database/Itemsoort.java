/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Bart Jansen
 */
@Entity
@Table(name = "itemsoort", catalog = "administration", schema = "")
@NamedQueries({
    @NamedQuery(name = "Itemsoort.findAll", query = "SELECT i FROM Itemsoort i"),
    @NamedQuery(name = "Itemsoort.findByItemsoortId", query = "SELECT i FROM Itemsoort i WHERE i.itemsoortId = :itemsoortId"),
    @NamedQuery(name = "Itemsoort.findByItemsoortItemsoort", query = "SELECT i FROM Itemsoort i WHERE i.itemsoortItemsoort = :itemsoortItemsoort"),
    @NamedQuery(name = "Itemsoort.findByItemsoortBeschrijving", query = "SELECT i FROM Itemsoort i WHERE i.itemsoortBeschrijving = :itemsoortBeschrijving"),
    @NamedQuery(name = "Itemsoort.findByItemsoortDateCreated", query = "SELECT i FROM Itemsoort i WHERE i.itemsoortDateCreated = :itemsoortDateCreated"),
    @NamedQuery(name = "Itemsoort.findByItemsoortDateModified", query = "SELECT i FROM Itemsoort i WHERE i.itemsoortDateModified = :itemsoortDateModified")})
public class Itemsoort implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "itemsoort_id")
    private Integer itemsoortId;
    @Column(name = "itemsoort_itemsoort")
    private String itemsoortItemsoort;
    @Column(name = "itemsoort_beschrijving")
    private String itemsoortBeschrijving;
    @Column(name = "itemsoort_date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date itemsoortDateCreated;
    @Column(name = "itemsoort_date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date itemsoortDateModified;
    @OneToMany(mappedBy="Itemsoort",targetEntity=Item.class,fetch=FetchType.LAZY)
    private Collection items; 
    @OneToMany(mappedBy="Itemsoort",targetEntity=Extension.class,fetch=FetchType.EAGER)
    private Collection extensions; 
    
    public Itemsoort() {
    }

    public Itemsoort(Integer itemsoortId) {
        this.itemsoortId = itemsoortId;
    }

    public Integer getItemsoortId() {
        return itemsoortId;
    }

    public void setItemsoortId(Integer itemsoortId) {
        Integer oldItemsoortId = this.itemsoortId;
        this.itemsoortId = itemsoortId;
        changeSupport.firePropertyChange("itemsoortId", oldItemsoortId, itemsoortId);
    }

    public String getItemsoortItemsoort() {
        return itemsoortItemsoort;
    }

    public void setItemsoortItemsoort(String itemsoortItemsoort) {
        String oldItemsoortItemsoort = this.itemsoortItemsoort;
        this.itemsoortItemsoort = itemsoortItemsoort;
        changeSupport.firePropertyChange("itemsoortItemsoort", oldItemsoortItemsoort, itemsoortItemsoort);
    }

    public String getItemsoortBeschrijving() {
        return itemsoortBeschrijving;
    }

    public void setItemsoortBeschrijving(String itemsoortBeschrijving) {
        String oldItemsoortBeschrijving = this.itemsoortBeschrijving;
        this.itemsoortBeschrijving = itemsoortBeschrijving;
        changeSupport.firePropertyChange("itemsoortBeschrijving", oldItemsoortBeschrijving, itemsoortBeschrijving);
    }

    public Date getItemsoortDateCreated() {
        return itemsoortDateCreated;
    }

    public void setItemsoortDateCreated(Date itemsoortDateCreated) {
        Date oldItemsoortDateCreated = this.itemsoortDateCreated;
        this.itemsoortDateCreated = itemsoortDateCreated;
        changeSupport.firePropertyChange("itemsoortDateCreated", oldItemsoortDateCreated, itemsoortDateCreated);
    }

    public Date getItemsoortDateModified() {
        return itemsoortDateModified;
    }

    public void setItemsoortDateModified(Date itemsoortDateModified) {
        Date oldItemsoortDateModified = this.itemsoortDateModified;
        this.itemsoortDateModified = itemsoortDateModified;
        changeSupport.firePropertyChange("itemsoortDateModified", oldItemsoortDateModified, itemsoortDateModified);
    }

    public Collection getItems() {
        return items;
    }

    public void setItems(Collection items) {
        this.items = items;
    }

    public Collection getExtensions() {
        return extensions;
    }

    public void setExtensions(Collection extensions) {
        this.extensions = extensions;
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemsoortId != null ? itemsoortId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itemsoort)) {
            return false;
        }
        Itemsoort other = (Itemsoort) object;
        if ((this.itemsoortId == null && other.itemsoortId != null) || (this.itemsoortId != null && !this.itemsoortId.equals(other.itemsoortId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return itemsoortBeschrijving + " (" + itemsoortItemsoort + ")"; 
        // return "admin.frames.Itemsoort[ itemsoortId=" + itemsoortId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
