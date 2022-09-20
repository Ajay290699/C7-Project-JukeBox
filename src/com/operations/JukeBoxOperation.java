package com.operations;

import com.data.PlayList;
import com.data.Song;
import com.utility.DBconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JukeBoxOperation {

    public List<Song> displayAllSongs() {
        List<Song> songList = new ArrayList<>();
        try {
            Connection connection = DBconnection.getConnection();
            String sql = "select * from song";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int songID = resultSet.getInt(1);
                String songName = resultSet.getString(2);
                String songDuration = resultSet.getString(3);
                String artistName = resultSet.getString(4);
                String genreType = resultSet.getString(5);
                String songPath = resultSet.getString(6);
                Song songs = new Song(songID, songName, songDuration, artistName, genreType, songPath);
                songList.add(songs);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songList;
    }

    public List<Song> displayArtistName(String artistName) {
        List<Song> listOfArtist = new ArrayList<>();
        try {
            Connection connection = DBconnection.getConnection();
            String sql = "select * from song where artistName like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, artistName + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.format("%-10s %20s", "ArtistName", "songName\n");
            System.out.println("-----------------------------------------------------------");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String songName = resultSet.getString(2);
                String duration = resultSet.getString(3);
                String artistName1 = resultSet.getString(4);
                String genre = resultSet.getString(5);
                String songPath = resultSet.getString(6);
                listOfArtist.add(new Song(id, songName, duration, artistName1, genre, songPath));
                /*System.out.println();
                System.out.println(ArtistName + "        " + songName);*/
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfArtist;
    }

    public List<Song> displayGenreType(String genreName) {
        List<Song> listOfGenre = new ArrayList<>();
        try {
            Connection connection = DBconnection.getConnection();
            String sql = "select genreName,songName from song where genreName=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, genreName);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.format("%-10s %20s", "GenreName", "songName\n");
            System.out.println("-----------------------------------------------------------");
            while (resultSet.next()) {
                String genreName1 = resultSet.getString(1);
                String songName = resultSet.getString(2);
                System.out.println();
                System.out.println(genreName + "        " + songName);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfGenre;
    }

    public List<Song> searchSongByName(String songName) {
        List<Song> songList = new ArrayList<>();
        try {
            Connection connection = DBconnection.getConnection();
            String sql = "select songId,songName,songDuration from song where songName like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, songName);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.format("%-10s %25s %20s", "SongID", "SongName", "SongDuration\n");
            System.out.println("-----------------------------------------------------------");
            while (resultSet.next()) {
                int songID = resultSet.getInt(1);
                String songName1 = resultSet.getString(2);
                String songDuration = resultSet.getString(3);
                System.out.println();
                System.out.format("%-10s %25s %20s", songID, songName1, songDuration);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songList;
    }

    public void getDetailsOfExistingPlaylist() {
        //List<String> stringList = new ArrayList<>();

        Connection connection = null;
        try {
            connection = DBconnection.getConnection();

            String sql = "select * from playlist";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            System.out.format("%-30s %-30s\n", "PlayList Id", "Playlist Name");
            while (resultSet.next()) {
                String playlistId = resultSet.getString(1);
                String playlistName = resultSet.getString(2);

                System.out.format("%-30s %-30s\n", playlistId, playlistName);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PlayList> displayAllPlaylist() {

        List<PlayList> playlists = new ArrayList<>();
        try {
            Connection connection = DBconnection.getConnection();
            Statement statement = connection.createStatement();
            String sql = "Select * from playlist";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int playlist_id = resultSet.getInt(1);
                String playlist_name = resultSet.getString(2);
                PlayList playlists1 = new PlayList(playlist_id, playlist_name);
                playlists.add(playlists1);
            }

            return playlists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> getSongIdInPlaylistContent(int playlistId) {
        List<Integer> songId = new ArrayList<>();
        try {
            Connection connection = DBconnection.getConnection();
            String sql = "Select songId from playlistcontent where playListID =" + playlistId;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                songId.add(resultSet.getInt(1));
            }

            return songId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Song> searchSongInPlaylist(int playlistId) {

        List<Song> list = new ArrayList<>();
        try {
            Connection connection = DBconnection.getConnection();
            List<Integer> songId = getSongIdInPlaylistContent(playlistId);
            for (Integer integer : songId) {
                String sql = "Select * from song where songId =" + integer;
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery(sql);

                while (resultSet.next()) {
                    int song_id = resultSet.getInt(1);
                    String song_name = resultSet.getString(2);
                    String song_duration = resultSet.getString(3);
                    String artist_name = resultSet.getString(4);
                    String genre_name = resultSet.getString(5);
                    String songPath = resultSet.getString(6);

                    Song song = new Song(song_id, song_name, song_duration, artist_name, genre_name, songPath);
                    list.add(song);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

}
