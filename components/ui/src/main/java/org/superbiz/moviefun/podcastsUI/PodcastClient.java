package org.superbiz.moviefun.podcastsUI;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
import java.util.List;

public class PodcastClient {
    private static ParameterizedTypeReference<List<PodcastUI>> podcastListType = new ParameterizedTypeReference<List<PodcastUI>>() {
    };
    private String podcastURL;
    private RestOperations restOperations;
    private static final int CACHE_SIZE = 5;
    private final List<PodcastUI> lastRead = new ArrayList<>(CACHE_SIZE);
    private static final Logger log = LoggerFactory.getLogger(PodcastClient.class);

    public PodcastClient(String podcastURL, RestOperations restOperations) {
        this.podcastURL = podcastURL;
        this.restOperations = restOperations;
    }

    public void create(PodcastUI podcast) {
        restOperations.postForEntity(podcastURL, podcast, PodcastUI.class);
    }

    @HystrixCommand(fallbackMethod="getAllFallback")
    public List<PodcastUI> getAll() {
        List<PodcastUI> read = restOperations.exchange(podcastURL, HttpMethod.GET, null, podcastListType).getBody();
        log.debug("Read {} podcasts from {}", read.size(), podcastURL);

        lastRead.clear();
        int copyCount = (read.size() < CACHE_SIZE) ? read.size() : CACHE_SIZE;
        for (int i =0; i < copyCount; i++)
            lastRead.add(read.get(i));
        log.debug("Copied {} podcasts into the cache", copyCount);

        return read;
    }

    public List<PodcastUI> getAllFallback() {
        log.debug("Returning {} podcasts from the fallback method", lastRead.size());

        return lastRead;
    }
}