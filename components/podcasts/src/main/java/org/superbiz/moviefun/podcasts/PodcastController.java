package org.superbiz.moviefun.podcasts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/podcasts")
public class PodcastController {
    private PodcastRepository podcastRepository;

    public PodcastController(PodcastRepository podcastRepository) {
        this.podcastRepository = podcastRepository;
    }

    @PostMapping
    public ResponseEntity<Podcast> create(@RequestBody Podcast podcast) {
        podcastRepository.save(podcast);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public List<Podcast> getAll() {
        List<Podcast> podcasts = new ArrayList<>();
        podcastRepository.findAll().forEach(podcasts::add);
        return podcasts;
    }
}
