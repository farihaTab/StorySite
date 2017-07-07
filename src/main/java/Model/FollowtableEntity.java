package Model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by DELL on 7/1/2017.
 */
@Entity
@Table(name = "FOLLOWTABLE", schema = "SDPROJECT", catalog = "")
@IdClass(FollowtableEntityPK.class)
public class FollowtableEntity {
    private String follower;
    private String followed;
    private Time updatedat;

    @Id
    @Column(name = "FOLLOWER", nullable = false, length = 20)
    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    @Id
    @Column(name = "FOLLOWED", nullable = false, length = 20)
    public String getFollowed() {
        return followed;
    }

    public void setFollowed(String followed) {
        this.followed = followed;
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

        FollowtableEntity that = (FollowtableEntity) o;

        if (follower != null ? !follower.equals(that.follower) : that.follower != null) return false;
        if (followed != null ? !followed.equals(that.followed) : that.followed != null) return false;
        if (updatedat != null ? !updatedat.equals(that.updatedat) : that.updatedat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = follower != null ? follower.hashCode() : 0;
        result = 31 * result + (followed != null ? followed.hashCode() : 0);
        result = 31 * result + (updatedat != null ? updatedat.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FollowtableEntity{" +
                "follower='" + follower + '\'' +
                ", followed='" + followed + '\'' +
                ", updatedat=" + updatedat +
                '}';
    }
}
