package database;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "year", catalog = "administration", schema = "")
@NamedQueries({
    @NamedQuery(name = "Year.findAll", query = "SELECT y FROM Year y")})
public class Year implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hoofditem_id")
    private Integer hoofditemId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "year")
    private Integer year;

    public Year() {

    }

    public Integer getHoofditemId() {
        return hoofditemId;
    }

    public void setHoofditemId(Integer hoofditemId) {
        this.hoofditemId = hoofditemId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}
