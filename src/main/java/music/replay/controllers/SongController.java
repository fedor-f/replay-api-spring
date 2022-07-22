package music.replay.controllers;

import music.replay.models.Song;
import music.replay.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/songs")
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public List<Song> getSong() {
        return songService.getSongs();
    }

    @PostMapping
    public List<Song> addNewSong(@RequestBody Song song) {
        return songService.addNewSong(song);
    }

    @DeleteMapping
    public List<Song> deleteSong(@RequestBody Integer id) {
        return songService.deleteSong(id);
    }

    @PutMapping
    public Song updateSong(@RequestBody Song song) {
        return songService.updateSong(song);
    }

    @GetMapping(path = "/genres")
    public List<String> getGenres() {
        return songService.getGenres();
    }

    @GetMapping(path = "/artists")
    public List<String> getArtists() {
        return songService.getArtists();
    }

    @GetMapping(path = "/albums")
    public List<String> getAlbums() {
        return songService.getAlbums();
    }

    @GetMapping(path = "/top")
    public List<Song> getTopSongs() {
        return songService.getSongsByAlbumRating();
    }
}
