package database;

import admin.PublicDefault.ItemStatus;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import javax.persistence.Transient;

@Entity
@Table(name = "item", catalog = "administration", schema = "")
@NamedQueries({
    @NamedQuery(name = "Item.findAll",
            query = "SELECT i FROM Item i")
    ,
    @NamedQuery(name = "Item.findAllOrdered",
            query = "SELECT i FROM Item i ORDER BY i.hoofditem.hoofditemBeschrijving, i.itemDatumDocument")
    ,
    @NamedQuery(name = "Item.findByItemId",
            query = "SELECT i FROM Item i WHERE i.itemId = :itemId")
    ,
    @NamedQuery(name = "Item.findByItemBeschrijving",
            query = "SELECT i FROM Item i WHERE i.itemBeschrijving = :itemBeschrijving")
    ,
    @NamedQuery(name = "Item.findByItemDatumDocument",
            query = "SELECT i FROM Item i WHERE i.itemDatumDocument = :itemDatumDocument")
    ,
    @NamedQuery(name = "Item.findByItemLocatie",
            query = "SELECT i FROM Item i WHERE i.itemLocatie = :itemLocatie")
    ,
    @NamedQuery(name = "Item.findByItemDateCreated",
            query = "SELECT i FROM Item i WHERE i.itemDateCreated = :itemDateCreated")
    ,
    @NamedQuery(name = "Item.findByItemHoofditemId",
            query = "SELECT i FROM Item i "
            + "WHERE i.hoofditem.hoofditemId =  :hoofditemId "
            + "AND (i.itemstatus IN :itemStatus "
            + "OR (:statusLeeg IS NOT NULL AND i.itemstatus IS NULL)) "
            + "ORDER BY i.itemDatumDocument")
    ,
    @NamedQuery(name = "Item.findItemsByYear",
            query = "SELECT i FROM Item i "
            + "WHERE i.hoofditem.hoofditemId = :hoofditemId "
            + "AND i.itemYearDatumDocument = :year "
            + "AND (i.itemstatus IN :itemStatus "
            + "OR (:statusLeeg IS NOT NULL AND i.itemstatus IS NULL)) "
            + "ORDER BY i.itemDatumDocument")
    ,
    @NamedQuery(name = "Item.findItemsByMonth",
            query = "SELECT i FROM Item i "
            + "WHERE i.hoofditem.hoofditemId = :hoofditemId "
            + "AND i.itemYearDatumDocument = :year "
            + "AND i.itemMonthDatumDocument = :month "
            + "AND (i.itemstatus IN :itemStatus "
            + "OR (:statusLeeg IS NOT NULL AND i.itemstatus IS NULL)) "
            + "ORDER BY i.itemDatumDocument")
    ,
    @NamedQuery(name = "Item.findByItemYear",
            query = "SELECT DISTINCT(i.itemYearDatumDocument) "
            + "FROM Item i "
            + "WHERE i.hoofditem.hoofditemId = :hoofditemId "
            + "ORDER BY i.itemYearDatumDocument")
    ,
    @NamedQuery(name = "Item.findMonthsByYear",
            query = "SELECT DISTINCT(i.itemMonthDatumDocument) "
            + "FROM Item i "
            + "WHERE i.hoofditem.hoofditemId = :hoofditemId and i.itemYearDatumDocument = :year "
            + "ORDER BY i.itemMonthDatumDocument")
    ,
    @NamedQuery(name = "Item.findByItemHoofditemNaam", query = "SELECT i FROM Item i WHERE i.hoofditem.hoofditemNaam = :hoofditemnaam")
    ,
    @NamedQuery(name = "Item.findByItemDateModified", query = "SELECT i FROM Item i WHERE i.itemDateModified = :itemDateModified")
    ,
    @NamedQuery(name = "Item.findByItemLikeBeschrijving", query = "SELECT i FROM Item i WHERE i.itemBeschrijving like :itemBeschrijving")
    ,
    @NamedQuery(name = "Item.findItemWithFilter",
            query = "SELECT i FROM Item i "
            + "WHERE (:itemBeschrijving IS NULL OR :itemBeschrijving = '' or i.itemBeschrijving LIKE :itemBeschrijving) "
            + "AND (:itemLocatie IS NULL OR :itemLocatie = '' OR i.itemLocatie LIKE :itemLocatie) "
            + "AND (:hoofdItem IS NULL OR i.hoofditem = :hoofdItem) "
            + "AND (:itemSoort IS NULL OR i.itemsoort = :itemSoort) "
            + "AND (:dateFrom IS NULL OR i.itemDatumDocument >= :dateFrom) "
            + "AND (:dateTo IS NULL OR i.itemDatumDocument <= :dateTo) "
            + "AND (:itemCode IS NULL OR i.itemcode = :itemCode) "
            + "AND (:itemStatus IS NULL OR i.itemstatus = :itemStatus)")
    ,
    @NamedQuery(name = "Item.findAllFilter",
            query = "SELECT i FROM Item i "
            + "WHERE (i.itemstatus IN :itemStatus "
            + "OR (:statusLeeg IS NOT NULL AND i.itemstatus IS NULL))"
            + "ORDER BY i.hoofditem.hoofditemBeschrijving, i.itemDatumDocument"),})

public class Item implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "item_id")
    private Integer itemId;
    @Column(name = "item_beschrijving")
    private String itemBeschrijving;
    @Column(name = "item_datum_document")
    @Temporal(TemporalType.TIMESTAMP)
    private Date itemDatumDocument;
    @Column(name = "item_locatie")
    private String itemLocatie;
    @Column(name = "item_date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date itemDateCreated;
    @Column(name = "item_date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date itemDateModified;
    @ManyToOne(optional = false)
    @JoinColumn(name = "hoofditem_id", referencedColumnName = "hoofditem_id")
    private Hoofditem hoofditem;
    @ManyToOne(optional = false)
    @JoinColumn(name = "itemsoort_id", referencedColumnName = "itemsoort_id")
    private Itemsoort itemsoort;
    @ManyToOne(optional = false)
    @JoinColumn(name = "itemcode_id", referencedColumnName = "itemcode_id")
    private Itemcode itemcode;
    @Column(name = "item_status")
    @Enumerated(EnumType.STRING)
    private ItemStatus itemstatus;
    @Column(name = "item_year_datum_document")
    private Integer itemYearDatumDocument;
    @Column(name = "item_month_datum_document")
    private Integer itemMonthDatumDocument;

    public Item() {
    }

    public Item(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        Integer oldItemId = this.itemId;
        this.itemId = itemId;
        changeSupport.firePropertyChange("itemId", oldItemId, itemId);
    }

    /**
     * Bijvoorbeeld: Word, PDF Plaatje...
     *
     * @return
     */
    public Itemsoort getItemsoort() {
        return itemsoort;
    }

    public void setItemsoort(Itemsoort itemsoort) {
        this.itemsoort = itemsoort;
    }

    public String getItemBeschrijving() {
        return itemBeschrijving;
    }

    public void setItemBeschrijving(String itemBeschrijving) {
        String oldItemBeschrijving = this.itemBeschrijving;
        this.itemBeschrijving = itemBeschrijving;
        changeSupport.firePropertyChange("itemBeschrijving", oldItemBeschrijving, itemBeschrijving);
    }

    public Date getItemDatumDocument() {
        return itemDatumDocument;
    }

    public void setItemDatumDocument(Date itemDatumDocument) {
        Date oldItemDatumDocument = this.itemDatumDocument;
        this.itemDatumDocument = itemDatumDocument;
        changeSupport.firePropertyChange("itemDatumDocument", oldItemDatumDocument, itemDatumDocument);
    }

    public String getItemLocatie() {
        return itemLocatie;
    }

    public void setItemLocatie(String itemLocatie) {
        String oldItemLocatie = this.itemLocatie;
        this.itemLocatie = itemLocatie;
        changeSupport.firePropertyChange("itemLocatie", oldItemLocatie, itemLocatie);
    }

    public Date getItemDateCreated() {
        return itemDateCreated;
    }

    public void setItemDateCreated(Date itemDateCreated) {
        Date oldItemDateCreated = this.itemDateCreated;
        this.itemDateCreated = itemDateCreated;
        changeSupport.firePropertyChange("itemDateCreated", oldItemDateCreated, itemDateCreated);
    }

    public Date getItemDateModified() {
        return itemDateModified;
    }

    public void setItemDateModified(Date itemDateModified) {
        Date oldItemDateModified = this.itemDateModified;
        this.itemDateModified = itemDateModified;
        changeSupport.firePropertyChange("itemDateModified", oldItemDateModified, itemDateModified);
    }

    public Hoofditem getHoofditem() {
        return hoofditem;
    }

    public void setHoofditem(Hoofditem hoofditem) {
        this.hoofditem = hoofditem;
    }

    /**
     * Itemcode is bijvoorbeeld: Factuur, Loonstrook, Garantiebewijs
     *
     * @return
     */
    public Itemcode getItemcode() {
        return itemcode;
    }

    public void setItemcode(Itemcode itemcode) {
        this.itemcode = itemcode;
    }

    /**
     * Waarden: Archief, Belangrijk, Onbelangrijk, Bijzonder, Verwijderd
     *
     * @return
     */
    public ItemStatus getItemstatus() {
        return itemstatus;
    }

    public void setItemstatus(ItemStatus itemstatus) {
        this.itemstatus = itemstatus;
    }

    public Integer getItemYearDatumDocument() {
        return itemYearDatumDocument;
    }

    public void setItemYearDatumDocument(Integer itemYearDatumDocument) {
        this.itemYearDatumDocument = itemYearDatumDocument;
    }

    public Integer getItemMonthDatumDocument() {
        return itemMonthDatumDocument;
    }

    public void setItemMonthDatumDocument(Integer itemMonthDatumDocument) {
        this.itemMonthDatumDocument = itemMonthDatumDocument;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemId != null ? itemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.itemId == null && other.itemId != null) || (this.itemId != null && !this.itemId.equals(other.itemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "admin.frames.Item[ itemId=" + itemId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
