package kelas;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane; 

public class category extends koneksi{
    private int categoryID;
    private String categoryName;
    private final Connection koneksi;
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;
    
    public category(){
        koneksi = super.configDB();
    }
    
    public int getCategoryID() {
        return categoryID;
    }
    
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
    
    public String getCategoryName() {
        return categoryName;
    }
    
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }   
    
    public void tambahCategory(){
        query = "insert into category values(?,?);";
        
        try {
            ps = koneksi.prepareStatement(query);
            ps.setInt(1, categoryID);
            ps.setString(2, categoryName);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Berhasil");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal" + e);
        }
    }
    
    public void hapusCategory(){
        query = "delete from category where categoryId =?;";
        
        try {
            ps = koneksi.prepareStatement(query);
            ps.setInt(1, categoryID);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Berhasil");
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Gagal");
        }
    }
    
    public void ubahCategory(){
        query = "update category set categoryName=? where categoryId=?;";
        
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, categoryName);
            ps.setInt(2, categoryID);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Berhasil");
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Gagal");
        }
    }
    
    public ResultSet TampilCategory(){
        query = "select * from category;";
        
        try {
            st = koneksi.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "GAGAL");
        }
        return rs;
    }
   
}