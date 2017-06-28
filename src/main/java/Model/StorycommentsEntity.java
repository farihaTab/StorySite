package Model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by DELL on 5/13/2017.
 */
@Entity
@Table(name = "STORYCOMMENTS", schema = "SDPROJECT", catalog = "")
public class StorycommentsEntity {
    private long scommentid;
    private String scommenttext;
    private Long scommentedon;
    private String scommentedby;
    private Time scommentedat;
    private String scommenttype;
    private StoryEntity storyByScommentedon;
    private AccountuserEntity accountuserByScommentedby;

    @Override
    public String toString() {
        return "StorycommentsEntity{" +
                "scommentid=" + scommentid +
                ", scommenttext='" + scommenttext + '\'' +
                ", scommentedon=" + scommentedon +
                ", scommentedby='" + scommentedby + '\'' +
                ", scommentedat=" + scommentedat +
                ", scommenttype='" + scommenttype + '\'' +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ID_GENERATOR")
    @SequenceGenerator(name = "ID_GENERATOR", sequenceName = "SEQ_STORY_COMMENT")
    @Column(name = "SCOMMENTID", nullable = false, precision = 0)
    public long getScommentid() {
        return scommentid;
    }

    public void setScommentid(long scommentid) {
        this.scommentid = scommentid;
    }

    @Basic
    @Column(name = "SCOMMENTTEXT", nullable = true, length = 4000)
    public String getScommenttext() {
        return scommenttext;
    }

    public void setScommenttext(String scommenttext) {
        this.scommenttext = scommenttext;
    }

    @Basic
    @Column(name = "SCOMMENTEDON", nullable = true, precision = 0)
    public Long getScommentedon() {
        return scommentedon;
    }

    public void setScommentedon(Long scommentedon) {
        this.scommentedon = scommentedon;
    }

    @Basic
    @Column(name = "SCOMMENTEDBY", nullable = true, length = 20)
    public String getScommentedby() {
        return scommentedby;
    }

    public void setScommentedby(String scommentedby) {
        this.scommentedby = scommentedby;
    }

    @Basic
    @Column(name = "SCOMMENTEDAT", nullable = true)
    public Time getScommentedat() {
        return scommentedat;
    }

    public void setScommentedat(Time scommentedat) {
        this.scommentedat = scommentedat;
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

        StorycommentsEntity that = (StorycommentsEntity) o;

        if (scommentid != that.scommentid) return false;
        if (scommenttext != null ? !scommenttext.equals(that.scommenttext) : that.scommenttext != null) return false;
        if (scommentedon != null ? !scommentedon.equals(that.scommentedon) : that.scommentedon != null) return false;
        if (scommentedby != null ? !scommentedby.equals(that.scommentedby) : that.scommentedby != null) return false;
        if (scommentedat != null ? !scommentedat.equals(that.scommentedat) : that.scommentedat != null) return false;
        if (scommenttype != null ? !scommenttype.equals(that.scommenttype) : that.scommenttype != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (scommentid ^ (scommentid >>> 32));
        result = 31 * result + (scommenttext != null ? scommenttext.hashCode() : 0);
        result = 31 * result + (scommentedon != null ? scommentedon.hashCode() : 0);
        result = 31 * result + (scommentedby != null ? scommentedby.hashCode() : 0);
        result = 31 * result + (scommentedat != null ? scommentedat.hashCode() : 0);
        result = 31 * result + (scommenttype != null ? scommenttype.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "SCOMMENTEDON", referencedColumnName = "STORYID", insertable = false, updatable = false)
    public StoryEntity getStoryByScommentedon() {
        return storyByScommentedon;
    }

    public void setStoryByScommentedon(StoryEntity storyByScommentedon) {
        this.storyByScommentedon = storyByScommentedon;
    }

    @ManyToOne
    @JoinColumn(name = "SCOMMENTEDBY", referencedColumnName = "USERNAME", insertable = false, updatable = false)
    public AccountuserEntity getAccountuserByScommentedby() {
        return accountuserByScommentedby;
    }

    public void setAccountuserByScommentedby(AccountuserEntity accountuserByScommentedby) {
        this.accountuserByScommentedby = accountuserByScommentedby;
    }
}
