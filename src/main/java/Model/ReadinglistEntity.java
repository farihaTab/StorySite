package Model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;

/**
 * Created by DELL on 5/13/2017.
 */
@Entity
@Table(name = "READINGLIST", schema = "SDPROJECT", catalog = "")
public class ReadinglistEntity {
    private long listid;
    private String listtitle;
    private String listowner;
    private Boolean isprivate;
    private Time createdat;
    private Time updatedat;
    private AccountuserEntity accountuserByListowner;
    private Collection<ReadinglistdetailsEntity> readinglistdetailssByListid;

    @Id
    @Column(name = "LISTID", nullable = false, precision = 0)
    public long getListid() {
        return listid;
    }

    public void setListid(long listid) {
        this.listid = listid;
    }

    @Basic
    @Column(name = "LISTTITLE", nullable = true, length = 100)
    public String getListtitle() {
        return listtitle;
    }

    public void setListtitle(String listtitle) {
        this.listtitle = listtitle;
    }

    @Basic
    @Column(name = "LISTOWNER", nullable = true, length = 20)
    public String getListowner() {
        return listowner;
    }

    public void setListowner(String listowner) {
        this.listowner = listowner;
    }

    @Basic
    @Column(name = "ISPRIVATE", nullable = true, precision = 0)
    public Boolean getIsprivate() {
        return isprivate;
    }

    public void setIsprivate(Boolean isprivate) {
        this.isprivate = isprivate;
    }

    @Basic
    @Column(name = "CREATEDAT", nullable = true)
    public Time getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Time createdat) {
        this.createdat = createdat;
    }

    @Basic
    @Column(name = "UPDATEDAT", nullable = true)
    public Time getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(Time updatedat) {
        this.updatedat = updatedat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReadinglistEntity that = (ReadinglistEntity) o;

        if (listid != that.listid) return false;
        if (listtitle != null ? !listtitle.equals(that.listtitle) : that.listtitle != null) return false;
        if (listowner != null ? !listowner.equals(that.listowner) : that.listowner != null) return false;
        if (createdat != null ? !createdat.equals(that.createdat) : that.createdat != null) return false;
        if (updatedat != null ? !updatedat.equals(that.updatedat) : that.updatedat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (listid ^ (listid >>> 32));
        result = 31 * result + (listtitle != null ? listtitle.hashCode() : 0);
        result = 31 * result + (listowner != null ? listowner.hashCode() : 0);
        result = 31 * result + (createdat != null ? createdat.hashCode() : 0);
        result = 31 * result + (updatedat != null ? updatedat.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "LISTOWNER", referencedColumnName = "USERNAME", insertable = false, updatable = false)
    public AccountuserEntity getAccountuserByListowner() {
        return accountuserByListowner;
    }

    public void setAccountuserByListowner(AccountuserEntity accountuserByListowner) {
        this.accountuserByListowner = accountuserByListowner;
    }

    @OneToMany(mappedBy = "readinglistByList")
    public Collection<ReadinglistdetailsEntity> getReadinglistdetailssByListid() {
        return readinglistdetailssByListid;
    }

    public void setReadinglistdetailssByListid(Collection<ReadinglistdetailsEntity> readinglistdetailssByListid) {
        this.readinglistdetailssByListid = readinglistdetailssByListid;
    }
}
