package finki.bazi.tunetalk.service.impl;


import finki.bazi.tunetalk.model.Album;
import finki.bazi.tunetalk.model.Artist;
import finki.bazi.tunetalk.model.Song;
import finki.bazi.tunetalk.model.exceptions.ArtistNameAlreadyExistsException;
import finki.bazi.tunetalk.repository.ArtistRepository;
import finki.bazi.tunetalk.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    @Override
    public void changeArtistName(int artistId, String name) {
        Artist artist = findArtistById(artistId);
        artist.setArtistName(name);
    }

    @Override
    public void changeArtistAge(int artistId, int age) {
        Artist artist = findArtistById(artistId);
        artist.setAge(age);
    }

    @Override
    public void changeArtistRealName(int artistId, String realName) {
        Artist artist = findArtistById(artistId);
        artist.setRealName(realName);
    }

    @Override
    public void changeArtistDescription(int artistId, String description) {
        Artist artist = findArtistById(artistId);
        artist.setDescription(description);
    }

    @Override
    public void changeArtistVerified(int artistId,boolean verified) {
        Artist artist = findArtistById(artistId);
        artist.setVerified(verified);
    }

    @Override
    public Artist findArtistById(int artistId) {
        return artistRepository.findByArtistId(artistId);
    }

    @Override
    public List<Artist> getAllArtistsByAge(int age) {
        return artistRepository.findAllByAge(age);
    }

    @Override
    public List<Artist> getAllArtistsByRealName(String realName) {
        return artistRepository.findAllByRealName(realName);
    }

    @Override
    public List<Artist> getAllArtistsByVerified(boolean verified) {
        return artistRepository.findAllByVerified(verified);
    }

    @Override
    public List<Song> getAllSongsByArtistId(int artistId) {
        return null;
    }

    @Override
    public Song addSongToArtist(Song song, Artist artist) {
        return null;
    }

    @Override
    public Song removeSongFromArtist(Song song, Artist artist) {
        return null;
    }

    @Override
    public Album addAlbumToArtist(Album album, Artist artist) {
        return null;
    }

    @Override
    public Album removeAlbumFromArtist(Album album, Artist artist) {
        return null;
    }

    @Override
    public List<Album> getAllAlbumsByArtistId(int artistId) {
        return null;
    }

    @Override
    public List<Artist> getArtistsByAlbumId(Integer albumId) {
        List<Integer> artistIds = artistRepository.findArtistsIdByAlbumId(albumId);
        List<Artist> artists = new ArrayList<>();
        for(Integer i : artistIds){
            artists.add(this.findArtistById(i));
        }

        return artists;
    }

    @Override
    public List<Artist> findArtistsBySongId(Integer songId) {
        List<Integer> artistIds = artistRepository.findArtistIdBySongId(songId);
        List<Artist> artists = new ArrayList<>();

        for(Integer artistId : artistIds){
            artists.add(this.findArtistById(artistId));
        }

        return artists;
    }

    private boolean checkIfArtistNameExists(String name) {
        return artistRepository.getArtistByArtistName(name) != null;
    }

    @Override
    public void createNewArtist(String artistName, String realName, Integer age, String description) {
        Artist artist = new Artist(artistName,realName,age,description);
        if(this.checkIfArtistNameExists(artistName)){
            throw new ArtistNameAlreadyExistsException(artistName);
        }
        artistRepository.save(artist);
    }

    @Override
    public void updateArtist(Integer id,String artistName, String realName, Integer age, String description) {
        artistRepository.updateArtist(id, artistName, realName, age, description);
    }

    @Override
    public void addArtistToSong(Integer songId, Integer artistId) {
        artistRepository.addArtistToSong(songId,artistId);
    }


    @Override
    public void addArtistToAlbum(Integer artistId,Integer albumId) {
        artistRepository.addAlbumToArtist(artistId,albumId);
    }

    @Override
    public List<Artist> findArtistsByAlbumId(Integer albumId) {
        List<Integer> artistIds = artistRepository.findArtistIdByAlbumId(albumId);
        List<Artist> artists = new ArrayList<>();

        for(Integer artistId : artistIds){
            artists.add(this.findArtistById(artistId));
        }

        return artists;
    }

}