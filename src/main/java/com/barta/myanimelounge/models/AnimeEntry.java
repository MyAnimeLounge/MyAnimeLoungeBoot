package com.barta.myanimelounge.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "anime-entries")
public class AnimeEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long Id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(unique = true, nullable = false)
    @Min(0)
    @NotNull(message = "AniList Id is mandatory")
    private Long aniListId;

    @Column()
    private String review;

    @NotBlank(message = "status is required")
    private String status;

    @Column()
    private Float rating;

    @Column()
    private Date startDate;

    @Column()
    private Date endDate;

    public AnimeEntry(Long aniListId, String review, String status, Float rating, Date startDate, Date endDate) {
        this.aniListId = aniListId;
        this.review = review;
        this.status = status;
        this.rating = rating;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public AnimeEntry() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getAniListId() {
        return aniListId;
    }

    public void setAniListId(Long aniListId) {
        this.aniListId = aniListId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
