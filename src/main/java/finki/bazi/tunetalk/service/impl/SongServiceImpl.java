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
    public List<Song> findAllSongsByTitle(String title) {
        return songRepository.findAllByTitle(title);
    }

    @Override
    public List<Song> findSongsByDate(LocalDate dateReleased) {
        return songRepository.findAllByDateReleased(dateReleased);
    }

    @Override
    public List<Song> findSongsByRating(float rating) {
        return songRepository.findAllByRating(rating);
    }

    @Override
    public List<Song> findByVerification(boolean verified) {
        return songRepository.findAllByVerified(verified);
    }

    @Override
    public List<Artist> getAllSongArtists(int songId) {
        return null;
    }

    @Override
    public Song addSong(Song song) {
        return songRepository.save(song);
    }

    @Override
    public void changeSongRating(int songId, float rating) {

    }

    @Override
    public void changeSongTitle(int songId, String title) {

    }

    @Override
    public void changeSongVerified(int songId, boolean verified) {

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
}
