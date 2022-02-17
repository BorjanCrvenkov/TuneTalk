package finki.bazi.tunetalk.repository;


import finki.bazi.tunetalk.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {

    Artist findByArtistId(Integer id);

    List<Artist> findAllByAge(int age);

    List<Artist> findAllByRealName(String realName);

    List<Artist> findAllByVerified(boolean verified);

    @Query(value = "select artist_id from album_released where album_released.album_id = :id",nativeQuery = true)
    List<Integer> findArtistsIdByAlbumId(@Param("id") Integer albumId);

    @Query(value = "select artist_id from song_released where song_released.song_id = :id",nativeQuery = true)
    List<Integer> findArtistIdBySongId(@Param("id") Integer songId);

    Artist getArtistByArtistName(String name);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update artist set artist_name = :artistName,age = :age, real_name = :realName, description = :description where artist_id = :id",nativeQuery = true)
    void updateArtist(@Param("id")Integer id, @Param("artistName") String artistName,@Param("realName") String realName, @Param("age")Integer age,@Param("description") String description);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into song_released (artist_id,song_id) values(:artistId,:songId)",nativeQuery = true)
    void addArtistToSong(@Param("songId")Integer songId,@Param("artistId")Integer artistId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into album_released (artist_id,album_id) values(:artistId,:albumId)",nativeQuery = true)
    void addAlbumToArtist(@Param("artistId")Integer artistId,@Param("albumId")Integer albumId);

    @Query(value = "select artist_id from album_released where song_released.album_id = :id",nativeQuery = true)
    List<Integer> findArtistIdByAlbumId(@Param("id") Integer albumId);

    List<Artist> findAllByArtistNameContainingOrRealNameContaining(String text,String text1);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update artist set verified = true where artist_id = :artistId",nativeQuery = true)
    void verifyArtist(@Param("artistId")Integer artistId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update artist set verified = false where artist_id = :artistId",nativeQuery = true)
    void unverifyArtist(@Param("artistId")Integer artistId);

}
