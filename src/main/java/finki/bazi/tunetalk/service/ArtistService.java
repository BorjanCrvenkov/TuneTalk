package finki.bazi.tunetalk.service;


import finki.bazi.tunetalk.model.Album;
import finki.bazi.tunetalk.model.Artist;
import finki.bazi.tunetalk.model.Song;

import java.util.List;

public interface ArtistService {

    List<Artist> findAll();

    void changeArtistName(int artistId,String name);

    void changeArtistAge(int artistId,int age);

    void changeArtistRealName(int artistId,String realName);

    void changeArtistDescription(int artistId,String description);

    void changeArtistVerified(int artistId,boolean verified);

    Artist findArtistById(int artistId);

    List<Artist> getAllArtistsByAge(int age);

    List<Artist> getAllArtistsByRealName(String realName);

    List<Artist> getAllArtistsByVerified(boolean verified);

    List<Song> getAllSongsByArtistId(int artistId);

    Song addSongToArtist(Song song,Artist artist);

    Song removeSongFromArtist(Song song,Artist artist);

    Album addAlbumToArtist(Album album, Artist artist);

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
