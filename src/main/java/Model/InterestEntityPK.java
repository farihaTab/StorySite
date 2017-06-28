package Model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by DELL on 5/13/2017.
 */
public class InterestEntityPK implements Serializable {
    private String interested;
    private long interestedin;

    @Column(name = "INTERESTED", nullable = false, length = 20)
    @Id
    public String getInterested() {
        return interested;
    }

    public void setInterested(String interested) {
        this.interested = interested;
    }

    @Column(name = "INTERESTEDIN", nullable = false, precision = 0)
    @Id
    public long getInterestedin() {
        return interestedin;
    }

    public void setInterestedin(long interestedin) {
        this.interestedin = interestedin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InterestEntityPK that = (InterestEntityPK) o;

        if (interestedin != that.interestedin) return false;
        if (interested != null ? !interested.equals(that.interested) : that.interested != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = interested != null ? interested.hashCode() : 0;
        result = 31 * result + (int) (interestedin ^ (interestedin >>> 32));
        return result;
    }
}
