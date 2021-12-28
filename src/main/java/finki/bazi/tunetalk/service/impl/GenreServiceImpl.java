package finki.bazi.tunetalk.service.impl;


import finki.bazi.tunetalk.model.Album;
import finki.bazi.tunetalk.model.Genre;
import finki.bazi.tunetalk.model.Song;
import finki.bazi.tunetalk.model.User;
import finki.bazi.tunetalk.repository.AlbumRepository;
import finki.bazi.tunetalk.repository.GenreRepository;
import finki.bazi.tunetalk.repository.SongRepository;
import finki.bazi.tunetalk.repository.UserRepository;
import finki.bazi.tunetalk.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;
    private final UserRepository userRepository;

    public GenreServiceImpl(GenreRepository genreRepository, AlbumRepository albumRepository, SongRepository songRepository, UserRepository userRepository) {
        this.genreRepository = genreRepository;
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Genre> listAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre findGenreById(int genreId) {
        return genreRepository.findByGenreId(genreId);
    }

    @Override
    public List<Genre> findGenresByName(String genreName) {
        return genreRepository.findAllByGenreNameLike(genreName);
    }

    @Override
    public List<Album> getAllAlbumsByGenreId(int genreId) {
        Genre genre = genreRepository.findByGenreId(genreId);
        return genre.getAlbums();
    }

    @Override
    public List<Song> getAllSongsByGenreId(int genreId) {
        Genre genre = genreRepository.findByGenreId(genreId);
        return genre.getSongs();
    }

    @Override
    public List<User> getAllUsersThatLikeGenre(int genreId) {
        Genre genre = genreRepository.findByGenreId(genreId);
        return genre.getUserLikes();
    }

    @Override
    public void changeGenreName(int genreId, String newName) {
        Genre genre = genreRepository.findByGenreId(genreId);
        genre.setGenreName(newName);
    }

    @Override
    public Album addAlbumToGenre(int albumId, int genreId) {
        Genre genre = genreRepository.findByGenreId(genreId);
        Album album = albumRepository.findByAlbumId(albumId);
        genre.getAlbums().add(album);
        return album;
    }

    @Override
    public Song addSongToGenre(int songId, int genreId) {
        Genre genre = genreRepository.findByGenreId(genreId);
        Song song = songRepository.findBySongId(songId);
        genre.getSongs().add(song);
        return song;
    }

    @Override
    public User addUserToGenre(int userId, int genreId) {
        Genre genre = genreRepository.findByGenreId(genreId);
        User user = userRepository.getById(userId);
        genre.getUserLikes().add(user);
        return user;
    }

    @Override
    public void removeAlbumFromGenre(int albumId, int genreId) {
        Genre genre = genreRepository.findByGenreId(genreId);
        Album album = albumRepository.findByAlbumId(albumId);
        genre.getAlbums().remove(album);
    }

    @Override
    public void removeSongFromGenre(int songId, int genreId) {
        Genre genre = genreRepository.findByGenreId(genreId);
        Song song = songRepository.findBySongId(songId);
        genre.getSongs().remove(song);
    }

    @Override
    public void removeUserFromGenre(int userId, int genreId) {
        Genre genre = genreRepository.findByGenreId(genreId);
        User user = userRepository.getById(userId);
        genre.getUserLikes().remove(user);
    }

    @Override
    public List<Genre> findGenresByAlbumId(Integer albumId) {
        List<Integer> genreIds = genreRepository.findGenresByAlbumId(albumId);
        List<Genre> genres = new ArrayList<>();

        for(Integer id :genreIds){
            genres.add(this.findGenreById(id));
        }
        return genres;
    }

    @Override
    public List<Genre> findGenresBySongId(Integer songId) {
        List<Integer> genreIds = genreRepository.findGenresBySongId(songId);
        List<Genre> genres = new ArrayList<>();

        for(Integer id :genreIds){
            genres.add(this.findGenreById(id));
        }
        return genres;
    }

    @Override
    public void updateGenre(Integer genreId, String genreName) {
        genreRepository.updateGenre(genreId,genreName);
    }


}
