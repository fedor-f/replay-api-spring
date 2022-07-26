package music.replay.services;

import music.replay.models.SearchSortParameters;
import music.replay.models.Song;
import music.replay.parser.SortParser;
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
        if (song == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request body is null");
        }

        boolean doesSongNameExist =
                songRepository.findAll().stream().anyMatch(s -> s.getSongName().equals(song.getSongName()));
        boolean doesAlbumNameExist =
                songRepository.findAll().stream().anyMatch(s -> s.getAlbum().equals(song.getAlbum()));
        boolean doesArtistExist =
                songRepository.findAll().stream().anyMatch(s -> s.getArtist().equals(song.getArtist()));

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

    public List<Song> getSongsByArtist(String artist) {
        if (artist == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Path variable is null");
        }

        boolean doesArtistExist =
                songRepository.findAll().stream().anyMatch(s -> s.getArtist().equals(artist));

        if (doesArtistExist) {
            return songRepository.getRecordsByArtist(artist);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist not found");
    }

    public List<Song> getSongsByAlbum(String album) {
        if (album == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Path variable is null");
        }

        boolean doesAlbumExist =
                songRepository.findAll().stream().anyMatch(s -> s.getAlbum().equals(album));

        if (doesAlbumExist) {
            return songRepository.getRecordsByAlbum(album);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Album not found");
    }

    public List<Song> getSongsByGenre(String genre) {
        if (genre == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Path variable is null");
        }

        if (genre.equals("rap")) {
            genre = "hip-hop/rap";
        }

        String finalGenre = genre;
        boolean doesGenreExist =
                songRepository.findAll().stream().anyMatch(s -> s.getGenre().equals(finalGenre));

        if (doesGenreExist) {
            return songRepository.getRecordsByGenre(genre);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found");
    }

    public List<Song> sortByCriteria(SearchSortParameters searchSortParameters) {
        if (searchSortParameters == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The request body is null");
        }

        try {
            SortParser.parseSortParams(searchSortParameters);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not resolve the given criteria");
        }

        boolean all = searchSortParameters.getArtistParam().equals("all")
                && searchSortParameters.getAlbumParam().equals("all")
                && searchSortParameters.getGenreParam().equals("all");

        if (all) {
            if (searchSortParameters.getOrder().equals("Ascending")) {
                return songRepository.getAllSortedRecords(Sort.by(searchSortParameters.getCriteria()));
            }

            return songRepository.getAllSortedRecords(Sort.by(searchSortParameters.getCriteria()).descending());
        }

        if (searchSortParameters.getOrder().equals("Ascending")) {
            return songRepository.getSortedRecordsByCriteria(searchSortParameters.getArtistParam(),
                    searchSortParameters.getAlbumParam(),
                    searchSortParameters.getGenreParam(),
                    Sort.by(searchSortParameters.getCriteria()));
        }

        return songRepository.getSortedRecordsByCriteria(searchSortParameters.getArtistParam(),
                searchSortParameters.getAlbumParam(),
                searchSortParameters.getGenreParam(),
                Sort.by(searchSortParameters.getCriteria()).descending());
    }

    public List<Song> getSongsBySearchParameters(SearchSortParameters searchSortParameters) {
        return songRepository.getSongsByParameters(searchSortParameters.getArtistParam(),
                searchSortParameters.getAlbumParam(), searchSortParameters.getGenreParam());
    }
}
