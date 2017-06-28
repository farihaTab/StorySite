package Model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by DELL on 5/15/2017.
 */
public class ReadingEntityPK implements Serializable {
    private String readby;
    private long readstory;

    public ReadingEntityPK(String readby, long readstory) {
        this.readby = readby;
        this.readstory = readstory;
    }

    public ReadingEntityPK() {
    }

    @Column(name = "READBY", nullable = false, length = 20)
    @Id
    public String getReadby() {
        return readby;
    }

    public void setReadby(String readby) {
        this.readby = readby;
    }

    @Column(name = "READSTORY", nullable = false, precision = 0)
    @Id
    public long getReadstory() {
        return readstory;
    }

    public void setReadstory(long readstory) {
        this.readstory = readstory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReadingEntityPK that = (ReadingEntityPK) o;

        if (readstory != that.readstory) return false;
        if (readby != null ? !readby.equals(that.readby) : that.readby != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = readby != null ? readby.hashCode() : 0;
        result = 31 * result + (int) (readstory ^ (readstory >>> 32));
        return result;
    }
}
