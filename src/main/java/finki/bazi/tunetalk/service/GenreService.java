package finki.bazi.tunetalk.service;


import finki.bazi.tunetalk.model.Album;
import finki.bazi.tunetalk.model.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> listAllGenres();

    Genre findGenreById(int genreId);

    List<Album> getAllAlbumsByGenreId(int genreId);


    List<Genre> findGenresByAlbumId(Integer albumId);

    List<Genre> findGenresBySongId(Integer songId);

    void updateGenre(Integer genreId,String genreName);

    void createNewGenre(String genreName);

}
