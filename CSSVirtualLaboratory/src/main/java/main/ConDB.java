

package main;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConDB {
    
    public static Connection getConnection() throws Exception{
        
        String dbRoot = "jdbc:mysql://";
        String hostName = "localhost:3306/";
        String dbName = "virtual_lab";
        String dbURL = dbRoot+hostName+dbName;
        
        //database credentials (correct credentials) SOLVED
        String hostUsername = "root"; //change hostname for every computer? SOLVED
        String hostPassword = "";
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection myConn = (Connection)DriverManager.getConnection(dbURL, hostUsername, hostPassword);
            return myConn;
        }catch(Exception e){
            System.out.println(e);
            return null; //error null?
        }
        
    }
    
}

        /* 
        virtual_lab
        users

        User: root@localhost

        java.lang.NullPointerException: Cannot invoke "java.sql.Connection.prepareStatement(String)" because "myConn" is null

        DESKTOP-TRDL86N 
        1. Open the Command Prompt: press the Windows key + R to open the Run dialog box, type "cmd", and then press Enter.
        2. In the Command Prompt window, type "hostname" and then press Enter.
        3. The hostname of your computer will be displayed in the next line.
        */


        /*
        FIXED
        MySQL connector
        CSS Error parsing file:/C:/Users/ethan/OneDrive/Desktop/System/Virtual%20Laboratory%20CSS%20-%20v1.7/CSSVirtualLaboratory/target/classes/main/ButtonStyle.css: Unexpected token '80x' at [6,20]
        java.lang.ClassNotFoundException: com.mysql.cj.jdbc.Driver
        java.lang.NullPointerException: Cannot invoke "java.sql.Connection.prepareStatement(String)" because "myConn" is null
        */

        /*
        FIXED
        To fix this issue, you should check the following:

        Done  1. Make sure that the MySQL database server is running.
        Done  2. Verify that the database credentials (username and password) are correct.
        ???  3. Ensure that the MySQL JDBC driver is included in your project's classpath.
        Done  4. Check if there is any firewall or antivirus blocking the database connection.
        */