import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerOperationsImplementation implements customerOperations {
    Connection connection = DBConnection.getDBConnection();

    @Override
    public void createOrder(int id) throws SQLException {
        // insert into Orders table
        final String OrderstatusQuery = "INSERT INTO Orders" +
                "  (orderStatus) VALUES " +
                " (?);";
        PreparedStatement insertItems = connection.prepareStatement(OrderstatusQuery);
        insertItems.setString(1, "Pending...");
        System.out.println(insertItems);
        insertItems.executeUpdate();
    }


    @Override
    public void createOrder_items(int itemid, int quantity) throws SQLException {
        System.out.println("creating orders itemid is : " + itemid + " QUANITTY: " + quantity);
        // get latest order id from db
        final String getOrderidQuery = "SELECT MAX (orderid) FROM orders;";
        PreparedStatement getOrderid = connection.prepareStatement(getOrderidQuery);
        ResultSet resultSet = getOrderid.executeQuery();
        int Orderid = -1;

        // check if id exists before fetching
        if (!resultSet.next()) {
            System.out.println("Table is empty");
        } else {
            // fetch order id
            Orderid = resultSet.getInt("MAX(ORDERID)");
            System.out.println("order id: " + Orderid);
            printOrderDetails(Orderid);
        }
        // insert into Order_items table
        final String insertOrder_items = "INSERT INTO orders_items" +
                "  (orderid, itemid, orderquantity) VALUES " +
                " (?, ?, ?);";
        PreparedStatement insertOrderItems = connection.prepareStatement(insertOrder_items);
        // insert into table columns
        insertOrderItems.setInt(1, Orderid);
        insertOrderItems.setInt(2, itemid);
        insertOrderItems.setInt(3, quantity);
        System.out.println(insertOrderItems);
        insertOrderItems.executeUpdate();
        printOrderDetails(Orderid);
    }

    @Override
    public void printOrderDetails(int orderId) throws SQLException {
        final String viewOrderItemsQuery = "select * from ORDERS_ITEMS where orderid >= ?";
        // Step 2:Create a statement using connection object
        PreparedStatement viewOrderItems = connection.prepareStatement(viewOrderItemsQuery);
        viewOrderItems.setInt(1, orderId);

        // Step 3: Execute the query or update query
        ResultSet resultSet = viewOrderItems.executeQuery();
        // Step 4: Display the ResultSet object.

        if (!resultSet.next()) {
            System.out.println("No items are available..");
        } else {
            int orderId1;
            int quantity;
            int itemId;
            System.out.println("Order place ");
            System.out.println("OrderID \t ItemID \t OrderQuantity\n -----------------------------------------------");
            do {
                orderId1 = resultSet.getInt("orderid");
                itemId = resultSet.getInt("itemid");
                quantity = resultSet.getInt("orderquantity");
                System.out.format("%1s %10s %15s ", orderId1, itemId, quantity);
                System.out.print("\n");
            } while (resultSet.next());

        }
    }

    @Override
    public void markOrderCompleted() throws SQLException {

    }

    @Override
    public void printInvoice() throws SQLException {

    }
}
