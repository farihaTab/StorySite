package Model;

import javax.persistence.*;

/**
 * Created by DELL on 5/13/2017.
 */
@Entity
@Table(name = "USERPROFILE", schema = "SDPROJECT", catalog = "")
public class UserprofileEntity {
    private String writer;
    private String aboutwriter;
    private AccountuserEntity accountuserByWriter;

    @Id
    @Column(name = "WRITER", nullable = false, length = 20)
    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Basic
    @Column(name = "ABOUTWRITER", nullable = true, length = 4000)
    public String getAboutwriter() {
        return aboutwriter;
    }

    public void setAboutwriter(String aboutwriter) {
        this.aboutwriter = aboutwriter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserprofileEntity that = (UserprofileEntity) o;

        if (writer != null ? !writer.equals(that.writer) : that.writer != null) return false;
        if (aboutwriter != null ? !aboutwriter.equals(that.aboutwriter) : that.aboutwriter != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = writer != null ? writer.hashCode() : 0;
        result = 31 * result + (aboutwriter != null ? aboutwriter.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "WRITER", referencedColumnName = "USERNAME", nullable = false, insertable = false, updatable = false)
    public AccountuserEntity getAccountuserByWriter() {
        return accountuserByWriter;
    }

    public void setAccountuserByWriter(AccountuserEntity accountuserByWriter) {
        this.accountuserByWriter = accountuserByWriter;
    }
}
