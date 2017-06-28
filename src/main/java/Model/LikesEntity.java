package Model;

import javax.persistence.*;

/**
 * Created by DELL on 5/13/2017.
 */
@Entity
@Table(name = "LIKES", schema = "SDPROJECT", catalog = "")
@IdClass(LikesEntityPK.class)
public class LikesEntity {
    private String likedby;
    private long likedstory;
    private AccountuserEntity accountuserByLikedby;
    private StoryEntity storyByLikedstory;

    @Id
    @Column(name = "LIKEDBY", nullable = false, length = 20)
    public String getLikedby() {
        return likedby;
    }

    public void setLikedby(String likedby) {
        this.likedby = likedby;
    }

    @Id
    @Column(name = "LIKEDSTORY", nullable = false, precision = 0)
    public long getLikedstory() {
        return likedstory;
    }

    public void setLikedstory(long likedstory) {
        this.likedstory = likedstory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LikesEntity that = (LikesEntity) o;

        if (likedstory != that.likedstory) return false;
        if (likedby != null ? !likedby.equals(that.likedby) : that.likedby != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = likedby != null ? likedby.hashCode() : 0;
        result = 31 * result + (int) (likedstory ^ (likedstory >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "LIKEDBY", referencedColumnName = "USERNAME", nullable = false, insertable = false, updatable = false)
    public AccountuserEntity getAccountuserByLikedby() {
        return accountuserByLikedby;
    }

    public void setAccountuserByLikedby(AccountuserEntity accountuserByLikedby) {
        this.accountuserByLikedby = accountuserByLikedby;
    }

    @ManyToOne
    @JoinColumn(name = "LIKEDSTORY", referencedColumnName = "STORYID", nullable = false, insertable = false, updatable = false)
    public StoryEntity getStoryByLikedstory() {
        return storyByLikedstory;
    }

    public void setStoryByLikedstory(StoryEntity storyByLikedstory) {
        this.storyByLikedstory = storyByLikedstory;
    }
}
