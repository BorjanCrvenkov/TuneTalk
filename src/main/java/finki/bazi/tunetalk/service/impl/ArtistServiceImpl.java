package finki.bazi.tunetalk.service.impl;


import finki.bazi.tunetalk.model.Album;
import finki.bazi.tunetalk.model.Artist;
import finki.bazi.tunetalk.model.Song;
import finki.bazi.tunetalk.model.exceptions.ArtistNameAlreadyExistsException;
import finki.bazi.tunetalk.repository.ArtistRepository;
import finki.bazi.tunetalk.service.ArtistService;
import org.springframework.stereotype.Service;

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
    public Artist findArtistById(int artistId) {
        return artistRepository.findByArtistId(artistId);
    }

    @Override
    public List<Song> getAllSongsByArtistId(int artistId) {
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

        return artistRepository.findAllById(artistIds);
    }

    @Override
    public List<Artist> findArtistsBySongId(Integer songId) {
        List<Integer> artistIds = artistRepository.findArtistIdBySongId(songId);

        return artistRepository.findAllById(artistIds);
    }

    private boolean checkIfArtistNameExists(String name) {
        return artistRepository.getArtistByArtistName(name) != null;
    }

    @Override
    public Artist createNewArtist(String artistName, String realName, Integer age, String description) {
        Artist artist = new Artist(artistName,realName,age,description);
        if(this.checkIfArtistNameExists(artistName)){
            throw new ArtistNameAlreadyExistsException(artistName);
        }
        return artistRepository.save(artist);
    }

    @Override
    public void updateArtist(Integer id,String artistName, String realName, Integer age, String description) {
        Artist artist = this.findArtistById(id);
        artist.setArtistName(artistName);
        artist.setRealName(realName);
        artist.setAge(age);
        artist.setDescription(description);
        this.artistRepository.save(artist);
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
        return artistRepository.findAllById(artistIds);
    }

    @Override
    public void verifyArtist(Integer artistId) {
        artistRepository.verifyArtist(artistId);
    }

    @Override
    public void unverifyArtist(Integer artistId) {
        artistRepository.unverifyArtist(artistId);
    }

}