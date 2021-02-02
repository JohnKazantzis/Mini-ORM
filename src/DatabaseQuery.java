import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseQuery extends DatabaseOperations {

    public DatabaseQuery(Connection c) {
        super(c);
    }

    /**
     * Simple database query without filters
     * @param columns The columns of the table to be returned
     *                If null return all the columns of the table
     * @param table Name of the table to be queried
     */
    public StringBuilder query(ArrayList<String> columns, String table) {
        StringBuilder query = new StringBuilder().append("Select ");
        
        // Check if all the columns should be selected
        if(columns == null) {
            query.append("* ");
            
        }
        else {
            for(String s : columns) {
                query.append(s + ", ");
            }

            query.setLength(query.length() - 2);
            query.append(" ");
        }
        query.append("FROM ").append(table).toString();

        return query;
    }

    public StringBuilder setLimit(StringBuilder statement, int limit) {
        return null;
    }

    /**
     * Executes the select query
     * @param statement The query statement to be executed
     */
    public List<HashMap<String, Object>> executeSelectStatement(StringBuilder statementBuilder) {
        String statement = statementBuilder.toString();
        Statement statementToExecuted;

        List<HashMap<String, Object>> results = new ArrayList<HashMap<String, Object>>();
        try {
            statementToExecuted = this.conn.createStatement();
            ResultSet queryResults = statementToExecuted.executeQuery(statement);

            while(queryResults.next())
            {   
                HashMap<String, Object> tempMap = new HashMap<String, Object>();
                for(int i=1; i<=queryResults.getMetaData().getColumnCount(); i++) {
                    tempMap.put(queryResults.getMetaData().getColumnName(i), queryResults.getObject(i));
                }

                results.add(tempMap);
            }
            
            return results;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

            return null;
        }
    }
}
