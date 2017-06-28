package Model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by DELL on 5/13/2017.
 */
@Entity
@Table(name = "CHAPTERCOMMENTS", schema = "SDPROJECT", catalog = "")
public class ChaptercommentsEntity {
    private long ccommentid;
    private String ccommenttext;
    private Long ccommentedonstory;
    private Long ccommentedonchapter;
    private String ccommentedby;
    private Time ccommentedat;
    private String scommenttype;
    private ChapterEntity chapter;
    private AccountuserEntity accountuserByCcommentedby;

    @Override
    public String toString() {
        return "ChaptercommentsEntity{" +
                "ccommentid=" + ccommentid +
                ", ccommenttext='" + ccommenttext + '\'' +
                ", ccommentedonstory=" + ccommentedonstory +
                ", ccommentedonchapter=" + ccommentedonchapter +
                ", ccommentedby='" + ccommentedby + '\'' +
                ", ccommentedat=" + ccommentedat +
                ", scommenttype='" + scommenttype + '\'' +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ID_GENERATOR")
    @SequenceGenerator(name = "ID_GENERATOR", sequenceName = "SEQ_CHAPTER_COMMENT")
    @Column(name = "CCOMMENTID", nullable = false, precision = 0)
    public long getCcommentid() {
        return ccommentid;
    }

    public void setCcommentid(long ccommentid) {
        this.ccommentid = ccommentid;
    }

    @Basic
    @Column(name = "CCOMMENTTEXT", nullable = true, length = 4000)
    public String getCcommenttext() {
        return ccommenttext;
    }

    public void setCcommenttext(String ccommenttext) {
        this.ccommenttext = ccommenttext;
    }

    @Basic
    @Column(name = "CCOMMENTEDONSTORY", nullable = true, precision = 0)
    public Long getCcommentedonstory() {
        return ccommentedonstory;
    }

    public void setCcommentedonstory(Long ccommentedonstory) {
        this.ccommentedonstory = ccommentedonstory;
    }

    @Basic
    @Column(name = "CCOMMENTEDONCHAPTER", nullable = true, precision = 0)
    public Long getCcommentedonchapter() {
        return ccommentedonchapter;
    }

    public void setCcommentedonchapter(Long ccommentedonchapter) {
        this.ccommentedonchapter = ccommentedonchapter;
    }

    @Basic
    @Column(name = "CCOMMENTEDBY", nullable = true, length = 20)
    public String getCcommentedby() {
        return ccommentedby;
    }

    public void setCcommentedby(String ccommentedby) {
        this.ccommentedby = ccommentedby;
    }

    @Basic
    @Column(name = "CCOMMENTEDAT", nullable = true)
    public Time getCcommentedat() {
        return ccommentedat;
    }

    public void setCcommentedat(Time ccommentedat) {
        this.ccommentedat = ccommentedat;
    }

    @Basic
    @Column(name = "SCOMMENTTYPE", nullable = true, length = 20)
    public String getScommenttype() {
        return scommenttype;
    }

    public void setScommenttype(String scommenttype) {
        this.scommenttype = scommenttype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChaptercommentsEntity that = (ChaptercommentsEntity) o;

        if (ccommentid != that.ccommentid) return false;
        if (ccommenttext != null ? !ccommenttext.equals(that.ccommenttext) : that.ccommenttext != null) return false;
        if (ccommentedonstory != null ? !ccommentedonstory.equals(that.ccommentedonstory) : that.ccommentedonstory != null)
            return false;
        if (ccommentedonchapter != null ? !ccommentedonchapter.equals(that.ccommentedonchapter) : that.ccommentedonchapter != null)
            return false;
        if (ccommentedby != null ? !ccommentedby.equals(that.ccommentedby) : that.ccommentedby != null) return false;
        if (ccommentedat != null ? !ccommentedat.equals(that.ccommentedat) : that.ccommentedat != null) return false;
        if (scommenttype != null ? !scommenttype.equals(that.scommenttype) : that.scommenttype != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (ccommentid ^ (ccommentid >>> 32));
        result = 31 * result + (ccommenttext != null ? ccommenttext.hashCode() : 0);
        result = 31 * result + (ccommentedonstory != null ? ccommentedonstory.hashCode() : 0);
        result = 31 * result + (ccommentedonchapter != null ? ccommentedonchapter.hashCode() : 0);
        result = 31 * result + (ccommentedby != null ? ccommentedby.hashCode() : 0);
        result = 31 * result + (ccommentedat != null ? ccommentedat.hashCode() : 0);
        result = 31 * result + (scommenttype != null ? scommenttype.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "CCOMMENTEDONCHAPTER", referencedColumnName = "CHAPTERID", insertable = false, updatable = false), @JoinColumn(name = "CCOMMENTEDONSTORY", referencedColumnName = "BELONGSTO", insertable = false, updatable = false)})
    public ChapterEntity getChapter() {
        return chapter;
    }

    public void setChapter(ChapterEntity chapter) {
        this.chapter = chapter;
    }

    @ManyToOne
    @JoinColumn(name = "CCOMMENTEDBY", referencedColumnName = "USERNAME", insertable = false, updatable = false)
    public AccountuserEntity getAccountuserByCcommentedby() {
        return accountuserByCcommentedby;
    }

    public void setAccountuserByCcommentedby(AccountuserEntity accountuserByCcommentedby) {
        this.accountuserByCcommentedby = accountuserByCcommentedby;
    }
}
