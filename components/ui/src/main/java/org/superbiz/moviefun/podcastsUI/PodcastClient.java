package org.superbiz.moviefun.podcastsUI;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

import java.util.List;

public class PodcastClient {
    private static ParameterizedTypeReference<List<PodcastUI>> podcastListType = new ParameterizedTypeReference<List<PodcastUI>>() {};
    private String podcastURL;
    private RestOperations restOperations;

    public PodcastClient(String podcastURL, RestOperations restOperations) {
        this.podcastURL = podcastURL;
        this.restOperations = restOperations;
    }

    public void create(PodcastUI podcast) {
        restOperations.postForEntity(podcastURL, podcast, PodcastUI.class);
    }

    public List<PodcastUI> getAll() {
        return restOperations.exchange(podcastURL, HttpMethod.GET, null, podcastListType).getBody();
    }
}
