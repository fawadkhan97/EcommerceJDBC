import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerOperationsImplementation implements customerOperations {
    final String insertOrder_items = "INSERT INTO order_items" +
            "  (orderid, orderItemsID , orderquantity,orderprice) VALUES " +
            " (?, ?, ?,?);";
    final String setOrderStatusQuery = "INSERT INTO Orders" +
            "  (orderStatus) VALUES " +
            " (?);";
    final String getOrderItemsQuery = "select  ORDERID, itemname , ITEMID, ORDERQUANTITY, ORDERPRICE from ORDER_ITEMS  join items on itemid=ORDERITEMSID where orderid =?;";
    Connection connection = DBConnection.getDBConnection();


    @Override
    public void createOrder() throws SQLException {
        // insert into Orders table
        PreparedStatement insertItemsStatement = connection.prepareStatement(setOrderStatusQuery);
        insertItemsStatement.setString(1, "Pending...");
        insertItemsStatement.executeUpdate();
    }

    //customers interface implementation
    @Override
    public void createOrder_items(int itemId, int Itemquantity, int itemPrice) throws SQLException {
        // insert into Order_items table
        PreparedStatement insertOrderItems = connection.prepareStatement(insertOrder_items);

        DbOperations dbOperations = new DbOperations();
        int Orderid = 0;
        try {
            Orderid = dbOperations.getOrderId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int orderPrice = itemPrice * Itemquantity;
        System.out.println("creating orders itemid is : " + itemId + " QUANITTY: " + Itemquantity + "orderprice: " + orderPrice);


        // insert into table columns
        insertOrderItems.setInt(1, Orderid);
        insertOrderItems.setInt(2, itemId);
        insertOrderItems.setInt(3, Itemquantity);
        insertOrderItems.setInt(4, orderPrice);
        System.out.println(insertOrderItems);
        insertOrderItems.executeUpdate();
        printOrderDetails(Orderid, itemId);
    }

    @Override
    public void printOrderDetails(int orderId, int itemId) throws SQLException {
        // Step 2:Create a statement using connection object
        PreparedStatement viewOrderItems = connection.prepareStatement(getOrderItemsQuery);
        viewOrderItems.setInt(1, orderId);

        // Step 3: Execute the query or update query
        ResultSet resultSet = viewOrderItems.executeQuery();
        // Step 4: Display the ResultSet object.

        if (!resultSet.next()) {
            System.out.println("No items are available..");
        } else {

            System.out.println("DbOperations place ");
            System.out.println("OrderID \t ItemName2 \t ItemID \t OrderQuantity \t OrderPrice \n---------------------------------------------------");
            do {

                System.out.format("%2s %15s %14s %13s %13s", resultSet.getInt("orderid"), resultSet.getString("itemname"),
                        resultSet.getInt("itemid"), resultSet.getInt("orderquantity"), resultSet.getInt("orderprice"));
                System.out.print("\n");
                System.out.println("---------------------------------------------------");
            } while (resultSet.next());

        }
    }

    @Override
    public void markOrderCompleted() throws SQLException {

    }

    @Override
    public void printInvoice(int Orderid) throws SQLException {

        PreparedStatement viewOrderItems = connection.prepareStatement(getOrderItemsQuery);
        viewOrderItems.setInt(1, Orderid);

        // Step 3: Execute the query or update query
        ResultSet resultSet = viewOrderItems.executeQuery();
        // Step 4: Display the ResultSet object.

        if (!resultSet.next()) {
            System.out.println("No items are available..");
        } else {

            System.out.println("DbOperations place ");
            System.out.println("OrderID \t ItemName2 \t ItemID \t OrderQuantity \t OrderPrice \n---------------------------------------------------");
            do {

                System.out.format("%2s %15s %14s %13s %13s", resultSet.getInt("orderid"), resultSet.getString("itemname"),
                        resultSet.getInt("itemid"), resultSet.getInt("orderquantity"), resultSet.getInt("orderprice"));
                System.out.print("\n");
                System.out.println("---------------------------------------------------");
            } while (resultSet.next());

        }



    }
}
