package finki.bazi.tunetalk.service;


import finki.bazi.tunetalk.model.Album;
import finki.bazi.tunetalk.model.Artist;
import finki.bazi.tunetalk.model.Song;

import java.util.List;

public interface ArtistService {

    List<Artist> findAll();

    Artist findArtistById(int artistId);

    List<Song> getAllSongsByArtistId(int artistId);

    Album removeAlbumFromArtist(Album album,Artist artist);

    List<Album> getAllAlbumsByArtistId(int artistId);

    List<Artist> getArtistsByAlbumId(Integer albumId);

    List<Artist> findArtistsBySongId(Integer songId);

    void createNewArtist(String artistName, String realName, Integer age,String description);

    void updateArtist(Integer id, String artistName, String realName, Integer age,String description);

    void addArtistToSong(Integer songId,Integer artistId);

    void addArtistToAlbum(Integer artistId,Integer albumId);

    List<Artist> findArtistsByAlbumId(Integer albumId);

}
