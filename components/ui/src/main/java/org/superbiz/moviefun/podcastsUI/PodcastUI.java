package org.superbiz.moviefun.podcastsui;

import java.util.Objects;

public class PodcastUI {
    private Long id;
    private String title;
    private String description;
    private String url;

    public PodcastUI() {
    }

    public PodcastUI(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PodcastUI podcastUI = (PodcastUI) o;
        return title.equals(podcastUI.title) &&
                url.equals(podcastUI.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, url);
    }
}
