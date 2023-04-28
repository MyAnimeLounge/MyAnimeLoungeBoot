package com.barta.myanimelounge.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EpisodeProgress {

    @Id
    @Column(nullable = false)
    private Integer episodeNum;

    @Column(nullable = false)
    private Long progressMicroseconds;

    public EpisodeProgress(Integer episodeNum, Long progressMicroseconds) {
        this.episodeNum = episodeNum;
        this.progressMicroseconds = progressMicroseconds;
    }

    public EpisodeProgress() {

    }

    public Integer getEpisodeNum() {
        return episodeNum;
    }

    public void setEpisodeNum(Integer episodeNum) {
        this.episodeNum = episodeNum;
    }

    public Long getProgressMicroseconds() {
        return progressMicroseconds;
    }

    public void setProgressMicroseconds(Long progressMicroseconds) {
        this.progressMicroseconds = progressMicroseconds;
    }
}
