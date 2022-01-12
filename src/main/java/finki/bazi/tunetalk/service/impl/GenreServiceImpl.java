package finki.bazi.tunetalk.service.impl;


import finki.bazi.tunetalk.model.Album;
import finki.bazi.tunetalk.model.Genre;
import finki.bazi.tunetalk.model.exceptions.GenreNameAlreadyExistsException;
import finki.bazi.tunetalk.repository.AlbumRepository;
import finki.bazi.tunetalk.repository.GenreRepository;
import finki.bazi.tunetalk.repository.SongRepository;
import finki.bazi.tunetalk.repository.UserRepository;
import finki.bazi.tunetalk.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;
    private final UserRepository userRepository;

    public GenreServiceImpl(GenreRepository genreRepository, AlbumRepository albumRepository, SongRepository songRepository, UserRepository userRepository) {
        this.genreRepository = genreRepository;
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Genre> listAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre findGenreById(int genreId) {
        return genreRepository.findByGenreId(genreId);
    }


    @Override
    public List<Album> getAllAlbumsByGenreId(int genreId) {
        return null;
    }

    @Override
    public List<Genre> findGenresByAlbumId(Integer albumId) {
        List<Integer> genreIds = genreRepository.findGenresByAlbumId(albumId);
        return this.genreRepository.findAllById(genreIds);
    }

    @Override
    public List<Genre> findGenresBySongId(Integer songId) {
        List<Integer> genreIds = genreRepository.findGenresBySongId(songId);
        return this.genreRepository.findAllById(genreIds);
    }

    @Override
    public void updateGenre(Integer genreId, String genreName) {
        genreRepository.updateGenre(genreId,genreName);
    }

    private boolean genreNameExists(String genreName){
        return genreRepository.findGenreByGenreName(genreName) != null;
    }

    @Override
    public Genre createNewGenre(String genreName) {
        Genre genre = new Genre(genreName);
        if(genreNameExists(genreName)){
            throw new GenreNameAlreadyExistsException(genreName);
        }else {
            return genreRepository.save(genre);
        }
    }


}
