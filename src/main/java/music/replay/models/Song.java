package music.replay.models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "topsongsinfo", schema = "public")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "songname")
    private String songName;

    @Column(name = "artist")
    private String artist;

    @Column(name = "album")
    private String album;

    @Column(name = "releasedate")
    private LocalDate releaseDate;

    @Column(name = "genre")
    private String genre;

    @Column(name = "coverimageurl")
    private String coverImageUrl;

    @Column(name = "pitchforkalbumrating")
    @Nullable
    private float pitchforkAlbumRating;

    public Song() {
    }

    public Song(int id, String songName, String artist, String album, LocalDate releaseDate, String genre, String coverImageUrl, float pitchforkAlbumRating) {
        this.id = id;
        this.songName = songName;
        this.artist = artist;
        this.album = album;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.coverImageUrl = coverImageUrl;
        this.pitchforkAlbumRating = pitchforkAlbumRating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public float getPitchforkAlbumRating() {
        return pitchforkAlbumRating;
    }

    public void setPitchforkAlbumRating(float pitchforkAlbumRating) {
        this.pitchforkAlbumRating = pitchforkAlbumRating;
    }
}
