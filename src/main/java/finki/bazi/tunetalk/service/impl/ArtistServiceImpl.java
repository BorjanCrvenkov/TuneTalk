package finki.bazi.tunetalk.service.impl;

import finki.bazi.tunetalk.model.Album;
import finki.bazi.tunetalk.model.Artist;
import finki.bazi.tunetalk.model.Song;
import finki.bazi.tunetalk.model.exceptions.ArtistNameAlreadyExistsException;
import finki.bazi.tunetalk.model.metamodel.Artist_;
import finki.bazi.tunetalk.repository.ArtistRepository;
import finki.bazi.tunetalk.service.ArtistService;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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
    public List<Artist> findAllFiltered(String artistNameContains, Integer artistAge, String genre) {
        Specification<Artist> artistFilterSpecification = nameContains(
                artistNameContains == null ? "" : artistNameContains);
        if (artistAge != null) {
            artistFilterSpecification = artistFilterSpecification.and(artistAgeIs(artistAge));
        }
        // if (genre != null) {
        //     artistFilterSpecification = artistFilterSpecification.and(artistGenreContains(artistGenre));
        // }
        var artists = artistRepository.findAll(artistFilterSpecification);
        return artists;

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
    public Artist createNewArtist(String artistName, String realName, String description, LocalDate birthday, String artistImage) {
        Artist artist = new Artist(artistName, realName, description, birthday, artistImage);
        if (this.checkIfArtistNameExists(artistName)) {
            throw new ArtistNameAlreadyExistsException(artistName);
        }
        return artistRepository.save(artist);
    }

    @Override
    public void updateArtist(Integer id, String artistName, String realName, String description, LocalDate birthday, String artistImage) {
        Artist artist = this.findArtistById(id);
        artist.setArtistName(artistName);
        artist.setRealName(realName);
        artist.setDescription(description);
        artist.setArtistImage(artistImage);
        this.artistRepository.save(artist);
    }

    @Override
    public void addArtistToSong(Integer songId, Integer artistId) {
        artistRepository.addArtistToSong(songId, artistId);
    }

    @Override
    public void addArtistToAlbum(Integer artistId, Integer albumId) {
        artistRepository.addAlbumToArtist(artistId, albumId);
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

    @Override
    public Specification<Artist> nameContains(String text) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.upper(root.get(Artist_.ARTISTNAME)),
                "%" + text.toUpperCase() + "%");
    }

    @Override
    public Specification<Artist> artistAgeIs(Integer artistAge) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(Artist_.AGE), artistAge);
    }
}