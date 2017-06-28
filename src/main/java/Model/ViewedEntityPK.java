package Model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by DELL on 5/13/2017.
 */
public class ViewedEntityPK implements Serializable {
    private String viewedby;
    private long viewedstory;

    @Column(name = "VIEWEDBY", nullable = false, length = 20)
    @Id
    public String getViewedby() {
        return viewedby;
    }

    public void setViewedby(String viewedby) {
        this.viewedby = viewedby;
    }

    @Column(name = "VIEWEDSTORY", nullable = false, precision = 0)
    @Id
    public long getViewedstory() {
        return viewedstory;
    }

    public void setViewedstory(long viewedstory) {
        this.viewedstory = viewedstory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewedEntityPK that = (ViewedEntityPK) o;

        if (viewedstory != that.viewedstory) return false;
        if (viewedby != null ? !viewedby.equals(that.viewedby) : that.viewedby != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = viewedby != null ? viewedby.hashCode() : 0;
        result = 31 * result + (int) (viewedstory ^ (viewedstory >>> 32));
        return result;
    }
}
