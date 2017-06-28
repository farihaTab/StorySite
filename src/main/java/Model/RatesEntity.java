package Model;

import javax.persistence.*;

/**
 * Created by DELL on 5/13/2017.
 */
@Entity
@Table(name = "RATES", schema = "SDPROJECT", catalog = "")
@IdClass(RatesEntityPK.class)
public class RatesEntity {
    private String ratedby;
    private long ratedstory;
    private Long rating;
    private AccountuserEntity accountuserByRatedby;
    private StoryEntity storyByRatedstory;

    @Id
    @Column(name = "RATEDBY", nullable = false, length = 20)
    public String getRatedby() {
        return ratedby;
    }

    public void setRatedby(String ratedby) {
        this.ratedby = ratedby;
    }

    @Id
    @Column(name = "RATEDSTORY", nullable = false, precision = 0)
    public long getRatedstory() {
        return ratedstory;
    }

    public void setRatedstory(long ratedstory) {
        this.ratedstory = ratedstory;
    }

    @Basic
    @Column(name = "RATING", nullable = true, precision = 1)
    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatesEntity that = (RatesEntity) o;

        if (ratedstory != that.ratedstory) return false;
        if (ratedby != null ? !ratedby.equals(that.ratedby) : that.ratedby != null) return false;
        if (rating != null ? !rating.equals(that.rating) : that.rating != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ratedby != null ? ratedby.hashCode() : 0;
        result = 31 * result + (int) (ratedstory ^ (ratedstory >>> 32));
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "RATEDBY", referencedColumnName = "USERNAME", nullable = false, insertable = false, updatable = false)
    public AccountuserEntity getAccountuserByRatedby() {
        return accountuserByRatedby;
    }

    public void setAccountuserByRatedby(AccountuserEntity accountuserByRatedby) {
        this.accountuserByRatedby = accountuserByRatedby;
    }

    @ManyToOne
    @JoinColumn(name = "RATEDSTORY", referencedColumnName = "STORYID", nullable = false, insertable = false, updatable = false)
    public StoryEntity getStoryByRatedstory() {
        return storyByRatedstory;
    }

    public void setStoryByRatedstory(StoryEntity storyByRatedstory) {
        this.storyByRatedstory = storyByRatedstory;
    }
}
