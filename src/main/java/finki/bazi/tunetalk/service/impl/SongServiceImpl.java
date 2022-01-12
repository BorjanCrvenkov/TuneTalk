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
        return this.songRepository.findAllById(songIds);
    }

    @Override
    public List<Song> findAllSongsByAlbumId(Integer albumId) {
        List<Integer> songIds = songRepository.findAllSongsIdByAlbumId(albumId);
        return this.songRepository.findAllById(songIds);
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
        return this.songRepository.findAllById(songIds);

    }

    @Override
    public void verifySong(Integer songId) {
        songRepository.verifySong(songId);
    }

    @Override
    public void unverifySong(Integer songId) {
        songRepository.unverifySong(songId);
    }

    @Override
    public Song createNewSong(String title, LocalDate dateReleased, float rating, String lyrics) {
        Song song = new Song(title,dateReleased,rating,lyrics,false);
        return this.songRepository.save(song);
    }
}
