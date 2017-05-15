package admin;

import admin.PublicDefault.ItemStatus;
import database.Hoofditem;
import database.Itemcode;
import database.Itemsoort;
import java.util.Date;

public class Filter {

    private Hoofditem hoofditem;
    private Itemsoort itemsoort;
    private Itemcode itemcode;
    private String itemBeschrijving;
    private String itemLocatie;
    private Date dateFrom;
    private Date dateTo;
    private ItemStatus itemStatus;
    
    public Hoofditem getHoofditem() {
        return hoofditem;
    }

    public void setHoofditem(Hoofditem hoofditem) {
        this.hoofditem = hoofditem;
    }

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
        this.itemBeschrijving = itemBeschrijving;
    }

    public String getItemLocatie() {
        return itemLocatie;
    }

    public void setItemLocatie(String itemLocatie) {
        this.itemLocatie = itemLocatie;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Itemcode getItemcode() {
        return itemcode;
    }

    public void setItemcode(Itemcode itemcode) {
        this.itemcode = itemcode;
    }

    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(ItemStatus itemStatus) {
        this.itemStatus = itemStatus;
    }

    
    
}
