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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bart Jansen
 */
@Entity
@Table(name = "itemcode")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itemcode.findAll", query = "SELECT i FROM Itemcode i"),
    @NamedQuery(name = "Itemcode.findByItemcodeId", query = "SELECT i FROM Itemcode i WHERE i.itemcodeId = :itemcodeId"),
    @NamedQuery(name = "Itemcode.findByItemcodeCode", query = "SELECT i FROM Itemcode i WHERE i.itemcodeCode = :itemcodeCode"),
    @NamedQuery(name = "Itemcode.findByItemcodeBeschrijving", query = "SELECT i FROM Itemcode i WHERE i.itemcodeBeschrijving = :itemcodeBeschrijving"),
    @NamedQuery(name = "Itemcode.findByItemcodeDateCreated", query = "SELECT i FROM Itemcode i WHERE i.itemcodeDateCreated = :itemcodeDateCreated"),
    @NamedQuery(name = "Itemcode.findByItemcodeDateModified", query = "SELECT i FROM Itemcode i WHERE i.itemcodeDateModified = :itemcodeDateModified")})
public class Itemcode implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "itemcode_id")
    private Integer itemcodeId;
    @Column(name = "itemcode_code")
    private String itemcodeCode;
    @Column(name = "itemcode_beschrijving")
    private String itemcodeBeschrijving;
    @Column(name = "itemcode_date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date itemcodeDateCreated;
    @Column(name = "itemcode_date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date itemcodeDateModified;
    @OneToMany(mappedBy="itemcode",targetEntity=Item.class,fetch=FetchType.LAZY)
    private Collection items; 
    
    
    public Itemcode() {
    }

    public Itemcode(Integer itemcodeId) {
        this.itemcodeId = itemcodeId;
    }

    public Integer getItemcodeId() {
        return itemcodeId;
    }

    public void setItemcodeId(Integer itemcodeId) {
        Integer oldItemcodeId = this.itemcodeId;
        this.itemcodeId = itemcodeId;
        changeSupport.firePropertyChange("itemcodeId", oldItemcodeId, itemcodeId);
    }

    public String getItemcodeCode() {
        return itemcodeCode;
    }

    public void setItemcodeCode(String itemcodeCode) {
        String oldItemcodeCode = this.itemcodeCode;
        this.itemcodeCode = itemcodeCode;
        changeSupport.firePropertyChange("itemcodeCode", oldItemcodeCode, itemcodeCode);
    }

    public String getItemcodeBeschrijving() {
        return itemcodeBeschrijving;
    }

    public void setItemcodeBeschrijving(String itemcodeBeschrijving) {
        String oldItemcodeBeschrijving = this.itemcodeBeschrijving;
        this.itemcodeBeschrijving = itemcodeBeschrijving;
        changeSupport.firePropertyChange("itemcodeBeschrijving", oldItemcodeBeschrijving, itemcodeBeschrijving);
    }

    public Date getItemcodeDateCreated() {
        return itemcodeDateCreated;
    }

    public void setItemcodeDateCreated(Date itemcodeDateCreated) {
        Date oldItemcodeDateCreated = this.itemcodeDateCreated;
        this.itemcodeDateCreated = itemcodeDateCreated;
        changeSupport.firePropertyChange("itemcodeDateCreated", oldItemcodeDateCreated, itemcodeDateCreated);
    }

    public Date getItemcodeDateModified() {
        return itemcodeDateModified;
    }

    public void setItemcodeDateModified(Date itemcodeDateModified) {
        Date oldItemcodeDateModified = this.itemcodeDateModified;
        this.itemcodeDateModified = itemcodeDateModified;
        changeSupport.firePropertyChange("itemcodeDateModified", oldItemcodeDateModified, itemcodeDateModified);
    }

    public Collection getItems() {
        return items;
    }

    public void setItems(Collection items) {
        this.items = items;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemcodeId != null ? itemcodeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itemcode)) {
            return false;
        }
        Itemcode other = (Itemcode) object;
        if ((this.itemcodeId == null && other.itemcodeId != null) || (this.itemcodeId != null && !this.itemcodeId.equals(other.itemcodeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return itemcodeCode + " (" + itemcodeBeschrijving + ")";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
