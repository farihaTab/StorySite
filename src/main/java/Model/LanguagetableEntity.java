package Model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by DELL on 5/13/2017.
 */
@Entity
@Table(name = "LANGUAGETABLE", schema = "SDPROJECT", catalog = "")
public class LanguagetableEntity {
    private String languageid;
    private Collection<StoryEntity> storiesByLanguageid;

    @Id
    @Column(name = "LANGUAGEID", nullable = false, length = 30)
    public String getLanguageid() {
        return languageid;
    }

    public void setLanguageid(String languageid) {
        this.languageid = languageid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LanguagetableEntity that = (LanguagetableEntity) o;

        if (languageid != null ? !languageid.equals(that.languageid) : that.languageid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return languageid != null ? languageid.hashCode() : 0;
    }

    @OneToMany(mappedBy = "languagetableByLang")
    public Collection<StoryEntity> getStoriesByLanguageid() {
        return storiesByLanguageid;
    }

    public void setStoriesByLanguageid(Collection<StoryEntity> storiesByLanguageid) {
        this.storiesByLanguageid = storiesByLanguageid;
    }
}
