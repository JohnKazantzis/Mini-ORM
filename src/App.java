import java.util.ArrayList;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
        Database db = new Database("config/database.json");

        DatabaseQuery queryOperation = new DatabaseQuery(db.conn);

        // Simple no filter queries
        StringBuilder statementBuilder1 = queryOperation.query(new ArrayList<String> (Arrays.asList("\"id\"", "\"name\"")), "\"User\"");
        System.out.println("QUERY: " + statementBuilder1.toString());

        System.out.println("Results: " + queryOperation.executeSelectStatement(statementBuilder1));

        System.out.println("\n\n");
        StringBuilder statementBuilder2 = queryOperation.query(null, "\"User\"");
        System.out.println("QUERY: " + statementBuilder2.toString());

        System.out.println("Results: " + queryOperation.executeSelectStatement(statementBuilder2));
    }
}
