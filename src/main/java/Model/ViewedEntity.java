package Model;

import javax.persistence.*;

/**
 * Created by DELL on 5/13/2017.
 */
@Entity
@Table(name = "VIEWED", schema = "SDPROJECT", catalog = "")
@IdClass(ViewedEntityPK.class)
public class ViewedEntity {
    private String viewedby;
    private long viewedstory;
    private AccountuserEntity accountuserByViewedby;
    private StoryEntity storyByViewedstory;

    @Id
    @Column(name = "VIEWEDBY", nullable = false, length = 20)
    public String getViewedby() {
        return viewedby;
    }

    public void setViewedby(String viewedby) {
        this.viewedby = viewedby;
    }

    @Id
    @Column(name = "VIEWEDSTORY", nullable = false, precision = 0)
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

        ViewedEntity that = (ViewedEntity) o;

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

    @ManyToOne
    @JoinColumn(name = "VIEWEDBY", referencedColumnName = "USERNAME", nullable = false, insertable = false, updatable = false)
    public AccountuserEntity getAccountuserByViewedby() {
        return accountuserByViewedby;
    }

    public void setAccountuserByViewedby(AccountuserEntity accountuserByViewedby) {
        this.accountuserByViewedby = accountuserByViewedby;
    }

    @ManyToOne
    @JoinColumn(name = "VIEWEDSTORY", referencedColumnName = "STORYID", nullable = false, insertable = false, updatable = false)
    public StoryEntity getStoryByViewedstory() {
        return storyByViewedstory;
    }

    public void setStoryByViewedstory(StoryEntity storyByViewedstory) {
        this.storyByViewedstory = storyByViewedstory;
    }
}
