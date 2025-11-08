package kelas;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class product extends koneksi{
    private int productID;
    private String productName;
    private String productDescription;
    private int productCategory;
    private int productPrice;
    private final Connection koneksi;
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;
    
    public product(){
        koneksi = super.configDB();
    }
    
    public int getProductID() {
        return productID;
    }
    
    public void setProductID(int productID) {
        this.productID = productID;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getProductDescription() {
        return productDescription;
    }
    
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    
    public int getProductCategory() {
        return productCategory;
    }
    
    public void setProductCategory(int productCategory) {
        this.productCategory = productCategory;
    }
    
    public int getProductPrice() {
        return productPrice;
    }
    
    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
    
    public void tambahProduct(){
    query = "insert into product (productName, productCategory, productDescription, productPrice) values(?,?,?,?);";
    
    try {
        ps = koneksi.prepareStatement(query);
        ps.setString(1, productName);
        ps.setInt(2, productCategory);
        ps.setString(3, productDescription);
        ps.setInt(4, productPrice);
        ps.executeUpdate();
        ps.close();
        JOptionPane.showMessageDialog(null, "Berhasil");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Gagal" + e);
    }
}
public void hapusProduct(){
    query = "delete from product where productID =?;";
    
    try {
        ps = koneksi.prepareStatement(query);
        ps.setInt(1, productID);
        ps.executeUpdate();
        ps.close();
        JOptionPane.showMessageDialog(null, "Berhasil");
    } catch (SQLException sQLException) {
        JOptionPane.showMessageDialog(null, "Gagal");
    }
}
public void ubahProduct(){
    query = "update product set productName=?, productCategory=?, productDescription=?, productPrice=? where productID=?;";
    
    try {
        ps = koneksi.prepareStatement(query);
        ps.setString(1, productName);
        ps.setInt(2, productCategory);
        ps.setString(3, productDescription);
        ps.setInt(4, productPrice);
        ps.setInt(5, productID);
        ps.executeUpdate();
        ps.close();
        JOptionPane.showMessageDialog(null, "Berhasil");
    } catch (SQLException sQLException) {
        JOptionPane.showMessageDialog(null, "Gagal");
    }
}

public ResultSet TampilProduct(){
    query = "select p.productID, p.productName, p.productCategory, c.categoryName, p.productDescription, p.productPrice from product p inner join category c on p.productCategory = c.categoryID;";
    
    try {
        st = koneksi.createStatement();
        rs = st.executeQuery(query);
    } catch (SQLException sQLException) {
        JOptionPane.showMessageDialog(null, "GAGAL");
    }
    return rs;
}
    
   
}