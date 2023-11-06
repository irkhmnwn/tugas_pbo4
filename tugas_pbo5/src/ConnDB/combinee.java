/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConnDB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class combinee {
    private Connection connection;

    public static void main(String[] args) {
        combinee main = new combinee();
        main.ConnectDB("dbPBO", "postgres", "1031");

        int pilihan;
        do {
            System.out.println("Pilih menu:");
            System.out.println("1. Insert data");
            System.out.println("2. Update data");
            System.out.println("3. Delete data");
            System.out.println("4. View data");
            System.out.println("5. Exit");
            System.out.println("-----------------");
            System.out.print("Pilihan: ");

            Scanner scanner = new Scanner(System.in);
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    main.insertData();
                    break;
                case 2:
                    main.updateData();
                    break;
                case 3:
                    main.deleteData();
                    break;
                case 4:
                    main.viewData();
                    break;
                case 5:
                    System.out.println("Terima kasih, program selesai:D");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!! Silakan coba lagi!!");
                    System.out.println("===========================================");
                    System.out.println("");
                    break;
            }
        } while (pilihan != 5);
    }

    public void ConnectDB(String dbName, String username, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, username, password);
            System.out.println("Connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteData() {
        String query = "DELETE FROM tabel_mahasiswa WHERE nim = ?";
        PreparedStatement pstmt = null;

        try {
            System.out.println("Masukkan NIM yang akan dihapus: ");
            Scanner scanner = new Scanner(System.in);
            int nim = scanner.nextInt();

            boolean dataExists = checkDataExists(nim);

            if (!dataExists) {
                System.out.println("Data tidak ditemukan. Penghapusan dibatalkan.");
                System.out.println("==============================================");
                System.out.println("");
                return;
            }

            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, nim);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data berhasil dihapus!");
            } else {
                System.out.println("Gagal menghapus data.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateData() {
        String query = "UPDATE tabel_mahasiswa SET nama = ? WHERE nim = ?";
        PreparedStatement pstmt = null;

        try {
            System.out.println("Masukkan NIM yang akan di-update: ");
            Scanner scanner = new Scanner(System.in);
            int nim = scanner.nextInt();

            boolean dataExists = checkDataExists(nim);

            if (!dataExists) {
                System.out.println("Data tidak ditemukan. Update dibatalkan.");
                return;
            }

            System.out.println("Masukkan Nama Mahasiswa yang baru: ");
            String nama = scanner.nextLine();

            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, nama);
            pstmt.setInt(2, nim);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data berhasil di-update!");
            } else {
                System.out.println("Gagal meng-update data.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void viewData() {
        String query = "SELECT * FROM tabel_mahasiswa";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int nim = rs.getInt("nim");
                String nama = rs.getString("nama");
                String alamat = rs.getString("alamat");
                
                System.out.println("NIM: " + nim);
                System.out.println("Nama Mahasiswa: " + nama);
                System.out.println("Alamat: " + alamat);
                System.out.println("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void insertData() {
        String query = "INSERT INTO tabel_mahasiswa (nim, nama) "
                + "VALUES (?, ?)";
        PreparedStatement pstmt = null;

        try {
            boolean dataExists = false;
            do {
                System.out.println("Masukkan NIM: ");
                Scanner scanner = new Scanner(System.in);
                int nim = scanner.nextInt();

                dataExists = checkDataExists(nim);

                if (dataExists) {
                    System.out.println("Data sudah ada, masukkan NIM yang berbeda!!");
                    System.out.println("");
                } else {
                    System.out.println("Masukkan Nama: ");
                    String nama = scanner.nextLine();

                    pstmt = connection.prepareStatement(query);
                    pstmt.setInt(1, nim);
                    pstmt.setString(2, nama);

                    int k = pstmt.executeUpdate();
                    if (k > 0)
                        System.out.println("Data berhasil tersimpan :D");
                }
            } while (dataExists);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkDataExists(int nim) {
        String query = "SELECT * FROM tabel_mahasiswa WHERE nim = ?";
        PreparedStatement pstmt = null;
        boolean dataExists = false;

        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, nim);

            dataExists = pstmt.executeQuery().next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return dataExists;
    }
}