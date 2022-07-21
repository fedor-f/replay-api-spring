package music.replay.services;

import music.replay.models.Song;
import music.replay.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
