package Model;

/**
 * Created by DELL on 7/7/2017.
 */
public class Follow {
    private String username;
    private boolean isFollowed;

    public Follow(String username, boolean isFollowed) {
        this.username = username;
        this.isFollowed = isFollowed;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "username='" + username + '\'' +
                ", isFollowed=" + isFollowed +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isFollowed() {
        return isFollowed;
    }

    public void setFollowed(boolean followed) {
        isFollowed = followed;
    }
}
