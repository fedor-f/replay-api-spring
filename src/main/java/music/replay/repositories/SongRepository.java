package music.replay.repositories;

import music.replay.models.Song;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {

    @Query(value = "SELECT DISTINCT genre FROM topsongsinfo", nativeQuery = true)
    List<String> getAllGenres();

    @Query(value = "SELECT DISTINCT artist FROM topsongsinfo", nativeQuery = true)
    List<String> getAllArtists();

    @Query(value = "SELECT DISTINCT album FROM topsongsinfo", nativeQuery = true)
    List<String> getAllAlbums();

    @Query("SELECT s FROM Song s")
    List<Song> getSongByRating(Sort sort);
}
