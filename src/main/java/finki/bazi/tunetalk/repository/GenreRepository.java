package finki.bazi.tunetalk.repository;


import finki.bazi.tunetalk.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    List<Genre> findAll();

    Genre findByGenreId(int genreId);

    List<Genre> findAllByGenreNameLike(String genreName);

    @Query(value = "select genre_id from album_genre where album_id = :id",nativeQuery = true)
    List<Integer> findGenresByAlbumId(@Param("id") Integer albumId);

    @Query(value = "select genre_id from song_genre where song_id = :id",nativeQuery = true)
    List<Integer> findGenresBySongId(@Param("id") Integer songId);
}
