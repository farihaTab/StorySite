package Model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by DELL on 5/13/2017.
 */
public class StorytagEntityPK implements Serializable {
    private long taggedstory;
    private String tagname;

    @Column(name = "TAGGEDSTORY", nullable = false, precision = 0)
    @Id
    public long getTaggedstory() {
        return taggedstory;
    }

    public void setTaggedstory(long taggedstory) {
        this.taggedstory = taggedstory;
    }

    @Column(name = "TAGNAME", nullable = false, length = 100)
    @Id
    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StorytagEntityPK that = (StorytagEntityPK) o;

        if (taggedstory != that.taggedstory) return false;
        if (tagname != null ? !tagname.equals(that.tagname) : that.tagname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (taggedstory ^ (taggedstory >>> 32));
        result = 31 * result + (tagname != null ? tagname.hashCode() : 0);
        return result;
    }
}
