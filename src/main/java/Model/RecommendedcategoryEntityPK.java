package Model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by DELL on 7/1/2017.
 */
public class RecommendedcategoryEntityPK implements Serializable {
    private String interested;
    private String interestedin;

    @Column(name = "INTERESTED", nullable = false, length = 20)
    @Id
    public String getInterested() {
        return interested;
    }

    public void setInterested(String interested) {
        this.interested = interested;
    }

    @Column(name = "INTERESTEDIN", nullable = false, length = 30)
    @Id
    public String getInterestedin() {
        return interestedin;
    }

    public void setInterestedin(String interestedin) {
        this.interestedin = interestedin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecommendedcategoryEntityPK that = (RecommendedcategoryEntityPK) o;

        if (interested != null ? !interested.equals(that.interested) : that.interested != null) return false;
        if (interestedin != null ? !interestedin.equals(that.interestedin) : that.interestedin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = interested != null ? interested.hashCode() : 0;
        result = 31 * result + (interestedin != null ? interestedin.hashCode() : 0);
        return result;
    }
}
