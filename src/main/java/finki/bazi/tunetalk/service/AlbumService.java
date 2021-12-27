package finki.bazi.tunetalk.service;


import finki.bazi.tunetalk.model.Album;
import finki.bazi.tunetalk.model.Song;

import java.time.LocalDate;
import java.util.List;

public interface AlbumService {

    Album findById(int albumId);

    List<Album> findAllAlbums();

    List<Album> findAllAlbumsByName(String albumName);

    List<Album> findAlbumsByDate(LocalDate dateReleased);

    List<Album> findAlbumsByRating(float rating);

    List<Album> findByVerification(boolean verified);

    List<Song> getAllSongsInAlbum(int albumId);

    Song addSongToAlbum(Song song,Album album);

    Song removeSongFromAlbum(Song song,Album album);

    void changeAlbumRating(int albumId,float rating);

    void changeAlbumName(int albumId,String albumName);

    void changeAlbumVerified(int albumId,boolean verified);

    void changeAlbumDateReleased(int albumId,LocalDate dateReleased);

    List<Album> findAllAlbumsByArtistId(Integer artistId);

    Album findAlbumBySongId(Integer songId);

    Album addNewAlbum(String albumName,LocalDate dateReleased, float rating, int artistId);

    void deleteArtistFromAlbum(Integer artistId,Integer albumId);

    void deleteSongFromAlbum(Integer songId,Integer albumId);

    void addGenreToAlbum(Integer genreId,Integer albumId);

    void deleteGenreFromAlbum(Integer genreId,Integer albumId);
}
