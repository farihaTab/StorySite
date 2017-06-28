package Model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by DELL on 5/13/2017.
 */
public class ReadinglistdetailsEntityPK implements Serializable {
    private long list;
    private long storyinlist;

    @Column(name = "LIST", nullable = false, precision = 0)
    @Id
    public long getList() {
        return list;
    }

    public void setList(long list) {
        this.list = list;
    }

    @Column(name = "STORYINLIST", nullable = false, precision = 0)
    @Id
    public long getStoryinlist() {
        return storyinlist;
    }

    public void setStoryinlist(long storyinlist) {
        this.storyinlist = storyinlist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReadinglistdetailsEntityPK that = (ReadinglistdetailsEntityPK) o;

        if (list != that.list) return false;
        if (storyinlist != that.storyinlist) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (list ^ (list >>> 32));
        result = 31 * result + (int) (storyinlist ^ (storyinlist >>> 32));
        return result;
    }
}
