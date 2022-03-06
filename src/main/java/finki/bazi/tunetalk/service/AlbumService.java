package finki.bazi.tunetalk.service;

import finki.bazi.tunetalk.model.Album;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

public interface AlbumService {

    Album findById(int albumId);

    List<Album> findAllAlbums();

    List<Album> findAllAlbumsByArtistId(Integer artistId);

    List<Album> findAllFiltered(String artistNameContains, Integer yearReleased, String genre);

    Album findAlbumBySongId(Integer songId);

    Album createNewAlbum(String albumName, LocalDate dateReleased, float rating, String albumImage, Integer artistId);

    void deleteArtistFromAlbum(Integer artistId, Integer albumId);

    void deleteSongFromAlbum(Integer songId, Integer albumId);

    void addGenreToAlbum(Integer genreId, Integer albumId);

    void deleteGenreFromAlbum(Integer genreId, Integer albumId);

    Album updateAlbum(Integer albumId, String albumName, LocalDate dateReleased, float rating, String albumImage);

    List<Album> findAlbumsByGenreId(Integer genreId);

    void verifyAlbum(Integer albumId);

    void unverifyAlbum(Integer albumId);

    Specification<Album> nameContains(String text);

    Specification<Album> yearReleased(Integer yearReleased);
}
