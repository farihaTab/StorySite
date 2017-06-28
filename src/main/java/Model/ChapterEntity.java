package Model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;

/**
 * Created by DELL on 5/13/2017.
 */
@Entity
@Table(name = "CHAPTER", schema = "SDPROJECT", catalog = "")
@IdClass(ChapterEntityPK.class)
public class ChapterEntity {
    private long chapterid;
    private long belongsto;
    private Long nextchapter;
    private Long prevchapter;
    private String title;
    private Boolean ispublished;
    private String imageurl;
    private String chapterbody;
    private Time createdat;
    private Time updatedat;
    private StoryEntity storyByBelongsto;
    private Collection<ChaptercommentsEntity> chaptercommentss;

    @Id
    @Column(name = "CHAPTERID", nullable = false, precision = 0)
    public long getChapterid() {
        return chapterid;
    }

    public void setChapterid(long chapterid) {
        this.chapterid = chapterid;
    }

    @Id
    @Column(name = "BELONGSTO", nullable = false, precision = 0)
    public long getBelongsto() {
        return belongsto;
    }

    public void setBelongsto(long belongsto) {
        this.belongsto = belongsto;
    }

    @Basic
    @Column(name = "NEXTCHAPTER", nullable = true, precision = 0)
    public Long getNextchapter() {
        return nextchapter;
    }

    public void setNextchapter(Long nextchapter) {
        this.nextchapter = nextchapter;
    }

    @Basic
    @Column(name = "PREVCHAPTER", nullable = true, precision = 0)
    public Long getPrevchapter() {
        return prevchapter;
    }

    public void setPrevchapter(Long prevchapter) {
        this.prevchapter = prevchapter;
    }

    @Basic
    @Column(name = "TITLE", nullable = true, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "ISPUBLISHED", nullable = true, precision = 0)
    public Boolean getIspublished() {
        return ispublished;
    }

    public void setIspublished(Boolean ispublished) {
        this.ispublished = ispublished;
    }

    @Basic
    @Column(name = "IMAGEURL", nullable = true, length = 50)
    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    @Basic
    @Column(name = "CHAPTERBODY", nullable = true)
    public String getChapterbody() {
        return chapterbody;
    }

    public void setChapterbody(String chapterbody) {
        this.chapterbody = chapterbody;
    }

    @Basic
    @Column(name = "CREATEDAT", nullable = true)
    public Time getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Time createdat) {
        this.createdat = createdat;
    }

    @Basic
    @Column(name = "UPDATEDAT", nullable = true)
    public Time getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(Time updatedat) {
        this.updatedat = updatedat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChapterEntity that = (ChapterEntity) o;

        if (chapterid != that.chapterid) return false;
        if (belongsto != that.belongsto) return false;
        if (nextchapter != null ? !nextchapter.equals(that.nextchapter) : that.nextchapter != null) return false;
        if (prevchapter != null ? !prevchapter.equals(that.prevchapter) : that.prevchapter != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (ispublished != null ? !ispublished.equals(that.ispublished) : that.ispublished != null) return false;
        if (imageurl != null ? !imageurl.equals(that.imageurl) : that.imageurl != null) return false;
        if (chapterbody != null ? !chapterbody.equals(that.chapterbody) : that.chapterbody != null) return false;
        if (createdat != null ? !createdat.equals(that.createdat) : that.createdat != null) return false;
        if (updatedat != null ? !updatedat.equals(that.updatedat) : that.updatedat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (chapterid ^ (chapterid >>> 32));
        result = 31 * result + (int) (belongsto ^ (belongsto >>> 32));
        result = 31 * result + (nextchapter != null ? nextchapter.hashCode() : 0);
        result = 31 * result + (prevchapter != null ? prevchapter.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (ispublished != null ? ispublished.hashCode() : 0);
        result = 31 * result + (imageurl != null ? imageurl.hashCode() : 0);
        result = 31 * result + (chapterbody != null ? chapterbody.hashCode() : 0);
        result = 31 * result + (createdat != null ? createdat.hashCode() : 0);
        result = 31 * result + (updatedat != null ? updatedat.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "BELONGSTO", referencedColumnName = "STORYID", nullable = false, insertable = false, updatable = false)
    public StoryEntity getStoryByBelongsto() {
        return storyByBelongsto;
    }

    public void setStoryByBelongsto(StoryEntity storyByBelongsto) {
        this.storyByBelongsto = storyByBelongsto;
    }

    @OneToMany(mappedBy = "chapter")
    public Collection<ChaptercommentsEntity> getChaptercommentss() {
        return chaptercommentss;
    }

    public void setChaptercommentss(Collection<ChaptercommentsEntity> chaptercommentss) {
        this.chaptercommentss = chaptercommentss;
    }

    @Override
    public String toString() {
        return "ChapterEntity{" +
                "chapterid=" + chapterid +
                ", belongsto=" + belongsto +
                ", nextchapter=" + nextchapter +
                ", prevchapter=" + prevchapter +
                ", title='" + title + '\'' +
                ", ispublished=" + ispublished +
                ", imageurl='" + imageurl + '\'' +
                ", chapterbody='" + chapterbody + '\'' +
                ", createdat=" + createdat +
                ", updatedat=" + updatedat +
                '}';
    }
}
