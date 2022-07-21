package music.replay.services;

import music.replay.models.Song;
import music.replay.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        songRepository.save(song);

        return songRepository.findAll();
    }

    public List<Song> deleteSong(Integer id) {
        songRepository.deleteById(id);

        return songRepository.findAll();
    }

    public Song updateSong(Song song) {
        Optional<Song> uhh = songRepository.findById(song.getId());

        uhh.ifPresent(value -> {
            value.setAlbum(song.getAlbum());
            value.setArtist(song.getArtist());
            value.setSongName(song.getSongName());
            value.setReleaseDate(song.getReleaseDate());
            value.setPitchforkAlbumRating(song.getPitchforkAlbumRating());
            value.setCoverImageUrl(song.getCoverImageUrl());
            value.setGenre(song.getGenre());
        });

        songRepository.save(uhh.get());
        return uhh.get();
    }
}
