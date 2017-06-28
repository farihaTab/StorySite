package Model;

import javax.persistence.*;

/**
 * Created by DELL on 5/13/2017.
 */
@Entity
@Table(name = "FOLLOWTABLE", schema = "SDPROJECT", catalog = "")
@IdClass(FollowtableEntityPK.class)
public class FollowtableEntity {
    private String follower;
    private String followed;
    private AccountuserEntity accountuserByFollower;
    private AccountuserEntity accountuserByFollowed;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FollowtableEntity that = (FollowtableEntity) o;

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

    @ManyToOne
    @JoinColumn(name = "FOLLOWER", referencedColumnName = "USERNAME", nullable = false, insertable = false, updatable = false)
    public AccountuserEntity getAccountuserByFollower() {
        return accountuserByFollower;
    }

    public void setAccountuserByFollower(AccountuserEntity accountuserByFollower) {
        this.accountuserByFollower = accountuserByFollower;
    }

    @ManyToOne
    @JoinColumn(name = "FOLLOWED", referencedColumnName = "USERNAME", nullable = false, insertable = false, updatable = false)
    public AccountuserEntity getAccountuserByFollowed() {
        return accountuserByFollowed;
    }

    public void setAccountuserByFollowed(AccountuserEntity accountuserByFollowed) {
        this.accountuserByFollowed = accountuserByFollowed;
    }
}
