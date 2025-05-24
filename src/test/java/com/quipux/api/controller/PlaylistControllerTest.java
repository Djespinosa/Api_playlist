package com.quipux.api.controller;

import com.quipux.api.model.PlayList;
import com.quipux.api.repository.PlayListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistControllerTest {

    private PlayListRepository playlistRepository;
    private PlaylistController playlistController;

    @BeforeEach
    void setUp() {
        playlistRepository = mock(PlayListRepository.class);
        playlistController = new PlaylistController(playlistRepository);
    }

    @Test
    void shouldCreatePlaylist() {
        PlayList playlist = new PlayList();
        playlist.setName("Rock");

        when(playlistRepository.existsById("Rock")).thenReturn(false);
        when(playlistRepository.save(any())).thenReturn(playlist);

        ResponseEntity<?> response = playlistController.createPlaylist(playlist);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(playlist, response.getBody());
    }

    @Test
    void shouldNotCreatePlaylist_WhenNameIsNull() {
        PlayList playlist = new PlayList();
        playlist.setName(null);

        ResponseEntity<?> response = playlistController.createPlaylist(playlist);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Playlist name cannot be null or empty", response.getBody());
    }

    @Test
    void shouldNotCreatePlaylist_WhenNameExists() {
        PlayList playlist = new PlayList();
        playlist.setName("Rock");

        when(playlistRepository.existsById("Rock")).thenReturn(true);

        ResponseEntity<?> response = playlistController.createPlaylist(playlist);

        assertEquals(409, response.getStatusCodeValue());
        assertEquals("Playlist with this name already exists", response.getBody());
    }

    @Test
    void shouldReturnAllPlaylists() {
        PlayList p1 = new PlayList();
        p1.setName("Rock");
        PlayList p2 = new PlayList();
        p2.setName("Pop");

        when(playlistRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<PlayList> result = playlistController.getAllPlaylists();

        assertEquals(2, result.size());
        assertTrue(result.contains(p1));
        assertTrue(result.contains(p2));
    }

    @Test
    void shouldReturnPlaylistByName() {
        PlayList playlist = new PlayList();
        playlist.setName("Rock");

        when(playlistRepository.findById("Rock")).thenReturn(Optional.of(playlist));

        ResponseEntity<Object> response = playlistController.getPlaylistByName("Rock");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(playlist, response.getBody());
    }

    @Test
    void shouldReturn404_WhenPlaylistNotFound() {
        when(playlistRepository.findById("Jazz")).thenReturn(Optional.empty());

        ResponseEntity<Object> response = playlistController.getPlaylistByName("Jazz");

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Playlist not found", response.getBody());
    }

    @Test
    void shouldDeletePlaylist() {
        when(playlistRepository.existsById("Rock")).thenReturn(true);

        ResponseEntity<?> response = playlistController.deletePlaylist("Rock");

        assertEquals(204, response.getStatusCodeValue());
        verify(playlistRepository, times(1)).deleteById("Rock");
    }

    @Test
    void shouldReturn404_WhenDeletingNonexistentPlaylist() {
        when(playlistRepository.existsById("Jazz")).thenReturn(false);

        ResponseEntity<?> response = playlistController.deletePlaylist("Jazz");

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Playlist not found", response.getBody());
    }
}
