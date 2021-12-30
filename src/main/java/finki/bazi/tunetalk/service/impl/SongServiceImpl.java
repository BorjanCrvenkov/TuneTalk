package finki.bazi.tunetalk.service.impl;


import finki.bazi.tunetalk.model.Artist;
import finki.bazi.tunetalk.model.Song;
import finki.bazi.tunetalk.repository.SongRepository;
import finki.bazi.tunetalk.service.ArtistService;
import finki.bazi.tunetalk.service.SongService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final ArtistService artistService;

    public SongServiceImpl(SongRepository songRepository, ArtistService artistService) {
        this.songRepository = songRepository;
        this.artistService = artistService;
    }

    @Override
    public Song findById(int songId) {
        return songRepository.findBySongId(songId);
    }

    @Override
    public List<Song> findAllSongs() {
        return songRepository.findAll();
    }


    @Override
    public void changeSongDateReleased(int songId, LocalDate dateReleased) {

    }

    @Override
    public List<Song> findAllSongsByArtistId(Integer artistId) {
        List<Integer> songIds = songRepository.findAllSongsIdByArtistId(artistId);
        List<Song> songs = new ArrayList<>();

        for (Integer songId : songIds) {
            songs.add(this.findById(songId));
        }

        return songs;
    }

    @Override
    public List<Song> findAllSongsByAlbumId(Integer albumId) {
        List<Integer> songIds = songRepository.findAllSongsIdByAlbumId(albumId);
        List<Song> songs = new ArrayList<>();

        for (Integer songId : songIds) {
            songs.add(this.findById(songId));
        }

        return songs;
    }

    @Override
    public void deleteArtistFromSong(Integer artistId, Integer songId) {
        songRepository.deleteArtistFromSong(artistId,songId);
    }

    @Override
    public void addGenreToSong(Integer genreId, Integer songId) {
        songRepository.addGenreToSong(genreId,songId);
    }

    @Override
    public void deleteGenreFromSong(Integer genreId, Integer songId) {
        songRepository.deleteGenreFromSong(genreId, songId);
    }

    @Override
    public void updateSong(Integer songId, String songTitle, LocalDate dateReleased, float rating, String lyrics) {
        songRepository.updateSong(songId, songTitle, dateReleased, rating, lyrics);
    }

    @Override
    public List<Song> findSongsByGenreId(Integer genreId) {
        List<Integer> songIds = songRepository.findSongsByGenreId(genreId);
        List<Song> songs = new ArrayList<>();

        for(Integer id : songIds){
            songs.add(this.findById(id));
        }

        return songs;

    }

    @Override
    public void verifySong(Integer songId) {
        songRepository.verifySong(songId);
    }

    @Override
    public void unverifySong(Integer songId) {
        songRepository.unverifySong(songId);
    }
}
