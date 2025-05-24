package com.quipux.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.quipux.api.repository.PlayListRepository;
import com.quipux.api.model.PlayList;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lists")
public class PlaylistController {

    private final PlayListRepository playlistRepository;

    public PlaylistController(PlayListRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    // POST /lists → Crear nueva lista de reproducción
    @PostMapping
    public ResponseEntity<?> createPlaylist(@RequestBody PlayList playlist) {
        if (playlist.getName() == null || playlist.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Playlist name cannot be null or empty");
        }

        if (playlistRepository.existsById(playlist.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Playlist with this name already exists");
        }

        PlayList saved = playlistRepository.save(playlist);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // GET /lists → Obtener todas las listas
    @GetMapping
    public List<PlayList> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    // GET /lists/{listName} → Obtener una lista por nombre
    @GetMapping("/{listName}")
    public ResponseEntity<Object> getPlaylistByName(@PathVariable String listName) {
        Optional<PlayList> playlist = playlistRepository.findById(listName);
        return playlist
                .<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Playlist not found"));
    }

    // DELETE /lists/{listName} → Eliminar una lista
    @DeleteMapping("/{listName}")
    public ResponseEntity<?> deletePlaylist(@PathVariable String listName) {
        if (!playlistRepository.existsById(listName)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Playlist not found");
        }

        playlistRepository.deleteById(listName);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
