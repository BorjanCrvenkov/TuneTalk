package finki.bazi.tunetalk.service.impl;


import finki.bazi.tunetalk.model.Album;
import finki.bazi.tunetalk.model.Song;
import finki.bazi.tunetalk.repository.AlbumRepository;
import finki.bazi.tunetalk.service.AlbumService;
import finki.bazi.tunetalk.service.ArtistService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistService artistService;

    public AlbumServiceImpl(AlbumRepository albumRepository, ArtistService artistService) {
        this.albumRepository = albumRepository;
        this.artistService = artistService;
    }

    @Override
    public Album findById(int albumId) {
        return albumRepository.findByAlbumId(albumId);
    }

    @Override
    public List<Album> findAllAlbums() {
        return albumRepository.findAll();
    }

    @Override
    public List<Album> findAllAlbumsByName(String albumName) {
        return albumRepository.findAllByAlbumNameLike(albumName);
    }

    @Override
    public List<Album> findAlbumsByDate(LocalDate dateReleased) {
        return albumRepository.findAllByDateReleased(dateReleased);
    }

    @Override
    public List<Album> findAlbumsByRating(float rating) {
        return albumRepository.findAllByRating(rating);
    }

    @Override
    public List<Album> findByVerification(boolean verified) {
        return albumRepository.findAllByVerified(verified);
    }

    @Override
    public List<Song> getAllSongsInAlbum(int albumId) {
        return null;
    }

    @Override
    public Song addSongToAlbum(Song song, Album album) {
        return null;
    }

    @Override
    public Song removeSongFromAlbum(Song song, Album album) {
        return null;
    }

    @Override
    public void changeAlbumRating(int albumId, float rating) {
        Album album = albumRepository.findByAlbumId(albumId);
        album.setRating(rating);
    }

    @Override
    public void changeAlbumName(int albumId, String albumName) {
        Album album = albumRepository.findByAlbumId(albumId);
        album.setAlbumName(albumName);
    }

    @Override
    public void changeAlbumVerified(int albumId, boolean verified) {
        Album album = albumRepository.findByAlbumId(albumId);
        album.setVerified(verified);
    }

    @Override
    public void changeAlbumDateReleased(int albumId, LocalDate dateReleased) {
        Album album = albumRepository.findByAlbumId(albumId);
        album.setDateReleased(dateReleased);
    }

    @Override
    public List<Album> findAllAlbumsByArtistId(Integer artistId) {
        List<Integer> albumsIds = albumRepository.findAllAlbumsIdByArtistId(artistId);
        List<Album> albums = new ArrayList<>();
        for(int i=0;i<albumsIds.size();i++){
            albums.add(this.findById(albumsIds.get(i)));
        }

        return albums;
    }

    @Override
    public Album findAlbumBySongId(Integer songId) {
        return findById(albumRepository.findAlbumIdBySongId(songId));
    }

    @Override
    public Album addNewAlbum(String albumName, LocalDate dateReleased, float rating, int artistId) {
        Album album = new Album(albumName,dateReleased,rating,false);
        albumRepository.save(album);
        Integer albumId = albumRepository.findAlbumIdByAlbumNameDateReleasedAndRating(albumName,dateReleased,rating);
        artistService.addArtistToAlbum(artistId,albumId);
        return album;
    }

    @Override
    public void deleteArtistFromAlbum(Integer artistId, Integer albumId) {
        albumRepository.deleteArtistFromAlbum(artistId, albumId);
    }

    @Override
    public void deleteSongFromAlbum(Integer songId, Integer albumId) {
        albumRepository.deleteSongFromAlbum(songId,albumId);
    }

    @Override
    public void addGenreToAlbum(Integer genreId, Integer albumId) {
        albumRepository.addGenreToAlbum(genreId, albumId);
    }

    @Override
    public void deleteGenreFromAlbum(Integer genreId, Integer albumId) {
        albumRepository.deleteGenreFromAlbum(genreId,albumId);
    }

    @Override
    public void updateAlbum(Integer albumId,String albumName, LocalDate dateReleased, float rating) {
        albumRepository.updateAlbum(albumId, albumName, dateReleased, rating);
    }

    @Override
    public List<Album> findAlbumsByGenreId(Integer genreId) {
        List<Integer> albumIds = albumRepository.findAlbumsByGenreId(genreId);
        List<Album> albums = new ArrayList<>();

        for(Integer id : albumIds){
            albums.add(this.findById(id));
        }

        return albums;

    }


}
