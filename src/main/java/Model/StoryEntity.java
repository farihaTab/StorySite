package Model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;

/**
 * Created by DELL on 5/13/2017.
 */
@Entity
@Table(name = "STORY", schema = "SDPROJECT")
public class StoryEntity {
    private long storyid;
    private String writerid;
    private String title;
    private String lang;
    private String description;
    private Long firstchapter;
    private Long lastchapter;
    private String categoryname;
    private Boolean ismature;
    private Boolean ispublished;
    private Boolean iscompleted;
    private Long readcount;
    private Short chaptercount;
    private Long commentcount;
    private Long likecount;
    private Long rating;
    private Long ratedbycount;
    private String storycopyright;
    private Time createdat;
    private Time updatedat;
    private Collection<ChapterEntity> chaptersByStoryid;
    private Collection<InterestEntity> interestsByStoryid;
    private Collection<LikesEntity> likesByStoryid;
    private Collection<RatesEntity> ratesByStoryid;
    private Collection<ReadingEntity> readingsByStoryid;
    private Collection<ReadinglistdetailsEntity> readinglistdetailssByStoryid;
    private AccountuserEntity accountuserByWriterid;
    private LanguagetableEntity languagetableByLang;
    private CategoryEntity categoryByCategoryname;
    private CopyrighttypeEntity copyrighttypeByStorycopyright;
    private Collection<StorycommentsEntity> storycommentssByStoryid;
    private Collection<StorytagEntity> storytagsByStoryid;
    private Collection<ViewedEntity> viewedsByStoryid;

    public StoryEntity() {
    }

    @Id
    @Column(name = "STORYID", nullable = false, precision = 0)
    public long getStoryid() {
        return storyid;
    }

    public void setStoryid(long storyid) {
        this.storyid = storyid;
    }

    @Basic
    @Column(name = "WRITERID", nullable = true, length = 20)
    public String getWriterid() {
        return writerid;
    }

    public void setWriterid(String writerid) {
        this.writerid = writerid;
    }

    @Basic
    @Column(name = "TITLE", nullable = false, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "LANG", nullable = false, length = 30)
    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 4000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "FIRSTCHAPTER", nullable = true, precision = 0)
    public Long getFirstchapter() {
        return firstchapter;
    }

    public void setFirstchapter(Long firstchapter) {
        this.firstchapter = firstchapter;
    }

    @Basic
    @Column(name = "LASTCHAPTER", nullable = true, precision = 0)
    public Long getLastchapter() {
        return lastchapter;
    }

    public void setLastchapter(Long lastchapter) {
        this.lastchapter = lastchapter;
    }

    @Basic
    @Column(name = "CATEGORYNAME", nullable = true, length = 30)
    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    @Basic
    @Column(name = "ISMATURE", nullable = true, precision = 0)
    public Boolean getIsmature() {
        return ismature;
    }

    public void setIsmature(Boolean ismature) {
        this.ismature = ismature;
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
    @Column(name = "ISCOMPLETED", nullable = true, precision = 0)
    public Boolean getIscompleted() {
        return iscompleted;
    }

    public void setIscompleted(Boolean iscompleted) {
        this.iscompleted = iscompleted;
    }

    @Basic
    @Column(name = "READCOUNT", nullable = true, precision = 0)
    public Long getReadcount() {
        return readcount;
    }

    public void setReadcount(Long readcount) {
        this.readcount = readcount;
    }

    @Basic
    @Column(name = "CHAPTERCOUNT", nullable = true, precision = 0)
    public Short getChaptercount() {
        return chaptercount;
    }

    public void setChaptercount(Short chaptercount) {
        this.chaptercount = chaptercount;
    }

    @Basic
    @Column(name = "COMMENTCOUNT", nullable = true, precision = 0)
    public Long getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(Long commentcount) {
        this.commentcount = commentcount;
    }

    @Basic
    @Column(name = "LIKECOUNT", nullable = true, precision = 0)
    public Long getLikecount() {
        return likecount;
    }

    public void setLikecount(Long likecount) {
        this.likecount = likecount;
    }

    @Basic
    @Column(name = "RATING", nullable = true, precision = 1)
    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "RATEDBYCOUNT", nullable = true, precision = 0)
    public Long getRatedbycount() {
        return ratedbycount;
    }

    public void setRatedbycount(Long ratedbycount) {
        this.ratedbycount = ratedbycount;
    }

    @Basic
    @Column(name = "STORYCOPYRIGHT", nullable = true, length = 40)
    public String getStorycopyright() {
        return storycopyright;
    }

    public void setStorycopyright(String storycopyright) {
        this.storycopyright = storycopyright;
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
    public String toString() {
        return "StoryEntity{" +
                "storyid=" + storyid +
                ", writerid='" + writerid + '\'' +
                ", title='" + title + '\'' +
                ", lang='" + lang + '\'' +
                ", description='" + description + '\'' +
                ", firstchapter=" + firstchapter +
                ", lastchapter=" + lastchapter +
                ", categoryname='" + categoryname + '\'' +
                ", ismature=" + ismature +
                ", ispublished=" + ispublished +
                ", iscompleted=" + iscompleted +
                ", readcount=" + readcount +
                ", chaptercount=" + chaptercount +
                ", commentcount=" + commentcount +
                ", likecount=" + likecount +
                ", rating=" + rating +
                ", ratedbycount=" + ratedbycount +
                ", storycopyright='" + storycopyright + '\'' +
                ", createdat=" + createdat +
                ", updatedat=" + updatedat +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoryEntity that = (StoryEntity) o;

        if (storyid != that.storyid) return false;
        if (writerid != null ? !writerid.equals(that.writerid) : that.writerid != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (lang != null ? !lang.equals(that.lang) : that.lang != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (firstchapter != null ? !firstchapter.equals(that.firstchapter) : that.firstchapter != null) return false;
        if (lastchapter != null ? !lastchapter.equals(that.lastchapter) : that.lastchapter != null) return false;
        if (categoryname != null ? !categoryname.equals(that.categoryname) : that.categoryname != null) return false;
        if (ismature != null ? !ismature.equals(that.ismature) : that.ismature != null) return false;
        if (ispublished != null ? !ispublished.equals(that.ispublished) : that.ispublished != null) return false;
        if (iscompleted != null ? !iscompleted.equals(that.iscompleted) : that.iscompleted != null) return false;
        if (readcount != null ? !readcount.equals(that.readcount) : that.readcount != null) return false;
        if (chaptercount != null ? !chaptercount.equals(that.chaptercount) : that.chaptercount != null) return false;
        if (commentcount != null ? !commentcount.equals(that.commentcount) : that.commentcount != null) return false;
        if (likecount != null ? !likecount.equals(that.likecount) : that.likecount != null) return false;
        if (rating != null ? !rating.equals(that.rating) : that.rating != null) return false;
        if (ratedbycount != null ? !ratedbycount.equals(that.ratedbycount) : that.ratedbycount != null) return false;
        if (storycopyright != null ? !storycopyright.equals(that.storycopyright) : that.storycopyright != null)
            return false;
        if (createdat != null ? !createdat.equals(that.createdat) : that.createdat != null) return false;
        if (updatedat != null ? !updatedat.equals(that.updatedat) : that.updatedat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (storyid ^ (storyid >>> 32));
        result = 31 * result + (writerid != null ? writerid.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (lang != null ? lang.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (firstchapter != null ? firstchapter.hashCode() : 0);
        result = 31 * result + (lastchapter != null ? lastchapter.hashCode() : 0);
        result = 31 * result + (categoryname != null ? categoryname.hashCode() : 0);
        result = 31 * result + (ismature != null ? ismature.hashCode() : 0);
        result = 31 * result + (ispublished != null ? ispublished.hashCode() : 0);
        result = 31 * result + (iscompleted != null ? iscompleted.hashCode() : 0);
        result = 31 * result + (readcount != null ? readcount.hashCode() : 0);
        result = 31 * result + (chaptercount != null ? chaptercount.hashCode() : 0);
        result = 31 * result + (commentcount != null ? commentcount.hashCode() : 0);
        result = 31 * result + (likecount != null ? likecount.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (ratedbycount != null ? ratedbycount.hashCode() : 0);
        result = 31 * result + (storycopyright != null ? storycopyright.hashCode() : 0);
        result = 31 * result + (createdat != null ? createdat.hashCode() : 0);
        result = 31 * result + (updatedat != null ? updatedat.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "storyByBelongsto")
    public Collection<ChapterEntity> getChaptersByStoryid() {
        return chaptersByStoryid;
    }

    public void setChaptersByStoryid(Collection<ChapterEntity> chaptersByStoryid) {
        this.chaptersByStoryid = chaptersByStoryid;
    }

    @OneToMany(mappedBy = "storyByInterestedin")
    public Collection<InterestEntity> getInterestsByStoryid() {
        return interestsByStoryid;
    }

    public void setInterestsByStoryid(Collection<InterestEntity> interestsByStoryid) {
        this.interestsByStoryid = interestsByStoryid;
    }

    @OneToMany(mappedBy = "storyByLikedstory")
    public Collection<LikesEntity> getLikesByStoryid() {
        return likesByStoryid;
    }

    public void setLikesByStoryid(Collection<LikesEntity> likesByStoryid) {
        this.likesByStoryid = likesByStoryid;
    }

    @OneToMany(mappedBy = "storyByRatedstory")
    public Collection<RatesEntity> getRatesByStoryid() {
        return ratesByStoryid;
    }

    public void setRatesByStoryid(Collection<RatesEntity> ratesByStoryid) {
        this.ratesByStoryid = ratesByStoryid;
    }

    @OneToMany(mappedBy = "storyByReadstory")
    public Collection<ReadingEntity> getReadingsByStoryid() {
        return readingsByStoryid;
    }

    public void setReadingsByStoryid(Collection<ReadingEntity> readingsByStoryid) {
        this.readingsByStoryid = readingsByStoryid;
    }

    @OneToMany(mappedBy = "storyByStoryinlist")
    public Collection<ReadinglistdetailsEntity> getReadinglistdetailssByStoryid() {
        return readinglistdetailssByStoryid;
    }

    public void setReadinglistdetailssByStoryid(Collection<ReadinglistdetailsEntity> readinglistdetailssByStoryid) {
        this.readinglistdetailssByStoryid = readinglistdetailssByStoryid;
    }

    @ManyToOne
    @JoinColumn(name = "WRITERID", referencedColumnName = "USERNAME", insertable = false, updatable = false)
    public AccountuserEntity getAccountuserByWriterid() {
        return accountuserByWriterid;
    }

    public void setAccountuserByWriterid(AccountuserEntity accountuserByWriterid) {
        this.accountuserByWriterid = accountuserByWriterid;
    }

    @ManyToOne
    @JoinColumn(name = "LANG", referencedColumnName = "LANGUAGEID", nullable = false,  insertable = false, updatable = false)
    public LanguagetableEntity getLanguagetableByLang() {
        return languagetableByLang;
    }

    public void setLanguagetableByLang(LanguagetableEntity languagetableByLang) {
        this.languagetableByLang = languagetableByLang;
    }

    @ManyToOne
    @JoinColumn(name = "CATEGORYNAME", referencedColumnName = "CATNAME", insertable = false, updatable = false)
    public CategoryEntity getCategoryByCategoryname() {
        return categoryByCategoryname;
    }

    public void setCategoryByCategoryname(CategoryEntity categoryByCategoryname) {
        this.categoryByCategoryname = categoryByCategoryname;
    }

    @ManyToOne
    @JoinColumn(name = "STORYCOPYRIGHT", referencedColumnName = "COPYRIGHT", insertable = false, updatable = false)
    public CopyrighttypeEntity getCopyrighttypeByStorycopyright() {
        return copyrighttypeByStorycopyright;
    }

    public void setCopyrighttypeByStorycopyright(CopyrighttypeEntity copyrighttypeByStorycopyright) {
        this.copyrighttypeByStorycopyright = copyrighttypeByStorycopyright;
    }

    @OneToMany(mappedBy = "storyByScommentedon")
    public Collection<StorycommentsEntity> getStorycommentssByStoryid() {
        return storycommentssByStoryid;
    }

    public void setStorycommentssByStoryid(Collection<StorycommentsEntity> storycommentssByStoryid) {
        this.storycommentssByStoryid = storycommentssByStoryid;
    }

    @OneToMany(mappedBy = "storyByTaggedstory")
    public Collection<StorytagEntity> getStorytagsByStoryid() {
        return storytagsByStoryid;
    }

    public void setStorytagsByStoryid(Collection<StorytagEntity> storytagsByStoryid) {
        this.storytagsByStoryid = storytagsByStoryid;
    }

    @OneToMany(mappedBy = "storyByViewedstory")
    public Collection<ViewedEntity> getViewedsByStoryid() {
        return viewedsByStoryid;
    }

    public void setViewedsByStoryid(Collection<ViewedEntity> viewedsByStoryid) {
        this.viewedsByStoryid = viewedsByStoryid;
    }

    public StoryEntity(String categoryname, Short chaptercount, Long commentcount, Time createdat, String description, Long firstchapter, Boolean iscompleted, Boolean ismature, Boolean ispublished, String lang, Long lastchapter, Long likecount, Long ratedbycount, Long rating, Long readcount, String storycopyright, long storyid, String title, Time updatedat, String writerid) {
        this.categoryname = categoryname;
        this.chaptercount = chaptercount;
        this.commentcount = commentcount;
        this.createdat = createdat;
        this.description = description;
        this.firstchapter = firstchapter;
        this.iscompleted = iscompleted;
        this.ismature = ismature;
        this.ispublished = ispublished;
        this.lang = lang;
        this.lastchapter = lastchapter;
        this.likecount = likecount;
        this.ratedbycount = ratedbycount;
        this.rating = rating;
        this.readcount = readcount;
        this.storycopyright = storycopyright;
        this.storyid = storyid;

        this.title = title;
        this.updatedat = updatedat;
        this.writerid = writerid;
    }


    //***********************TAMANNA********************

    private String tags;
    @Transient
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
