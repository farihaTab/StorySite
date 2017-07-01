package Model;

import javax.persistence.*;

/**
 * Created by DELL on 7/1/2017.
 */
@Entity
@Table(name = "RECOMMENDEDCATEGORY", schema = "SDPROJECT", catalog = "")
@IdClass(RecommendedcategoryEntityPK.class)
public class RecommendedcategoryEntity {
    private String interested;
    private String interestedin;
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
    @Column(name = "INTERESTEDIN", nullable = false, length = 30)
    public String getInterestedin() {
        return interestedin;
    }

    public void setInterestedin(String interestedin) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecommendedcategoryEntity that = (RecommendedcategoryEntity) o;

        if (interested != null ? !interested.equals(that.interested) : that.interested != null) return false;
        if (interestedin != null ? !interestedin.equals(that.interestedin) : that.interestedin != null) return false;
        if (interestrating != null ? !interestrating.equals(that.interestrating) : that.interestrating != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = interested != null ? interested.hashCode() : 0;
        result = 31 * result + (interestedin != null ? interestedin.hashCode() : 0);
        result = 31 * result + (interestrating != null ? interestrating.hashCode() : 0);
        return result;
    }
}
