package Model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by DELL on 5/13/2017.
 */
public class FollowtableEntityPK implements Serializable {
    private String follower;
    private String followed;

    public FollowtableEntityPK() {
    }

    public FollowtableEntityPK(String follower, String followed) {
        this.follower = follower;
        this.followed = followed;
    }

    @Column(name = "FOLLOWER", nullable = false, length = 20)
    @Id
    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    @Column(name = "FOLLOWED", nullable = false, length = 20)
    @Id
    public String getFollowed() {
        return followed;
    }

    public void setFollowed(String followed) {
        this.followed = followed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FollowtableEntityPK that = (FollowtableEntityPK) o;

        if (follower != null ? !follower.equals(that.follower) : that.follower != null) return false;
        if (followed != null ? !followed.equals(that.followed) : that.followed != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = follower != null ? follower.hashCode() : 0;
        result = 31 * result + (followed != null ? followed.hashCode() : 0);
        return result;
    }
}
