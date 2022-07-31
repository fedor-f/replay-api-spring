package music.replay.models;

public class FavoritesInfo {

    private Integer userId;
    private Integer songId;

    public FavoritesInfo(Integer userId, Integer songId) {
        this.userId = userId;
        this.songId = songId;
    }

    public FavoritesInfo() {
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
