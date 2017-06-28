package Model;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by DELL on 5/15/2017.
 */
@Entity
@Table(name = "READING", schema = "SDPROJECT", catalog = "")
@IdClass(ReadingEntityPK.class)
public class ReadingEntity {
    private String readby;
    private long readstory;
    private String readingpace;
    private Time startedat;
    private Time lastreadat;
    private AccountuserEntity accountuserByReadby;
    private StoryEntity storyByReadstory;

    @Override
    public String toString() {
        return "ReadingEntity{" +
                "readby='" + readby + '\'' +
                ", readstory=" + readstory +
                ", readingpace='" + readingpace + '\'' +
                ", startedat=" + startedat +
                ", lastreadat=" + lastreadat +
                '}';
    }

    @Id
    @Column(name = "READBY", nullable = false, length = 20)
    public String getReadby() {
        return readby;
    }

    public void setReadby(String readby) {
        this.readby = readby;
    }

    @Id
    @Column(name = "READSTORY", nullable = false, precision = 0)
    public long getReadstory() {
        return readstory;
    }

    public void setReadstory(long readstory) {
        this.readstory = readstory;
    }

    @Basic
    @Column(name = "READINGPACE", nullable = true, length = 100)
    public String getReadingpace() {
        return readingpace;
    }

    public void setReadingpace(String readingpace) {
        this.readingpace = readingpace;
    }

    @Basic
    @Column(name = "STARTEDAT", nullable = true)
    public Time getStartedat() {
        return startedat;
    }

    public void setStartedat(Time startedat) {
        this.startedat = startedat;
    }

    @Basic
    @Column(name = "LASTREADAT", nullable = true)
    public Time getLastreadat() {
        return lastreadat;
    }

    public void setLastreadat(Time lastreadat) {
        this.lastreadat = lastreadat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReadingEntity that = (ReadingEntity) o;

        if (readstory != that.readstory) return false;
        if (readby != null ? !readby.equals(that.readby) : that.readby != null) return false;
        if (readingpace != null ? !readingpace.equals(that.readingpace) : that.readingpace != null) return false;
        if (startedat != null ? !startedat.equals(that.startedat) : that.startedat != null) return false;
        if (lastreadat != null ? !lastreadat.equals(that.lastreadat) : that.lastreadat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = readby != null ? readby.hashCode() : 0;
        result = 31 * result + (int) (readstory ^ (readstory >>> 32));
        result = 31 * result + (readingpace != null ? readingpace.hashCode() : 0);
        result = 31 * result + (startedat != null ? startedat.hashCode() : 0);
        result = 31 * result + (lastreadat != null ? lastreadat.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "READBY", referencedColumnName = "USERNAME", nullable = false,insertable = false,updatable = false)
    public AccountuserEntity getAccountuserByReadby() {
        return accountuserByReadby;
    }

    public void setAccountuserByReadby(AccountuserEntity accountuserByReadby) {
        this.accountuserByReadby = accountuserByReadby;
    }

    @ManyToOne
    @JoinColumn(name = "READSTORY", referencedColumnName = "STORYID", nullable = false,insertable = false,updatable = false)
    public StoryEntity getStoryByReadstory() {
        return storyByReadstory;
    }

    public void setStoryByReadstory(StoryEntity storyByReadstory) {
        this.storyByReadstory = storyByReadstory;
    }
}
