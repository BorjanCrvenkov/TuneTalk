package finki.bazi.tunetalk.service.impl;

import finki.bazi.tunetalk.repository.AlbumRepository;
import finki.bazi.tunetalk.repository.ArtistRepository;
import finki.bazi.tunetalk.repository.SongRepository;
import finki.bazi.tunetalk.service.SearchService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;

    public SearchServiceImpl(AlbumRepository albumRepository, SongRepository songRepository,
            ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Object> searchByText(String text) {
        List<Object> objects = new ArrayList<>();

        objects.addAll(artistRepository.findAllByArtistNameContainingOrRealNameContaining(text, text));
        objects.addAll(albumRepository.findAllByAlbumNameContaining(text));
        objects.addAll(songRepository.findAllByTitleContaining(text));

        return objects;
    }
}
