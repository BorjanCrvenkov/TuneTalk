package finki.bazi.tunetalk.service.impl;

import finki.bazi.tunetalk.model.Album;
import finki.bazi.tunetalk.model.Artist;
import finki.bazi.tunetalk.model.Genre;
import finki.bazi.tunetalk.model.metamodel.Album_;
import finki.bazi.tunetalk.repository.AlbumRepository;
import finki.bazi.tunetalk.service.AlbumService;
import finki.bazi.tunetalk.service.ArtistService;

import finki.bazi.tunetalk.service.GenreService;
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
    private final GenreService genreService;

    public AlbumServiceImpl(AlbumRepository albumRepository, ArtistService artistService, GenreService genreService) {
        this.albumRepository = albumRepository;
        this.artistService = artistService;
        this.genreService = genreService;
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
        Artist artist = this.artistService.findArtistById(artistId);

        return artist.getAlbumsReleased();
    }

    @Override
    public Album findAlbumBySongId(Integer songId) {
        return findById(albumRepository.findAlbumIdBySongId(songId));
    }

    @Override
    public Album createNewAlbum(String albumName, LocalDate dateReleased, float rating, String albumImage, Integer artistId) {
        Album album = new Album(albumName,dateReleased,rating,albumImage);
        Album album1 = albumRepository.save(album);

        this.artistService.addArtistToAlbum(artistId, album1.getAlbumId());
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
    public Album updateAlbum(Integer albumId, String albumName, LocalDate dateReleased, float rating,
                            String albumImage) {
        Album album = this.findById(albumId);
        album.setAlbumName(albumName);
        album.setDateReleased(dateReleased);
        album.setRating(rating);
        album.setAlbumImage(albumImage);
        return this.albumRepository.save(album);
    }

    @Override
    public List<Album> findAlbumsByGenreId(Integer genreId) {
        Genre genre = this.genreService.findGenreById(genreId);
        return albumRepository.findAllByAlbumGenresContaining(genre);

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

    @Override
    public List<Album> findByPeriod(Integer period) {
        LocalDate from = LocalDate.parse(""+period+"-01-01");
        LocalDate to = LocalDate.parse(""+(period + 9)+"-12-31");
        return this.albumRepository.findByDateReleasedBetween(from,to);
    }

    @Override
    public List<Album> findByGenreAndPeriod(Integer genreId, Integer period) {
        LocalDate from = LocalDate.parse(""+period+"-01-01");
        LocalDate to = LocalDate.parse(""+(period + 9)+"-12-31");

        Genre genre = this.genreService.findGenreById(genreId);

        return this.albumRepository.findAllByDateReleasedBetweenAndAlbumGenresContaining(from,to,genre);

    }

    @Override
    public List<Album> findByNameLike(String nameLike) {
        String nameLike1 = "%"+nameLike+"%";
        return this.albumRepository.findAllByAlbumNameLike(nameLike1);
    }

    @Override
    public List<Album> findByNameLikeAndPeriod(String nameLike, Integer period) {
        LocalDate from = LocalDate.parse(""+period+"-01-01");
        LocalDate to = LocalDate.parse(""+(period + 9)+"-12-31");

        String nameLike1 = "%"+nameLike+"%";

        return this.albumRepository.findAllByAlbumNameLikeAndDateReleasedBetween(nameLike1,from,to);
    }

    @Override
    public List<Album> findByNameLikeAndGenre(String nameLike, Integer genreId) {
        String nameLike1 = "%"+nameLike+"%";

        Genre genre = this.genreService.findGenreById(genreId);

        return this.albumRepository.findAllByAlbumNameLikeAndAlbumGenresContaining(nameLike1,genre);
    }

    @Override
    public List<Album> findByNameLikeAndGenreAndPeriod(String nameLike, Integer genreId, Integer period) {
        String nameLike1 = "%"+nameLike+"%";

        LocalDate from = LocalDate.parse(""+period+"-01-01");
        LocalDate to = LocalDate.parse(""+(period + 9)+"-12-31");

        Genre genre = this.genreService.findGenreById(genreId);

        return this.albumRepository.findAllByAlbumNameLikeAndAlbumGenresContainingAndDateReleasedBetween(nameLike1,genre, from, to);
    }

}
