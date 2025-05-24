package com.quipux.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayList {

    @Id
    @JsonProperty("nombre")
    @Column(name = "name", nullable = false)
    private String name;

    @JsonProperty("descripcion")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "playlist_name")
    @JsonProperty("canciones")
    private List<Song> songs;
}
