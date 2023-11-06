/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConnDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Selecttt {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/dbPBO";
    static final String USER = "postgres";
    static final String PASS = "1031";
    static final String QUERY = "SELECT * FROM tabel_mahasiswa";

    public static void main(String[] args) {
        // Open a connection
        try ( Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(QUERY);) {
            while (rs.next()) {
                //Display values
                System.out.println("NIM: " + String.valueOf(rs.getObject(1)));
                System.out.println("Nama: " + String.valueOf(rs.getObject(2)));
                System.out.println("Akamat: "+ String.valueOf(rs.getObject(3)));
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}