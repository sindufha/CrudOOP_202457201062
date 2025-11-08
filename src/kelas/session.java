
package kelas;

public class session {
    private static String username,email,fullname,status;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        session.username = username;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        session.email = email;
    }

    public static String getFullname() {
        return fullname;
    }

    public static void setFullname(String fullname) {
        session.fullname = fullname;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        session.status = status;
    }
    
}
