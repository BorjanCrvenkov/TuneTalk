package finki.bazi.tunetalk.service;


import finki.bazi.tunetalk.model.Artist;
import finki.bazi.tunetalk.model.Song;

import java.time.LocalDate;
import java.util.List;

public interface SongService {

    Song findById(int songId);

    List<Song> findAllSongs();

    void changeSongDateReleased(int songId,LocalDate dateReleased);

    List<Song> findAllSongsByArtistId(Integer artistId);

    List<Song> findAllSongsByAlbumId(Integer albumId);

    void deleteArtistFromSong(Integer artistId,Integer songId);

    void addGenreToSong(Integer genreId,Integer songId);

    void deleteGenreFromSong(Integer genreId,Integer songId);

    void updateSong(Integer songId,String songTitle,LocalDate dateReleased,float rating,String lyrics);

    List<Song> findSongsByGenreId(Integer genreId);

    void verifySong(Integer songId);
    void unverifySong(Integer songId);

    Song createNewSong(String title, LocalDate dateReleased, float rating, String lyrics);
}
