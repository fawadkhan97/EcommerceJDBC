import java.sql.*;

public class DBConnection {
   private static String jdbcURL = "jdbc:h2:tcp://localhost/~/test";
    private static String jdbcUsername = "sa";
    private static String jdbcPassword = "";


    public static Connection getDBConnection(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
             connection = DriverManager.
                    getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            // add application code here
       /*     Statement s = connection.createStatement();
            ResultSet resultSet = s.executeQuery("select * from items");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            System.out.println("Select * from items\n");
            int coloumnNumbers = resultSetMetaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= coloumnNumbers; i++) {
                    if (i > 1) System.out.print("   ");
                    System.out.print(resultSetMetaData.getColumnName(i).toLowerCase() + ":  " + resultSet.getString(i));
                }
                System.out.print("\n");

            }


            connection.close();*/
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("ERROR: Class not found: " + ex.getMessage());
        }

        return connection;


    }
}