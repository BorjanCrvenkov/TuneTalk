package finki.bazi.tunetalk.service;

import finki.bazi.tunetalk.model.Album;
import finki.bazi.tunetalk.model.Artist;
import finki.bazi.tunetalk.model.Song;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

public interface ArtistService {

    List<Artist> findAll();

    Artist findArtistById(int artistId);

    List<Artist> findAllFiltered(String artistNameContains, Integer artistAge, String genre);

    List<Song> getAllSongsByArtistId(int artistId);

    Album removeAlbumFromArtist(Album album, Artist artist);

    List<Album> getAllAlbumsByArtistId(int artistId);

    List<Artist> getArtistsByAlbumId(Integer albumId);

    List<Artist> findArtistsBySongId(Integer songId);

    Artist createNewArtist(String artistName, String realName, Integer age, String description);

    void updateArtist(Integer id, String artistName, String realName, Integer age, String description);

    void addArtistToSong(Integer songId, Integer artistId);

    void addArtistToAlbum(Integer artistId, Integer albumId);

    List<Artist> findArtistsByAlbumId(Integer albumId);

    void verifyArtist(Integer artistId);

    void unverifyArtist(Integer artistId);

    Specification<Artist> nameContains(String text);

    Specification<Artist> artistAgeIs(Integer age);
}