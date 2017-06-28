package Model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by DELL on 5/13/2017.
 */
@Entity
@Table(name = "NOTIFY", schema = "SDPROJECT", catalog = "")
@IdClass(NotifyEntityPK.class)
public class NotifyEntity {
    private long notifyid;
    private String notify;
    private String notifymsg;
    private Time notifytime;
    private String notifylink;
    private Boolean notifystatus;
    private AccountuserEntity accountuserByNotify;

    @Id
    @Column(name = "NOTIFYID", nullable = false, precision = 0)
    public long getNotifyid() {
        return notifyid;
    }

    public void setNotifyid(long notifyid) {
        this.notifyid = notifyid;
    }

    @Id
    @Column(name = "NOTIFY", nullable = false, length = 20)
    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    @Basic
    @Column(name = "NOTIFYMSG", nullable = true, length = 1000)
    public String getNotifymsg() {
        return notifymsg;
    }

    public void setNotifymsg(String notifymsg) {
        this.notifymsg = notifymsg;
    }

    @Basic
    @Column(name = "NOTIFYTIME", nullable = true)
    public Time getNotifytime() {
        return notifytime;
    }

    public void setNotifytime(Time notifytime) {
        this.notifytime = notifytime;
    }

    @Basic
    @Column(name = "NOTIFYLINK", nullable = true, length = 1000)
    public String getNotifylink() {
        return notifylink;
    }

    public void setNotifylink(String notifylink) {
        this.notifylink = notifylink;
    }

    @Basic
    @Column(name = "NOTIFYSTATUS", nullable = true, precision = 0)
    public Boolean getNotifystatus() {
        return notifystatus;
    }

    public void setNotifystatus(Boolean notifystatus) {
        this.notifystatus = notifystatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotifyEntity that = (NotifyEntity) o;

        if (notifyid != that.notifyid) return false;
        if (notify != null ? !notify.equals(that.notify) : that.notify != null) return false;
        if (notifymsg != null ? !notifymsg.equals(that.notifymsg) : that.notifymsg != null) return false;
        if (notifytime != null ? !notifytime.equals(that.notifytime) : that.notifytime != null) return false;
        if (notifylink != null ? !notifylink.equals(that.notifylink) : that.notifylink != null) return false;
        if (notifystatus != null ? !notifystatus.equals(that.notifystatus) : that.notifystatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (notifyid ^ (notifyid >>> 32));
        result = 31 * result + (notify != null ? notify.hashCode() : 0);
        result = 31 * result + (notifymsg != null ? notifymsg.hashCode() : 0);
        result = 31 * result + (notifytime != null ? notifytime.hashCode() : 0);
        result = 31 * result + (notifylink != null ? notifylink.hashCode() : 0);
        result = 31 * result + (notifystatus != null ? notifystatus.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "NOTIFY", referencedColumnName = "USERNAME", nullable = false, insertable = false, updatable = false)
    public AccountuserEntity getAccountuserByNotify() {
        return accountuserByNotify;
    }

    public void setAccountuserByNotify(AccountuserEntity accountuserByNotify) {
        this.accountuserByNotify = accountuserByNotify;
    }
}
