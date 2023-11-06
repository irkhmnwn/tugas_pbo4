package ConnDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Updateee {
    public static void main(String[] args) {
        String query = "UPDATE tabel_mahasiswa "
                + "SET nama = ?, alamat = ? "
                + "WHERE nim = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbPBO", "postgres", "1031");

            int nim = 123456789; 
            String nama = "Yuna Ikbar Zaidan"; 
            String alamat = "Trenggalek - Jawa Timur"; 

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nama);
            pstmt.setString(2, alamat);
            pstmt.setInt(3, nim);

            int k = pstmt.executeUpdate();
            if (k > 0)
                System.out.println("Data berhasil diubah :D");
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
