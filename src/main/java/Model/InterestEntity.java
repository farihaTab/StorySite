package Model;

import javax.persistence.*;

/**
 * Created by DELL on 5/13/2017.
 */
@Entity
@Table(name = "INTEREST", schema = "SDPROJECT", catalog = "")
@IdClass(InterestEntityPK.class)
public class InterestEntity {
    private String interested;
    private long interestedin;
    private Long interestrating;
    private String interesttype;
    private AccountuserEntity accountuserByInterested;
    private StoryEntity storyByInterestedin;

    @Id
    @Column(name = "INTERESTED", nullable = false, length = 20)
    public String getInterested() {
        return interested;
    }

    public void setInterested(String interested) {
        this.interested = interested;
    }

    @Id
    @Column(name = "INTERESTEDIN", nullable = false, precision = 0)
    public long getInterestedin() {
        return interestedin;
    }

    public void setInterestedin(long interestedin) {
        this.interestedin = interestedin;
    }

    @Basic
    @Column(name = "INTERESTRATING", nullable = true, precision = 0)
    public Long getInterestrating() {
        return interestrating;
    }

    public void setInterestrating(Long interestrating) {
        this.interestrating = interestrating;
    }

    @Basic
    @Column(name = "INTERESTTYPE", nullable = true, length = 200)
    public String getInteresttype() {
        return interesttype;
    }

    public void setInteresttype(String interesttype) {
        this.interesttype = interesttype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InterestEntity that = (InterestEntity) o;

        if (interestedin != that.interestedin) return false;
        if (interested != null ? !interested.equals(that.interested) : that.interested != null) return false;
        if (interestrating != null ? !interestrating.equals(that.interestrating) : that.interestrating != null)
            return false;
        if (interesttype != null ? !interesttype.equals(that.interesttype) : that.interesttype != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = interested != null ? interested.hashCode() : 0;
        result = 31 * result + (int) (interestedin ^ (interestedin >>> 32));
        result = 31 * result + (interestrating != null ? interestrating.hashCode() : 0);
        result = 31 * result + (interesttype != null ? interesttype.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "INTERESTED", referencedColumnName = "USERNAME", nullable = false, insertable = false, updatable = false)
    public AccountuserEntity getAccountuserByInterested() {
        return accountuserByInterested;
    }

    public void setAccountuserByInterested(AccountuserEntity accountuserByInterested) {
        this.accountuserByInterested = accountuserByInterested;
    }

    @ManyToOne
    @JoinColumn(name = "INTERESTEDIN", referencedColumnName = "STORYID", nullable = false, insertable = false, updatable = false)
    public StoryEntity getStoryByInterestedin() {
        return storyByInterestedin;
    }

    public void setStoryByInterestedin(StoryEntity storyByInterestedin) {
        this.storyByInterestedin = storyByInterestedin;
    }
}
