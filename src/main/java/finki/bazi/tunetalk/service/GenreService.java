package finki.bazi.tunetalk.service;


import finki.bazi.tunetalk.model.Album;
import finki.bazi.tunetalk.model.Genre;
import finki.bazi.tunetalk.model.Song;
import finki.bazi.tunetalk.model.User;

import java.util.List;

public interface GenreService {

    List<Genre> listAllGenres();

    Genre findGenreById(int genreId);

    List<Genre> findGenresByName(String genreName);

    List<Album> getAllAlbumsByGenreId(int genreId);

    List<Song> getAllSongsByGenreId(int genreId);

    List<User> getAllUsersThatLikeGenre(int genreId);

    void changeGenreName(int genreId,String newName);

    Album addAlbumToGenre(int albumId, int genreId);

    Song addSongToGenre(int songId, int genreId);

    User addUserToGenre(int userId, int genreId);

    void removeAlbumFromGenre(int albumId, int genreId);

    void removeSongFromGenre(int songId, int genreId);

    void removeUserFromGenre(int userId, int genreId);

    List<Genre> findGenresByAlbumId(Integer albumId);

    List<Genre> findGenresBySongId(Integer songId);

    void updateGenre(Integer genreId,String genreName);

    void createNewGenre(String genreName);

}
