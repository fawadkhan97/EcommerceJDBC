import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Order  {

    final String getOrderidQuery = "SELECT MAX (orderid) FROM orders;";
    Connection connection = DBConnection.getDBConnection();
    int getOrderId()throws Exception {
        // get latest order id from db
        PreparedStatement getOrderidStatement = connection.prepareStatement(getOrderidQuery);
        ResultSet resultSet = getOrderidStatement.executeQuery();
        int Orderid = -1;
        // check if id exists before fetching
        if (!resultSet.next()) {
            System.out.println("Table is empty");
        } else {
            // fetch order id
            Orderid = resultSet.getInt("MAX(ORDERID)");
            System.out.println("order id: " + Orderid);
        }
        return Orderid;
    };

}
