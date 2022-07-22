package music.replay.services;

import music.replay.models.Song;
import music.replay.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    private final SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }
    public List<Song> getSongs() {
        return songRepository.findAll();
    }

    public List<Song> addNewSong(Song song) {
        boolean doesSongNameExist =
                songRepository.findAll().stream().anyMatch(s -> s.getSongName().equals(song.getSongName()));
        boolean doesAlbumNameExist =
                songRepository.findAll().stream().anyMatch(s -> s.getAlbum().equals(song.getAlbum()));
        boolean doesArtistExist =
                songRepository.findAll().stream().anyMatch(s -> s.getArtist().equals(song.getArtist()));

        if (song == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request body is null");
        }

        if (doesSongNameExist && doesAlbumNameExist && doesArtistExist) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The item already exists");
        }

        songRepository.save(song);

        return songRepository.findAll();
    }

    public List<Song> deleteSong(Integer id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request body is null");
        }

        boolean doesIdExist =
                songRepository.findAll().stream().anyMatch(s -> s.getId() == id);

        if (!doesIdExist) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A song with given id does not exist");
        }

        songRepository.deleteById(id);

        return songRepository.findAll();
    }

    public Song updateSong(Song song) {
        if (song == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request body is null");
        }

        Optional<Song> songOptional = songRepository.findById(song.getId());

        songOptional.ifPresent(value -> {
            value.setAlbum(song.getAlbum());
            value.setArtist(song.getArtist());
            value.setSongName(song.getSongName());
            value.setReleaseDate(song.getReleaseDate());
            value.setPitchforkAlbumRating(song.getPitchforkAlbumRating());
            value.setCoverImageUrl(song.getCoverImageUrl());
            value.setGenre(song.getGenre());
        });

        if (songOptional.isPresent()) {
            songRepository.save(songOptional.get());
            return songOptional.get();
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request body is null");
    }

    public List<String> getGenres() {
        return songRepository.getAllGenres();
    }

    public List<String> getArtists() {
        return songRepository.getAllArtists();
    }

    public List<String> getAlbums() {
        return songRepository.getAllAlbums();
    }

    public List<Song> getSongsByAlbumRating() {
        return songRepository.getSongByRating(Sort.by("pitchforkAlbumRating").descending());
    }

    public List<Song> getSongsByArtist(String artist) {
        return songRepository.getRecordsByArtist(artist);
    }

    public List<Song> getSongsByAlbum(String album) {
        return songRepository.getRecordsByAlbum(album);
    }

    public List<Song> getSongsByGenre(String genre) {
        return songRepository.getRecordsByGenre(genre);
    }
}
