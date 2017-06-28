package Model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by DELL on 5/13/2017.
 */
@Entity
@Table(name = "CATEGORY", schema = "SDPROJECT", catalog = "")
public class CategoryEntity {
    private String catname;
    private Collection<StoryEntity> storiesByCatname;

    @Id
    @Column(name = "CATNAME", nullable = false, length = 30)
    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryEntity that = (CategoryEntity) o;

        if (catname != null ? !catname.equals(that.catname) : that.catname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return catname != null ? catname.hashCode() : 0;
    }

    @OneToMany(mappedBy = "categoryByCategoryname")
    public Collection<StoryEntity> getStoriesByCatname() {
        return storiesByCatname;
    }

    public void setStoriesByCatname(Collection<StoryEntity> storiesByCatname) {
        this.storiesByCatname = storiesByCatname;
    }
}
