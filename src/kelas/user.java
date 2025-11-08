
package kelas;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class user extends koneksi{
    private String userName,userEmail,userPassword,userFullName;
    private int userStatus;
    private final Connection koneksi;
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;
    public user(){
        koneksi = super.configDB();
        
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String UserPassword) {
        this.userPassword = UserPassword;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public int getStatus() {
        return userStatus;
    }

    public void setStatus(int status) {
        this.userStatus = status;
    }
    
    public void tambahUser(){
        query = "insert into user values(?,?,MD5(?),?,?);";
        
        
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, userEmail);
            ps.setString(3, userPassword);
            ps.setString(4, userFullName);
            ps.setInt(5, userStatus);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Berhasil");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal" + e );
        }
    }
    public void hapusUser(){
        query = "delete from user where userName =?;";
        
        
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, userName);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Berhasil");
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Gagal");
        }
    }
    public void ubahUser(){
        if(userPassword.isEmpty()){
            query = "update user set userEmail=?,userFullname=?,userStatus=? where userName=?;";
        
        
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, userEmail);          
            ps.setString(2, userFullName);
            ps.setInt(3, userStatus);
            ps.setString(4, userName);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Berhasil");
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Gagal");
        }
        }
        query = "update user set userEmail=?,userPassword=MD5(?),userFullname=?,userStatus=? where userName=?;";
        
        
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, userEmail);
            ps.setString(2, userPassword);
            ps.setString(3, userFullName);
            ps.setInt(4, userStatus);
            ps.setString(5, userName);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Berhasil");
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Gagal");
        }
    }
    public ResultSet TampilUser(){
        query = "select * from user;";
        
            try {
                st = koneksi.createStatement();
                rs = st.executeQuery(query);
            } catch (SQLException sQLException) {
                JOptionPane.showMessageDialog(null, "GAGAL");
            }
        return rs;
                
    }
    
    
    public void reset(){
        
    }
    
    public void login(){
        query = "select * from user where userName=? and userPassword=md5(?)";
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, userPassword);
            rs = ps.executeQuery();
            
            if(rs.next()){
                session.setUsername(rs.getString("userName"));
                session.setEmail(rs.getString("userEmail"));
                session.setFullname(rs.getString("userFullName"));
                session.setStatus("aktif");
            }else{
                session.setStatus("nonaktif");
                JOptionPane.showMessageDialog(null, "GAGAL LOGIN");
            }
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Gagal karena sql" + sQLException);
        }
        
    }
    public void logout(){
        session.setUsername("");
        session.setFullname("");
        session.setEmail("");
        session.setStatus("Non Aktif");
    }
    
}
