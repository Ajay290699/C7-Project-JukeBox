package com.main;

import com.data.PlayList;
import com.data.Song;
import com.operations.JukeBoxOperation;
import com.operations.SongOperation;
import com.operations.UserOperation;

import java.util.List;
import java.util.Scanner;

public class Implementation {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PlayList playList = new PlayList();
        UserOperation userOperation = new UserOperation();
        JukeBoxOperation jukeBoxOperation = new JukeBoxOperation();
        SongOperation songOperation = new SongOperation();
        System.out.println("*********************************** Welcome to Ajay's Music App **************************************\n");
        System.out.println("\t\t\t\t\tSong Table\n=======================================================================================================================================");
        List<Song> songList = jukeBoxOperation.displayAllSongs();
        printList(songList);
        System.out.println("\n=======================================================================================================================================");

        int choice = 0;

        while (choice != 5) {
            System.out.println("1 : to search in list\n2 : to create Play List\n3 : to view Play List\n4 : to Play a Song\n5 : to Exit");
            int task = sc.nextInt();
            System.out.println("=======================================================================================================================================");
            switch (task) {
                case 1:
                    boolean flag = true;
                    while (flag) {
                        System.out.println();
                        System.out.println("\n1 : search song by Artist Name\n2 : search by Genre Type\n3 : search by Song-Name\n4 : back");
                        int choice1 = sc.nextInt();
                        switch (choice1) {
                            case 1:
                                System.out.println("Enter Artist Name");
                                sc.nextLine();
                                String artistName = sc.nextLine();
                                List<Song> songList1 = jukeBoxOperation.displayArtistName(artistName);
                                printArtist(songList1);
                                break;
                            case 2:
                                System.out.println("Enter genre Type");
                                sc.nextLine();
                                String genre = sc.nextLine();
                                List<Song> songList2 = jukeBoxOperation.displayGenreType(genre);
                                printGenre(songList2);
                                break;
                            case 3:
                                System.out.println("Enter Song Name");
                                sc.nextLine();
                                String songName = sc.nextLine();
                                List<Song> songList3 = jukeBoxOperation.searchSongByName(songName);
                                printSongName(songList3);
                                break;
                            case 4:
                                flag = false;
                                String[] arg = new String[0];
                                Implementation.main(arg);
                        }
                    }
                case 2:
                    System.out.println("Enter new Name for creating PlayList : ");
                    userOperation.createSongPlayList();
                    boolean flag1 = true;
                    while (flag1) {
                        System.out.println("If you want to add song in playlist\n1 : to add song\n2 : Exit");
                        int choice2 = sc.nextInt();
                        switch (choice2) {
                            case 1:
                                jukeBoxOperation.getDetailsOfExistingPlaylist();
                                userOperation.addSongtoPlayList();
                            case 2:
                                flag1 = false;
                                break;
                        }
                    }
                case 3:
                    List<PlayList> existingPlayList = jukeBoxOperation.displayAllPlaylist();
                    System.out.format("%-10s %-20s\n", "PlayList-ID", "PlayListName");
                    for (PlayList playList1 : existingPlayList) {
                        System.out.format("%-10d %-20s\n", playList1.getPlayListID(), playList1.getPlayListName());
                    }
                    System.out.println("Enter playList-ID : ");
                    int id = sc.nextInt();
                    List<Song> songList1 = jukeBoxOperation.searchSongInPlaylist(id);
                    System.out.println("==================================================================================================");
                    System.out.format("%-10s %-30s %-30s %-30s %-30s \n", "SongID", "SongName", "Duration", "GenreType", "Artist");
                    System.out.println("==================================================================================================");
                    for (Song song : songList1) {
                        System.out.format("%-10s %-30s %-30s %-30s %-30s \n", song.getSongID(), song.getSongName(), song.getSongDuration(), song.getGenreType(), song.getArtistName());
                    }
                    songOperation.playSong();
                    break;
                case 4:
                    songOperation.playSong();
                    break;
                case 5:
                    System.out.println("Successfully Exited");
                    System.exit(0);
            }
        }

    }

    private static void printList(List<Song> songList) {
        System.out.format("%-10s %-25s %-20s %-20s %-20s\n", "id", "SongName", "Duration", "Artist-Name", "GenreType");
        System.out.println("------------------------------------------------------------------------------------------------");
        for (Song song : songList) {
            System.out.format("%-10s %-25s %-20s %-20s %-20s\n", song.getSongID(), song.getSongName(), song.getSongDuration(),
                    song.getArtistName(), song.getGenreType());
        }
    }

    private static void printArtist(List<Song> songList) {
        for (Song song : songList) {
            System.out.format("%-10s %20s\n", song.getArtistName(), song.getSongName());
        }
    }

    private static void printGenre(List<Song> songList) {
        for (Song song : songList) {
            System.out.format("%-10s %20s\n", song.getGenreType(), song.getSongName());
        }
    }

    private static void printSongName(List<Song> songList) {
        for (Song song : songList) {
            System.out.format("%-10s %20s %20s\n", song.getGenreType(), song.getSongName(), song.getSongDuration());
        }
    }

}
