package dbTest;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection conn;

    public static Connection DBConnection(){
        try{
            String user = "yuhan22";
            String password = "yuhan1234";
            String url = "jdbc:oracle:thin:@localhost:1521/XE";

            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected To Oracle db");
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return conn;
    }
}


