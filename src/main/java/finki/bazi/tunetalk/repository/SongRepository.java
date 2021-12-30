package finki.bazi.tunetalk.repository;


import finki.bazi.tunetalk.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {

    Song findBySongId(int songId);

    List<Song> findAllByTitle(String title);

    List<Song> findAllByDateReleased(LocalDate dateReleased);

    List<Song> findAllByRating(float rating);

    List<Song> findAllByVerified(boolean verified);

    @Query(value = "select song_id from song_released where song_released.artist_id = :id",nativeQuery = true)
    List<Integer> findAllSongsIdByArtistId(@Param("id") Integer artistId);

    @Query(value = "select song_id from is_in where is_in.album_id = :id",nativeQuery = true)
    List<Integer> findAllSongsIdByAlbumId(@Param("id") Integer albumId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from song_released where artist_id = :artistId and song_id = :songId",nativeQuery = true)
    void deleteArtistFromSong(Integer artistId, Integer songId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into song_genre(song_id,genre_id) values(:songId,:genreId)",nativeQuery = true)
    void addGenreToSong(@Param("genreId") Integer genreId,@Param("songId") Integer songId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from song_genre where song_id = :songId and genre_id = :genreId",nativeQuery = true)
    void deleteGenreFromSong(@Param("genreId") Integer genreId,@Param("songId") Integer songId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update song set title = :songTitle, date_released = :dateReleased, rating = :rating, lyrics = :lyrics where song_id = :songId",nativeQuery = true)
    void updateSong(@Param("songId") Integer songId,
                    @Param("songTitle") String songTitle,
                    @Param("dateReleased") LocalDate dateReleased,
                    @Param("rating") float rating,
                    @Param("lyrics") String lyrics);

    @Query(value = "select song_id from song_genre where genre_id = :genreId",nativeQuery = true)
    List<Integer> findSongsByGenreId(@Param("genreId") Integer genreId);

    List<Song> findAllByTitleContaining(String text);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update song set verified = true where song_id = :songId",nativeQuery = true)
    void verifySong(@Param("songId")Integer songId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update song set verified = false where song_id = :songId",nativeQuery = true)
    void unverifySong(@Param("songId")Integer songId);
}
