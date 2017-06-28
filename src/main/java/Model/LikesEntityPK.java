package Model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by DELL on 5/13/2017.
 */
public class LikesEntityPK implements Serializable {
    private String likedby;
    private long likedstory;

    public LikesEntityPK() {
    }

    public LikesEntityPK(String likedby, long likedstory) {
        this.likedby = likedby;
        this.likedstory = likedstory;
    }

    @Column(name = "LIKEDBY", nullable = false, length = 20)
    @Id
    public String getLikedby() {
        return likedby;
    }

    public void setLikedby(String likedby) {
        this.likedby = likedby;
    }

    @Column(name = "LIKEDSTORY", nullable = false, precision = 0)
    @Id
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

        LikesEntityPK that = (LikesEntityPK) o;

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
}
