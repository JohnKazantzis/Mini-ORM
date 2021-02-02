import java.sql.Connection;

public abstract class DatabaseOperations {
    protected Connection conn;

    public DatabaseOperations(Connection conn) {
        this.conn = conn;
    } 
}
