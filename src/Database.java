import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import org.json.JSONObject;

public class Database {
    // Database config
    private JSONObject config;

    // Connection
    Connection conn;

    public Database(String file) {
        try {
            String jsonString = this.getFileAsString(file);
            this.parseJsonString(jsonString);

            // Create connection instance
            Class.forName(this.config.getJSONObject("Development").getString("JDBCDriver"));
            this.conn = DriverManager.getConnection(
                this.config.getJSONObject("Development").getString("DBURL"),
                this.config.getJSONObject("Development").getString("Username"), 
                this.config.getJSONObject("Development").getString("Password")
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private String getFileAsString(String file) throws IOException {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    private void parseJsonString(String jsonString) {
        this.config = new JSONObject(jsonString);
    }
}