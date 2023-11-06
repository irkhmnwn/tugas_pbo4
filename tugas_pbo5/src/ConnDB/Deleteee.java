package ConnDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Deleteee {
    public static void main(String[] args) {
        String query = "DELETE FROM tabel_mahasiswa "
                + "WHERE nim = ? ";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbPBO", "postgres", "1031");

            int nim = 123456789;

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, nim);

            int k = pstmt.executeUpdate();
            if (k > 0)
                System.out.println("Data berhasil dihapus :D");
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
