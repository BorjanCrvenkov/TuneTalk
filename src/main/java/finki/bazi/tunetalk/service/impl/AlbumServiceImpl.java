package finki.bazi.tunetalk.service.impl;

import finki.bazi.tunetalk.model.Album;
import finki.bazi.tunetalk.model.metamodel.Album_;
import finki.bazi.tunetalk.repository.AlbumRepository;
import finki.bazi.tunetalk.service.AlbumService;
import finki.bazi.tunetalk.service.ArtistService;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
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
    public List<Album> findAllFiltered(String albumNameContains, Integer yearReleased, String genre) {
        Specification<Album> albumFilterSpecification = nameContains(
                albumNameContains == null ? "" : albumNameContains);
        if (yearReleased != null) {
            albumFilterSpecification = albumFilterSpecification.and(yearReleased(yearReleased));
        }

        var albums = albumRepository.findAll(albumFilterSpecification);
        return albums;

    }

    @Override
    public List<Album> findAllAlbumsByArtistId(Integer artistId) {
        List<Integer> albumsIds = albumRepository.findAllAlbumsIdByArtistId(artistId);

        return albumRepository.findAllById(albumsIds);
    }

    @Override
    public Album findAlbumBySongId(Integer songId) {
        return findById(albumRepository.findAlbumIdBySongId(songId));
    }

    @Override
    public Album createNewAlbum(String albumName, LocalDate dateReleased, float rating, int artistId) {
        Album album = new Album(albumName, dateReleased, rating, false);
        albumRepository.save(album);

        Integer albumId = albumRepository.findAlbumIdByAlbumNameDateReleasedAndRating(albumName, dateReleased, rating);
        artistService.addArtistToAlbum(artistId, albumId);
        return album;
    }

    @Override
    public void deleteArtistFromAlbum(Integer artistId, Integer albumId) {
        albumRepository.deleteArtistFromAlbum(artistId, albumId);
    }

    @Override
    public void deleteSongFromAlbum(Integer songId, Integer albumId) {
        albumRepository.deleteSongFromAlbum(songId, albumId);
    }

    @Override
    public void addGenreToAlbum(Integer genreId, Integer albumId) {
        albumRepository.addGenreToAlbum(genreId, albumId);
    }

    @Override
    public void deleteGenreFromAlbum(Integer genreId, Integer albumId) {
        albumRepository.deleteGenreFromAlbum(genreId, albumId);
    }

    @Override
    public void updateAlbum(Integer albumId, String albumName, LocalDate dateReleased, float rating) {
        albumRepository.updateAlbum(albumId, albumName, dateReleased, rating);
    }

    @Override
    public List<Album> findAlbumsByGenreId(Integer genreId) {
        List<Integer> albumIds = albumRepository.findAlbumsByGenreId(genreId);
        return albumRepository.findAllById(albumIds);

    }

    @Override
    public void verifyAlbum(Integer albumId) {
        albumRepository.verifyAlbum(albumId);
    }

    @Override
    public void unverifyAlbum(Integer albumId) {
        albumRepository.unverifyAlbum(albumId);
    }

    @Override
    public Specification<Album> nameContains(String text) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.upper(root.get(Album_.ALBUMNAME)),
                "%" + text.toUpperCase() + "%");
    }

    @Override
    public Specification<Album> yearReleased(Integer yearReleased) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, yearReleased);
        LocalDate localDate = LocalDate
                .ofInstant(calendar.toInstant(), ZoneId.systemDefault());

        LocalDate firstDay = localDate.with(firstDayOfYear());
        LocalDate lastDay = localDate.with(lastDayOfYear());
        Specification<Album> specification = (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(
                (root.get(Album_.DATERELEASED)),
                firstDay);
        specification = specification.and((root, query, criteriaBuilder) -> criteriaBuilder.lessThan(
                (root.get(Album_.DATERELEASED)),
                lastDay));

        return specification;
    }

}
