package com.operations;

import com.utility.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class UserOperation {


    public static void createSongPlayList() {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int id = random.nextInt(80) + 10;
        System.out.println("Enter PlayListName");
        String playListName = sc.nextLine();
        try {
            Connection connection = DBconnection.getConnection();
            String sql = "insert into playlist values (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, playListName);
            int i = preparedStatement.executeUpdate();
            if (i > 0)
                System.out.println("Succesfully Created PlayList ");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to create ");
            e.printStackTrace();
        }
    }

    public static void addSongtoPlayList() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter PlayList-ID");
        int playListID = sc.nextInt();
        System.out.println("Enter Song-Id which you want to add");
        int songID = sc.nextInt();
        try {
            Connection connection = DBconnection.getConnection();
            String sql1 = "select * from song where songId =" + songID;
            String sql2 = "insert into playlistcontent values (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultSet.getInt(1);
            }
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql2);
            preparedStatement1.setInt(1, playListID);
            preparedStatement1.setInt(2, songID);
            int i = preparedStatement1.executeUpdate();
            if (i > 0)
                System.out.println("Succesfully added song to Playlist");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to add Song");
            e.printStackTrace();
        }
    }

}