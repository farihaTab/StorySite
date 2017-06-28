package Model;

import javax.persistence.*;

/**
 * Created by DELL on 5/13/2017.
 */
@Entity
@Table(name = "STORYTAG", schema = "SDPROJECT", catalog = "")
@IdClass(StorytagEntityPK.class)
public class StorytagEntity {
    private long taggedstory;
    private String tagname;
    private StoryEntity storyByTaggedstory;

    @Override
    public String toString() {
        return "StorytagEntity{" +
                "taggedstory=" + taggedstory +
                ", tagname='" + tagname + '\'' +
                '}';
    }

    @Id
    @Column(name = "TAGGEDSTORY", nullable = false, precision = 0)
    public long getTaggedstory() {
        return taggedstory;
    }

    public void setTaggedstory(long taggedstory) {
        this.taggedstory = taggedstory;
    }

    @Id
    @Column(name = "TAGNAME", nullable = false, length = 100)
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

        StorytagEntity that = (StorytagEntity) o;

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

    @ManyToOne
    @JoinColumn(name = "TAGGEDSTORY", referencedColumnName = "STORYID", nullable = false, insertable = false, updatable = false)
    public StoryEntity getStoryByTaggedstory() {
        return storyByTaggedstory;
    }

    public void setStoryByTaggedstory(StoryEntity storyByTaggedstory) {
        this.storyByTaggedstory = storyByTaggedstory;
    }
}
