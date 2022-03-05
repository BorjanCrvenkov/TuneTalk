package finki.bazi.tunetalk.service;

import finki.bazi.tunetalk.model.Album;
import finki.bazi.tunetalk.model.Genre;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

public interface GenreService {

    List<Genre> listAllGenres();

    List<Genre> findAllFiltered(String genreNameContains);

    Genre findGenreById(int genreId);

    List<Album> getAllAlbumsByGenreId(int genreId);

    List<Genre> findGenresByAlbumId(Integer albumId);

    List<Genre> findGenresBySongId(Integer songId);

    void updateGenre(Integer genreId, String genreName);

    Genre createNewGenre(String genreName);

    public Specification<Genre> nameContains(String text);
}
