import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminOperationsImplementation implements adminOperations {

    @Override
    public void addNewitems(Items items) throws SQLException {

         Connection connection = DBConnection.getDBConnection() ;
       final String insertItemsQuery = "INSERT INTO items" +
                "  (itemname, unitprice,itemquantity) VALUES " +
                " (?, ?,?);";
        PreparedStatement insertItems = connection.prepareStatement(insertItemsQuery);
            insertItems.setString(1, items.getItemName());
            insertItems.setInt(2, items.getUnitPrice());
            insertItems.setInt(3,items.getQuantity());

            System.out.println(insertItems);

            insertItems.executeUpdate();
        }




    @Override
    public void modifyItems(String name) {



    }
}
