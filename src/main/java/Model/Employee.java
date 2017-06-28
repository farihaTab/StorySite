package Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by DELL on 5/13/2017.
 */
@Entity
public class Employee {
    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
