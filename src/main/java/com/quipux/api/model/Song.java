package com.quipux.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("titulo")
    private String title;

    @JsonProperty("artista")
    private String artist;

    @JsonProperty("album")
    private String album;

    @JsonProperty("anno")
    private String releaseYear;

    @JsonProperty("genero")
    private String genre;

}

