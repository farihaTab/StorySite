package Model;

import javax.persistence.*;

/**
 * Created by DELL on 7/1/2017.
 */
@Entity
@Table(name = "RECOMMENDEDSTORY", schema = "SDPROJECT", catalog = "")
@IdClass(RecommendedstoryEntityPK.class)
public class RecommendedstoryEntity {
    private String interested;
    private long interestedin;
    private Long interestrating;

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
    @Column(name = "INTERESTRATING", nullable = true, precision = 2)
    public Long getInterestrating() {
        return interestrating;
    }

    public void setInterestrating(Long interestrating) {
        this.interestrating = interestrating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecommendedstoryEntity that = (RecommendedstoryEntity) o;

        if (interestedin != that.interestedin) return false;
        if (interested != null ? !interested.equals(that.interested) : that.interested != null) return false;
        if (interestrating != null ? !interestrating.equals(that.interestrating) : that.interestrating != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = interested != null ? interested.hashCode() : 0;
        result = 31 * result + (int) (interestedin ^ (interestedin >>> 32));
        result = 31 * result + (interestrating != null ? interestrating.hashCode() : 0);
        return result;
    }
}
