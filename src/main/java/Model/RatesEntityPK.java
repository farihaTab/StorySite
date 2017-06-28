package Model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by DELL on 5/13/2017.
 */
public class RatesEntityPK implements Serializable {
    private String ratedby;
    private long ratedstory;

    @Column(name = "RATEDBY", nullable = false, length = 20)
    @Id
    public String getRatedby() {
        return ratedby;
    }

    public void setRatedby(String ratedby) {
        this.ratedby = ratedby;
    }

    @Column(name = "RATEDSTORY", nullable = false, precision = 0)
    @Id
    public long getRatedstory() {
        return ratedstory;
    }

    public void setRatedstory(long ratedstory) {
        this.ratedstory = ratedstory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatesEntityPK that = (RatesEntityPK) o;

        if (ratedstory != that.ratedstory) return false;
        if (ratedby != null ? !ratedby.equals(that.ratedby) : that.ratedby != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ratedby != null ? ratedby.hashCode() : 0;
        result = 31 * result + (int) (ratedstory ^ (ratedstory >>> 32));
        return result;
    }
}
