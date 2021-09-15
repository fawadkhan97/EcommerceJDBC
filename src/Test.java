import java.sql.*;

public class Test {
    public static void main(String[] a)
            throws Exception {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.
                    getConnection("jdbc:h2:~/test", "FAWAD", "123");
            System.out.println(conn);
            // add application code here
            Statement s = conn.createStatement();
            ResultSet resultSet = s.executeQuery("select * from items");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            System.out.println("Select * from items\n");
            int coloumnNumbers = resultSetMetaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= coloumnNumbers; i++) {
                    if (i > 1) System.out.print(", ");
                    System.out.print(resultSetMetaData.getColumnName(i).toLowerCase() + " " + resultSet.getString(i));
                }
                System.out.print("\n");

            }


            conn.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR: Class not found: " + ex.getMessage());
        }


    }
}