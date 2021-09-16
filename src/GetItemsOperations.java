import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetItemsOperations implements getItems {

    @Override
    public void getItemsDetails() throws SQLException {
        // get connection
        Connection connection = DBConnection.getDBConnection();
        final String viewItemsQuery = "select * from items";
        // Step 2:Create a statement using connection object
        PreparedStatement viewItems = connection.prepareStatement(viewItemsQuery);
        // Step 3: Execute the query or update query
        ResultSet resultSet = viewItems.executeQuery();
        // Step 4: Display the ResultSet object.

        if (!resultSet.next()){
            System.out.println("No items are available..");
        }
        else{
        int id;
        String name;
        int quantity;
        int price;
        System.out.println("ItemID\tItemName\tItemquantity\t UnitPrice\n -----------------------------------------------");
        do {
            id = resultSet.getInt("itemid");
            name = resultSet.getString("itemname");
            quantity = resultSet.getInt("itemquantity");
            price = resultSet.getInt("itemprice");
            System.out.format("%1s %10s %15s %15s ",id ,name ,quantity ,price);
            System.out.print("\n");
        }while (resultSet.next());


        }
    }

}
