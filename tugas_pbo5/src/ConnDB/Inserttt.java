package ConnDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Inserttt {
    public static void main(String[] args) {
        String query = "INSERT INTO tabel_mahasiswa (nim, nama, alamat) "
                + "VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbPBO", "postgres", "1031");

            int nim = 123456789; 
            String nama = "Irkhamnawan Dikmanta Putra"; 
            String alamat = "Menganti - Gresik"; 

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, nim); // Menggunakan setString untuk NIM
            pstmt.setString(2, nama);
            pstmt.setString(3, alamat);

            int k = pstmt.executeUpdate();
            if (k > 0)
                System.out.println("Data berhasil tersimpan :D");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
