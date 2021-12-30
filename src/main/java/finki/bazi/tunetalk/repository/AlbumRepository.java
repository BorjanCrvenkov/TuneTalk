package finki.bazi.tunetalk.repository;


import finki.bazi.tunetalk.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {

    Album findByAlbumId(int albumId);

    List<Album> findAllByAlbumNameLike(String albumName);

    List<Album> findAllByDateReleased(LocalDate dateReleased);

    List<Album> findAllByRating(float rating);

    List<Album> findAllByVerified(boolean verified);

    @Query(value = "select album_id from album_released where album_released.artist_id = :id",nativeQuery = true)
    List<Integer> findAllAlbumsIdByArtistId(@Param("id") Integer artistId);

    @Query(value = "select album_id from is_in where is_in.song_id = :id",nativeQuery = true)
    Integer findAlbumIdBySongId(@Param("id") Integer song);

    @Query(value = "select album_id from album where album_name = :albumName and date_released = :localDate and rating = :rating",nativeQuery = true)
    Integer findAlbumIdByAlbumNameDateReleasedAndRating(@Param("albumName")String albumName,@Param("localDate")LocalDate localDate,@Param("rating")float rating);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from album_released where artist_id = :artistId and album_id = :albumId",nativeQuery = true)
    void deleteArtistFromAlbum(@Param("artistId") Integer artistId,@Param("albumId") Integer albumId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from is_in where song_id = :songId and album_id = :albumId",nativeQuery = true)
    void deleteSongFromAlbum(@Param("songId") Integer songId,@Param("albumId") Integer albumId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into album_genre(album_id,genre_id) values(:albumId,:genreId)",nativeQuery = true)
    void addGenreToAlbum(@Param("genreId") Integer genreId,@Param("albumId") Integer albumId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from album_genre where album_id = :albumId and genre_id = :genreId",nativeQuery = true)
    void deleteGenreFromAlbum(@Param("genreId") Integer genreId,@Param("albumId") Integer albumId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update album set album_name = :albumName, date_released = :dateReleased, rating = :rating where album_id = :albumId",nativeQuery = true)
    void updateAlbum(@Param("albumId") Integer albumId,
                     @Param("albumName") String albumName,
                     @Param("dateReleased") LocalDate dateReleased,
                     @Param("rating") float rating);

    @Query(value = "select album_id from album_genre where genre_id = :genreId",nativeQuery = true)
    List<Integer> findAlbumsByGenreId(@Param("genreId") Integer genreId);

    List<Album> findAllByAlbumNameContaining(String text);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update album set verified = true where album_id = :albumId",nativeQuery = true)
    void verifyAlbum(@Param("albumId")Integer albumId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update album set verified = false where album_id = :albumId",nativeQuery = true)
    void unverifyAlbum(@Param("albumId")Integer albumId);

}
