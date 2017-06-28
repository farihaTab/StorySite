package Model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;

/**
 * Created by DELL on 5/13/2017.
 */
@Entity
@Table(name = "ACCOUNTUSER", schema = "SDPROJECT", catalog = "")
public class AccountuserEntity {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String imageurl;
    private String usertype;
    private Time createdat;
    private Time updatedat;
    private Collection<ChaptercommentsEntity> chaptercommentssByUsername;
    private Collection<FollowtableEntity> followtablesByUsername;
    private Collection<FollowtableEntity> followtablesByUsername_0;
    private Collection<InterestEntity> interestsByUsername;
    private Collection<LikesEntity> likesByUsername;
    private Collection<NotifyEntity> notifiesByUsername;
    private Collection<RatesEntity> ratesByUsername;
    private Collection<ReadingEntity> readingsByUsername;
    private Collection<ReadinglistEntity> readinglistsByUsername;
    private Collection<StoryEntity> storiesByUsername;
    private Collection<StorycommentsEntity> storycommentssByUsername;
    private UserprofileEntity userprofileByUsername;
    private Collection<ViewedEntity> viewedsByUsername;

    public AccountuserEntity() {

    }

    public AccountuserEntity(Time createdat, String email, String firstname, String lastname, String username) {
        this.createdat = createdat;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
    }

    @Id
    @Column(name = "USERNAME", nullable = false, length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = true, length = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "FIRSTNAME", nullable = false, length = 20)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "LASTNAME", nullable = false, length = 20)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "EMAIL", nullable = false, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "IMAGEURL", nullable = true, length = 100)
    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    @Basic
    @Column(name = "USERTYPE", nullable = true, length = 10)
    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
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

    public AccountuserEntity(String username,String email)
    {
        this.username=username;
        this.email=email;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountuserEntity that = (AccountuserEntity) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (imageurl != null ? !imageurl.equals(that.imageurl) : that.imageurl != null) return false;
        if (usertype != null ? !usertype.equals(that.usertype) : that.usertype != null) return false;
        if (createdat != null ? !createdat.equals(that.createdat) : that.createdat != null) return false;
        if (updatedat != null ? !updatedat.equals(that.updatedat) : that.updatedat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (imageurl != null ? imageurl.hashCode() : 0);
        result = 31 * result + (usertype != null ? usertype.hashCode() : 0);
        result = 31 * result + (createdat != null ? createdat.hashCode() : 0);
        result = 31 * result + (updatedat != null ? updatedat.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "accountuserByCcommentedby")
    public Collection<ChaptercommentsEntity> getChaptercommentssByUsername() {
        return chaptercommentssByUsername;
    }

    public void setChaptercommentssByUsername(Collection<ChaptercommentsEntity> chaptercommentssByUsername) {
        this.chaptercommentssByUsername = chaptercommentssByUsername;
    }

    @OneToMany(mappedBy = "accountuserByFollower")
    public Collection<FollowtableEntity> getFollowtablesByUsername() {
        return followtablesByUsername;
    }

    public void setFollowtablesByUsername(Collection<FollowtableEntity> followtablesByUsername) {
        this.followtablesByUsername = followtablesByUsername;
    }

    @OneToMany(mappedBy = "accountuserByFollowed")
    public Collection<FollowtableEntity> getFollowtablesByUsername_0() {
        return followtablesByUsername_0;
    }

    public void setFollowtablesByUsername_0(Collection<FollowtableEntity> followtablesByUsername_0) {
        this.followtablesByUsername_0 = followtablesByUsername_0;
    }

    @OneToMany(mappedBy = "accountuserByInterested")
    public Collection<InterestEntity> getInterestsByUsername() {
        return interestsByUsername;
    }

    public void setInterestsByUsername(Collection<InterestEntity> interestsByUsername) {
        this.interestsByUsername = interestsByUsername;
    }

    @OneToMany(mappedBy = "accountuserByLikedby")
    public Collection<LikesEntity> getLikesByUsername() {
        return likesByUsername;
    }

    public void setLikesByUsername(Collection<LikesEntity> likesByUsername) {
        this.likesByUsername = likesByUsername;
    }

    @OneToMany(mappedBy = "accountuserByNotify")
    public Collection<NotifyEntity> getNotifiesByUsername() {
        return notifiesByUsername;
    }

    public void setNotifiesByUsername(Collection<NotifyEntity> notifiesByUsername) {
        this.notifiesByUsername = notifiesByUsername;
    }

    @OneToMany(mappedBy = "accountuserByRatedby")
    public Collection<RatesEntity> getRatesByUsername() {
        return ratesByUsername;
    }

    public void setRatesByUsername(Collection<RatesEntity> ratesByUsername) {
        this.ratesByUsername = ratesByUsername;
    }

    @OneToMany(mappedBy = "accountuserByReadby")
    public Collection<ReadingEntity> getReadingsByUsername() {
        return readingsByUsername;
    }

    public void setReadingsByUsername(Collection<ReadingEntity> readingsByUsername) {
        this.readingsByUsername = readingsByUsername;
    }

    @OneToMany(mappedBy = "accountuserByListowner")
    public Collection<ReadinglistEntity> getReadinglistsByUsername() {
        return readinglistsByUsername;
    }

    public void setReadinglistsByUsername(Collection<ReadinglistEntity> readinglistsByUsername) {
        this.readinglistsByUsername = readinglistsByUsername;
    }

    @OneToMany(mappedBy = "accountuserByWriterid")
    public Collection<StoryEntity> getStoriesByUsername() {
        return storiesByUsername;
    }

    public void setStoriesByUsername(Collection<StoryEntity> storiesByUsername) {
        this.storiesByUsername = storiesByUsername;
    }

    @OneToMany(mappedBy = "accountuserByScommentedby")
    public Collection<StorycommentsEntity> getStorycommentssByUsername() {
        return storycommentssByUsername;
    }

    public void setStorycommentssByUsername(Collection<StorycommentsEntity> storycommentssByUsername) {
        this.storycommentssByUsername = storycommentssByUsername;
    }

    @OneToOne(mappedBy = "accountuserByWriter")
    public UserprofileEntity getUserprofileByUsername() {
        return userprofileByUsername;
    }

    public void setUserprofileByUsername(UserprofileEntity userprofileByUsername) {
        this.userprofileByUsername = userprofileByUsername;
    }

    @OneToMany(mappedBy = "accountuserByViewedby")
    public Collection<ViewedEntity> getViewedsByUsername() {
        return viewedsByUsername;
    }

    public void setViewedsByUsername(Collection<ViewedEntity> viewedsByUsername) {
        this.viewedsByUsername = viewedsByUsername;
    }
}
