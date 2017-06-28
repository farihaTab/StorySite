package Model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by DELL on 5/13/2017.
 */
public class ChapterEntityPK implements Serializable {
    private long chapterid;
    private long belongsto;

    @Column(name = "CHAPTERID", nullable = false, precision = 0)
    @Id
    public long getChapterid() {
        return chapterid;
    }

    public void setChapterid(long chapterid) {
        this.chapterid = chapterid;
    }

    @Column(name = "BELONGSTO", nullable = false, precision = 0)
    @Id
    public long getBelongsto() {
        return belongsto;
    }

    public void setBelongsto(long belongsto) {
        this.belongsto = belongsto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChapterEntityPK that = (ChapterEntityPK) o;

        if (chapterid != that.chapterid) return false;
        if (belongsto != that.belongsto) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (chapterid ^ (chapterid >>> 32));
        result = 31 * result + (int) (belongsto ^ (belongsto >>> 32));
        return result;
    }
}
