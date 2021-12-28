package finki.bazi.tunetalk.repository;


import finki.bazi.tunetalk.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    List<Genre> findAll();

    Genre findByGenreId(int genreId);

    List<Genre> findAllByGenreNameLike(String genreName);

    Genre findGenreByGenreName(String name);

    @Query(value = "select genre_id from album_genre where album_id = :id",nativeQuery = true)
    List<Integer> findGenresByAlbumId(@Param("id") Integer albumId);

    @Query(value = "select genre_id from song_genre where song_id = :id",nativeQuery = true)
    List<Integer> findGenresBySongId(@Param("id") Integer songId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update genre set genre_name = :genreName where genre_id = :genreId",nativeQuery = true)
    void updateGenre(@Param("genreId") Integer genreId,@Param("genreName") String genreName);
}
