package com.operations;

import com.main.Implementation;
import com.utility.DBconnection;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SongOperation {
    int songIndex;

    public void playSong() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Song ID");
        int songId = scanner.nextInt();
        songIndex = songId;
        SongOperation songOperation = new SongOperation();
        songOperation.songListName(songId);
    }

    public String songListName(int id) {
        String songName = null;
        try {
            while (id != 15) {
                Connection connection = DBconnection.getConnection();
                String sql = "select songPath from song where songId = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    File file = new File(resultSet.getString(1));
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    int delay = 1000;

                    long length = (clip.getMicrosecondLength() / 1000);
                    Timer t = new Timer(delay, new ActionListener() {
                        private long time = length;

                        public void actionPerformed(ActionEvent e) {
                            if (time >= 0) {
                                long s = ((time / 1000) % 60);
                                long m = ((time / 1000) / 60 % 60);
                                long h = ((((time / 1000) / 60) / 60) % 60);
                                System.out.print("\r" + h + ":" + m + ":" + s);
                                time -= 1000;
                            }

                        }
                    });

                    Scanner sc = new Scanner(System.in);
                    String response = "";

                    while (!response.equals("Q")) {
                        System.out.println("P = play, S = pause, R = Reset, Q = Next Z=prev B=back");
                        System.out.print("Enter your choice: ");
                        response = sc.next();
                        response = response.toUpperCase();
                        if (response.equalsIgnoreCase("p")) {
                            clip.start();
                            t.start();


                        }
                        if (response.equalsIgnoreCase("s")) {
                            clip.stop();
                            t.stop();
                        }
                        if (response.equalsIgnoreCase("r")) {
                            clip.setMicrosecondPosition(0);
                        }
                        if (response.equalsIgnoreCase("q")) {

                            clip.close();
                            t.stop();
                            id = id + 1;
                            if (id == 15) {
                                id = 1;
                            }
                        }
                        if (response.equalsIgnoreCase("b")) {
                            String[] arg = new String[0];
                            Implementation.main(arg);
                        }
                        if (response.equalsIgnoreCase("z")) {

                            clip.close();
                            t.stop();
                            id = id - 1;
                            songListName(id);
                        }
                    }

                }

            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return songName;
    }
}