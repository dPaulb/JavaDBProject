package Member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;


    public MemberDAO() {
        try {
            String user = "yuhan22";
            String password = "yuhan1234";
            String url = "jdbc:oracle:thin:@localhost:1521/XE";

            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected To Oracle db");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int join(MemberDTO memberDTO) {
        String SQL = "INSERT INTO yuhan22.member VALUES(?, ?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, memberDTO.getUserID());
            pstmt.setString(2, memberDTO.getUserPassword());
            pstmt.setInt(3, memberDTO.getUserAge());
            pstmt.setString(4, memberDTO.getUserGender());
            pstmt.setString(5, memberDTO.getUserEmail());

            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int login(MemberDTO memberDTO){
        String SQL = "SELECT * FROM yuhan22.member WHERE userID = ? and userPassword = ?";
        try{
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, memberDTO.getUserID());
            pstmt.setString(2, memberDTO.getUserPassword());
            rs = pstmt.executeQuery();
            if(rs.next()){
                return 1;
            }
            return -1;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return -2;
    }
}
