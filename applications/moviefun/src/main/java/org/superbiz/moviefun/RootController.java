package org.superbiz.moviefun;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.superbiz.moviefun.movies.MoviesBean;
import org.superbiz.moviefun.movies.MoviesInitialList;
import org.superbiz.moviefun.podcasts.PodcastRepository;
import org.superbiz.moviefun.podcasts.PodcastsInitialList;

import java.util.Map;

@Controller
public class RootController {
    private final MoviesBean moviesBean;
    private final PodcastRepository podcastRepository;
    private final MoviesInitialList moviesInitialList;
    private final PodcastsInitialList podcastsInitialList;

    public RootController(MoviesBean moviesBean, PodcastRepository podcastRepository, MoviesInitialList moviesInitialList, PodcastsInitialList podcastsInitialList) {
        this.moviesBean = moviesBean;
        this.podcastRepository = podcastRepository;
        this.moviesInitialList = moviesInitialList;
        this.podcastsInitialList = podcastsInitialList;
    }

    @GetMapping("/")
    public String rootPath() {
        return "index";
    }

    @GetMapping("/setup")
    public String setupDatabase(Map<String, Object> model) {

        moviesInitialList.asList().forEach(moviesBean::addMovie);
        model.put("movies", moviesBean.getMovies());

        podcastsInitialList.asList().forEach(podcastRepository::save);
        model.put("podcasts", podcastRepository.findAll());

        return "setup";
    }

}
