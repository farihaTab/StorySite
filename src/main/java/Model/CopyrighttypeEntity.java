package Model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by DELL on 5/13/2017.
 */
@Entity
@Table(name = "COPYRIGHTTYPE", schema = "SDPROJECT", catalog = "")
public class CopyrighttypeEntity {
    private String copyright;
    private Collection<StoryEntity> storiesByCopyright;

    @Id
    @Column(name = "COPYRIGHT", nullable = false, length = 40)
    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CopyrighttypeEntity that = (CopyrighttypeEntity) o;

        if (copyright != null ? !copyright.equals(that.copyright) : that.copyright != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return copyright != null ? copyright.hashCode() : 0;
    }

    @OneToMany(mappedBy = "copyrighttypeByStorycopyright")
    public Collection<StoryEntity> getStoriesByCopyright() {
        return storiesByCopyright;
    }

    public void setStoriesByCopyright(Collection<StoryEntity> storiesByCopyright) {
        this.storiesByCopyright = storiesByCopyright;
    }
}
