import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbOperations {
    final String getOrderidQuery = "SELECT MAX (orderid) FROM orders;";
    final String getitempriceQuery = "SELECT ITEMPRICE from items where itemid =?;";
    Connection connection = DBConnection.getDBConnection();

    public  int getOrderId() throws Exception {
        // get the latest order id from db
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
    }

    public int getItemPrice(int itemId) throws Exception {

        PreparedStatement getItemPriceStatement = connection.prepareStatement(getitempriceQuery);
        getItemPriceStatement.setInt (1, itemId );
        System.out.println("item query" + getItemPriceStatement);
        ResultSet itemPriceResultSet = getItemPriceStatement.executeQuery();
       if(!itemPriceResultSet.next()){
           System.out.println("item not found , incorrect id was enter");
       }else {
           int itemPrice = itemPriceResultSet.getInt("ITEMPRICE");
           int orderPrice = 1;
           //     orderPrice = itemQuantity * itemPrice;
           System.out.println("Item price is " + itemPrice + "\t DbOperations price is " + orderPrice);

        return itemPrice;}
       return -1;
    }

}
