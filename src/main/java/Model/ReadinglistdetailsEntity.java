package Model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by DELL on 5/13/2017.
 */
@Entity
@Table(name = "READINGLISTDETAILS", schema = "SDPROJECT", catalog = "")
@IdClass(ReadinglistdetailsEntityPK.class)
public class ReadinglistdetailsEntity {
    private long list;
    private long storyinlist;
    private Time addedat;
    private ReadinglistEntity readinglistByList;
    private StoryEntity storyByStoryinlist;

    @Id
    @Column(name = "LIST", nullable = false, precision = 0)
    public long getList() {
        return list;
    }

    public void setList(long list) {
        this.list = list;
    }

    @Id
    @Column(name = "STORYINLIST", nullable = false, precision = 0)
    public long getStoryinlist() {
        return storyinlist;
    }

    public void setStoryinlist(long storyinlist) {
        this.storyinlist = storyinlist;
    }

    @Basic
    @Column(name = "ADDEDAT", nullable = true)
    public Time getAddedat() {
        return addedat;
    }

    public void setAddedat(Time addedat) {
        this.addedat = addedat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReadinglistdetailsEntity that = (ReadinglistdetailsEntity) o;

        if (list != that.list) return false;
        if (storyinlist != that.storyinlist) return false;
        if (addedat != null ? !addedat.equals(that.addedat) : that.addedat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (list ^ (list >>> 32));
        result = 31 * result + (int) (storyinlist ^ (storyinlist >>> 32));
        result = 31 * result + (addedat != null ? addedat.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "LIST", referencedColumnName = "LISTID", nullable = false, insertable = false, updatable = false)
    public ReadinglistEntity getReadinglistByList() {
        return readinglistByList;
    }

    public void setReadinglistByList(ReadinglistEntity readinglistByList) {
        this.readinglistByList = readinglistByList;
    }

    @ManyToOne
    @JoinColumn(name = "STORYINLIST", referencedColumnName = "STORYID", nullable = false, insertable = false, updatable = false)
    public StoryEntity getStoryByStoryinlist() {
        return storyByStoryinlist;
    }

    public void setStoryByStoryinlist(StoryEntity storyByStoryinlist) {
        this.storyByStoryinlist = storyByStoryinlist;
    }
}
