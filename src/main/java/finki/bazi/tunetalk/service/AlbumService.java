package finki.bazi.tunetalk.service;


import finki.bazi.tunetalk.model.Album;

import java.time.LocalDate;
import java.util.List;

public interface AlbumService {

    Album findById(int albumId);

    List<Album> findAllAlbums();

    List<Album> findAllAlbumsByArtistId(Integer artistId);

    Album findAlbumBySongId(Integer songId);

    Album createNewAlbum(String albumName,LocalDate dateReleased, float rating, int artistId);

    void deleteArtistFromAlbum(Integer artistId,Integer albumId);

    void deleteSongFromAlbum(Integer songId,Integer albumId);

    void addGenreToAlbum(Integer genreId,Integer albumId);

    void deleteGenreFromAlbum(Integer genreId,Integer albumId);

    void updateAlbum(Integer albumId,String albumName,LocalDate dateReleased,float rating);

    List<Album> findAlbumsByGenreId(Integer genreId);

    void verifyAlbum(Integer albumId);
    void unverifyAlbum(Integer albumId);
}
