package music.replay.repositories;

import music.replay.models.Song;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {

    @Query(value = "SELECT DISTINCT genre FROM topsongsinfo", nativeQuery = true)
    List<String> getAllGenres();

    @Query(value = "SELECT DISTINCT artist FROM topsongsinfo", nativeQuery = true)
    List<String> getAllArtists();

    @Query(value = "SELECT DISTINCT album FROM topsongsinfo", nativeQuery = true)
    List<String> getAllAlbums();

    @Query("SELECT s FROM Song s WHERE s.artist = :artist")
    List<Song> getRecordsByArtist(@Param("artist") String artist);

    @Query("SELECT s FROM Song s WHERE s.album = :album")
    List<Song> getRecordsByAlbum(@Param("album") String album);

    @Query("SELECT s FROM Song s WHERE s.genre = :genre")
    List<Song> getRecordsByGenre(@Param("genre") String genre);

    @Query("SELECT s FROM Song s WHERE s.artist = :artist AND s.album = :album AND s.genre = :genre")
    List<Song> getSortedRecordsByCriteria(@Param("artist") String artist,
                                          @Param("album") String album,
                                          @Param("genre") String genre,
                                          Sort sort);

    @Query("SELECT s FROM Song s")
    List<Song> getAllSortedRecords(Sort sort);

    @Query("SELECT s FROM Song s WHERE s.artist = :artist AND s.album = :album AND s.genre = :genre")
    List<Song> getSongsByParameters(@Param("artist") String artist,
                                    @Param("album") String album,
                                    @Param("genre") String genre);

    @Query(value =
            "SELECT * FROM topsongsinfo " +
            "WHERE topsongsinfo.id in (SELECT favorites.song_id FROM favorites WHERE favorites.user_id in" +
            "(SELECT users.id FROM users WHERE users.username = :name))",
            nativeQuery = true)
    List<Song> getSongsFromFavorites(@Param("name") String name);

    @Modifying
    @Query(value = "INSERT INTO favorites VALUES (:user, :song)", nativeQuery = true)
    void addToFavorites(@Param("user") Integer user, @Param("song") Integer song);

    @Modifying
    @Query(value = "DELETE FROM favorites WHERE user_id = :user and song_id = :song", nativeQuery = true)
    void removeFromFavorites(@Param("user") Integer user, @Param("song") Integer song);
}
