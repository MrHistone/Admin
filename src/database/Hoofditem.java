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

@Entity
@Table(name = "hoofditem", catalog = "administration", schema = "")
@NamedQueries({
    @NamedQuery(name = "Hoofditem.findAll", query = "SELECT h FROM Hoofditem h"),
    @NamedQuery(name = "Hoofditem.findAllOrdered", query = "SELECT h FROM Hoofditem h ORDER BY h.hoofditemNaam"),
    @NamedQuery(name = "Hoofditem.findByHoofditemId", query = "SELECT h FROM Hoofditem h WHERE h.hoofditemId = :hoofditemId"),
    @NamedQuery(name = "Hoofditem.findByHoofditemNaam", query = "SELECT h FROM Hoofditem h WHERE h.hoofditemNaam = :hoofditemNaam"),
    @NamedQuery(name = "Hoofditem.findByHoofditemBeschrijving", query = "SELECT h FROM Hoofditem h WHERE h.hoofditemBeschrijving = :hoofditemBeschrijving"),
    @NamedQuery(name = "Hoofditem.findByHoofditemLokatieOpNetwerk", query = "SELECT h FROM Hoofditem h WHERE h.hoofditemLokatieOpNetwerk = :hoofditemLokatieOpNetwerk"),
    @NamedQuery(name = "Hoofditem.findByHoofditemDateCreated", query = "SELECT h FROM Hoofditem h WHERE h.hoofditemDateCreated = :hoofditemDateCreated"),
    @NamedQuery(name = "Hoofditem.findByHoofditemDateModified", query = "SELECT h FROM Hoofditem h WHERE h.hoofditemDateModified = :hoofditemDateModified")})
public class Hoofditem implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hoofditem_id")
    private Integer hoofditemId;
    @Basic(optional = false)
    @Column(name = "hoofditem_naam")
    private String hoofditemNaam;
    @Column(name = "hoofditem_beschrijving")
    private String hoofditemBeschrijving;
    @Column(name = "hoofditem_lokatie_op_netwerk")
    private String hoofditemLokatieOpNetwerk;
    @Column(name = "hoofditem_date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hoofditemDateCreated;
    @Column(name = "hoofditem_date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hoofditemDateModified;
    @OneToMany(mappedBy="hoofditem",targetEntity=Item.class,fetch=FetchType.LAZY)
    private Collection items; 
    
    public Hoofditem() {
    }

    public Hoofditem(Integer hoofditemId) {
        this.hoofditemId = hoofditemId;
    }

    public Hoofditem(Integer hoofditemId, String hoofditemNaam) {
        this.hoofditemId = hoofditemId;
        this.hoofditemNaam = hoofditemNaam;
    }

    public Collection getItems() {
        return items;
    }

    public void setItems(Collection items) {
        this.items = items;
    }

    
    
    
    public Integer getHoofditemId() {
        return hoofditemId;
    }

    public void setHoofditemId(Integer hoofditemId) {
        Integer oldHoofditemId = this.hoofditemId;
        this.hoofditemId = hoofditemId;
        changeSupport.firePropertyChange("hoofditemId", oldHoofditemId, hoofditemId);
    }

    public String getHoofditemNaam() {
        return hoofditemNaam;
    }

    public void setHoofditemNaam(String hoofditemNaam) {
        String oldHoofditemNaam = this.hoofditemNaam;
        this.hoofditemNaam = hoofditemNaam;
        changeSupport.firePropertyChange("hoofditemNaam", oldHoofditemNaam, hoofditemNaam);
    }

    public String getHoofditemBeschrijving() {
        return hoofditemBeschrijving;
    }

    public void setHoofditemBeschrijving(String hoofditemBeschrijving) {
        String oldHoofditemBeschrijving = this.hoofditemBeschrijving;
        this.hoofditemBeschrijving = hoofditemBeschrijving;
        changeSupport.firePropertyChange("hoofditemBeschrijving", oldHoofditemBeschrijving, hoofditemBeschrijving);
    }

    public String getHoofditemLokatieOpNetwerk() {
        return hoofditemLokatieOpNetwerk;
    }

    public void setHoofditemLokatieOpNetwerk(String hoofditemLokatieOpNetwerk) {
        String oldHoofditemLokatieOpNetwerk = this.hoofditemLokatieOpNetwerk;
        this.hoofditemLokatieOpNetwerk = hoofditemLokatieOpNetwerk;
        changeSupport.firePropertyChange("hoofditemLokatieOpNetwerk", oldHoofditemLokatieOpNetwerk, hoofditemLokatieOpNetwerk);
    }

    public Date getHoofditemDateCreated() {
        return hoofditemDateCreated;
    }

    public void setHoofditemDateCreated(Date hoofditemDateCreated) {
        Date oldHoofditemDateCreated = this.hoofditemDateCreated;
        this.hoofditemDateCreated = hoofditemDateCreated;
        changeSupport.firePropertyChange("hoofditemDateCreated", oldHoofditemDateCreated, hoofditemDateCreated);
    }

    public Date getHoofditemDateModified() {
        return hoofditemDateModified;
    }

    public void setHoofditemDateModified(Date hoofditemDateModified) {
        Date oldHoofditemDateModified = this.hoofditemDateModified;
        this.hoofditemDateModified = hoofditemDateModified;
        changeSupport.firePropertyChange("hoofditemDateModified", oldHoofditemDateModified, hoofditemDateModified);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hoofditemId != null ? hoofditemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hoofditem)) {
            return false;
        }
        Hoofditem other = (Hoofditem) object;
        if ((this.hoofditemId == null && other.hoofditemId != null) || (this.hoofditemId != null && !this.hoofditemId.equals(other.hoofditemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return hoofditemBeschrijving + " (" + hoofditemNaam + ")" ;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
