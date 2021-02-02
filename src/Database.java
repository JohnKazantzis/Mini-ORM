import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;

import org.json.JSONObject;

public class Database {
    // Database config
    private String file;
    private JSONObject config;

    // Connection
    Connection conn;

    public Database(String file) {
        try {
            String jsonString = this.getFileAsString(file);
            this.parseJsonString(jsonString);

            System.out.println("Config: " + this.config.getJSONObject("Development").getString("Username"));
            Class.forName(this.config.getJSONObject("Development").getString("JDBCDriver"));
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