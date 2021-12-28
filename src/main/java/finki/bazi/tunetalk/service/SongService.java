package finki.bazi.tunetalk.service;


import finki.bazi.tunetalk.model.Artist;
import finki.bazi.tunetalk.model.Song;

import java.time.LocalDate;
import java.util.List;

public interface SongService {

    Song findById(int songId);

    List<Song> findAllSongs();

    List<Song> findAllSongsByTitle(String title);

    List<Song> findSongsByDate(LocalDate dateReleased);

    List<Song> findSongsByRating(float rating);

    List<Song> findByVerification(boolean verified);

    List<Artist> getAllSongArtists(int SongId);

    Song addSong(Song song);

    void changeSongRating(int songId,float rating);

    void changeSongTitle(int songId,String title);

    void changeSongVerified(int songId,boolean verified);

    void changeSongDateReleased(int songId,LocalDate dateReleased);

    List<Song> findAllSongsByArtistId(Integer artistId);

    List<Song> findAllSongsByAlbumId(Integer albumId);

    void deleteArtistFromSong(Integer artistId,Integer songId);

    void addGenreToSong(Integer genreId,Integer songId);

    void deleteGenreFromSong(Integer genreId,Integer songId);

    void updateSong(Integer songId,String songTitle,LocalDate dateReleased,float rating,String lyrics);

    List<Song> findSongsByGenreId(Integer genreId);
}
