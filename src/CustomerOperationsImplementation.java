import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerOperationsImplementation implements customerOperations {
    Connection connection = DBConnection.getDBConnection();
    final String getitempriceQuery = "SELECT ITEMPRICE from items where itemid =?;";
    final String insertOrder_items = "INSERT INTO order_items" +
            "  (orderid, itemid, orderquantity,orderprice) VALUES " +
            " (?, ?, ?,?);";
    final String setOrderStatusQuery = "INSERT INTO Orders" +
            "  (orderStatus) VALUES " +
            " (?);";

    @Override
    public void createOrder() throws SQLException {
        // insert into Orders table
        PreparedStatement insertItemsStatement = connection.prepareStatement(setOrderStatusQuery);
        insertItemsStatement.setString(1, "Pending...");
        insertItemsStatement.executeUpdate();
    }

    //customers interface implementation
    @Override
    public void createOrder_items(int itemId, int Itemquantity) throws SQLException {
        System.out.println("creating orders itemid is : " + itemId + " QUANITTY: " + Itemquantity);

        // get items price
        PreparedStatement getItemPriceStatement = connection.prepareStatement(getitempriceQuery);
        getItemPriceStatement.setInt(1,6);

        System.out.println("item query"+getItemPriceStatement);
        ResultSet itemPriceResultSet = getItemPriceStatement.executeQuery();
        itemPriceResultSet.next();
        int itemPrice = itemPriceResultSet.getInt("ITEMPRICE");
        int orderPrice = 1;
        orderPrice=Itemquantity * itemPrice;
        System.out.println("Item price is "+itemPrice + "\t Order price is "+orderPrice);



        // insert into Order_items table
        PreparedStatement insertOrderItems = connection.prepareStatement(insertOrder_items);
        // insert into table columns
        insertOrderItems.setInt(1, Orderid);
        insertOrderItems.setInt(2, itemId);
        insertOrderItems.setInt(3, Itemquantity);
        insertOrderItems.setInt(4, orderPrice);
        System.out.println(insertOrderItems);
        insertOrderItems.executeUpdate();
        printOrderDetails(Orderid);
    }

    @Override
    public void printOrderDetails(int orderId) throws SQLException {
        final String viewOrderItemsQuery = "select * from ORDER_ITEMS where orderid >= ?";
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
            int orderPrice;
            System.out.println("Order place ");
            System.out.println("OrderID \t ItemID \t OrderQuantity \t OrderPrice \n---------------------------------------------------");
            do {
                orderId1 = resultSet.getInt("orderid");
                itemId = resultSet.getInt("itemid");
                quantity = resultSet.getInt("orderquantity");
                orderPrice=resultSet.getInt("orderprice");
                System.out.format("%1s %12s %16s %16s", orderId1, itemId, quantity , orderPrice);
                System.out.print("\n");
                System.out.println("---------------------------------------------------");
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
