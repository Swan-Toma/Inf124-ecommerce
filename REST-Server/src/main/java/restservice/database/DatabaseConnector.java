package restservice.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnector {

    private DatabaseConnector() {
    }

    public static Connection getConnection() {

        try {
            String dbDriver = "com.mysql.cj.jdbc.Driver";
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        try {
            Properties properties = new Properties();
            String database = "arts_and_crafts";
            /*
                Add config.properties in Glassfish config folder:
                Mac Glassfish config path:
                /Users/[name]/GlassFish_Server/glassfish/domains/domain1/config
                Add these to the file:
                useSSL=false
                allowPublicKeyRetrieval=true
                serverTimezone=UTC
                user=db_name
                password=db_password
            */
            String cwd = new File("").getAbsolutePath();
            String filePath = cwd + "/dbconfig.properties";
            InputStream input = new FileInputStream(filePath);
            properties.load(input);
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, properties);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException fne) {
            fne.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return null;
    }
}
