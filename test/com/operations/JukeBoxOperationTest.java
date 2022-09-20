package com.operations;

import com.data.PlayList;
import com.data.Song;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JukeBoxOperationTest {

    JukeBoxOperation jukeBoxOperation;
    PlayList playList;

    @BeforeEach
    void setUp() {
        jukeBoxOperation = new JukeBoxOperation();
        playList = new PlayList();
    }

    @AfterEach
    void tearDown() {
        jukeBoxOperation = null;
        playList = null;
    }

    @Test
    void displayAllSongs() {
        List<Song> songsList = jukeBoxOperation.displayAllSongs();
        assertEquals(15, songsList.size());
    }

    @Test
    void displayArtistName() {
        String artistName = "s";
        List<Song> songsList = jukeBoxOperation.displayArtistName(artistName);
        assertEquals(1, songsList.size());
    }

    @Test
    void displayGenreType() {
        String genreType = "sound";
        List<Song> songsList = jukeBoxOperation.displayGenreType(genreType);
        assertEquals(0, songsList.size());
    }

    @Test
    void searchSongByName() {
        String songName = "Safari.wav";
        List<Song> songsList = jukeBoxOperation.searchSongByName(songName);
        assertEquals(0, songsList.size());
    }

    @Test
    void displayAllPlaylist() {
        List<PlayList> existingPlayList = jukeBoxOperation.displayAllPlaylist();
        assertEquals(10, existingPlayList.size());
    }

    @Test
    void searchSongInPlaylist() {
        int id = 12;
        List<Song> list1 = jukeBoxOperation.searchSongInPlaylist(id);
        assertEquals(0, list1.size());
    }
}