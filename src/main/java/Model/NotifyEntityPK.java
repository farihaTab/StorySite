package Model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by DELL on 5/13/2017.
 */
public class NotifyEntityPK implements Serializable {
    private long notifyid;
    private String notify;

    @Column(name = "NOTIFYID", nullable = false, precision = 0)
    @Id
    public long getNotifyid() {
        return notifyid;
    }

    public void setNotifyid(long notifyid) {
        this.notifyid = notifyid;
    }

    @Column(name = "NOTIFY", nullable = false, length = 20)
    @Id
    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotifyEntityPK that = (NotifyEntityPK) o;

        if (notifyid != that.notifyid) return false;
        if (notify != null ? !notify.equals(that.notify) : that.notify != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (notifyid ^ (notifyid >>> 32));
        result = 31 * result + (notify != null ? notify.hashCode() : 0);
        return result;
    }
}
