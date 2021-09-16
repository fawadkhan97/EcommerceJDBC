import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminOperationsImplementation implements adminOperations {
    Connection connection = DBConnection.getDBConnection() ;
    @Override
    public void addNewitems(Items items) throws SQLException {
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
    public void modifyItems(int id) throws SQLException {
        final String UpdateItemsQuery = "update users set itemname = ?, set unitprice=?,set itemquantity=?, where itemid = ?;"
        PreparedStatement insertItems = connection.prepareStatement(UpdateItemsQuery);
        insertItems.setString(1 );
        insertItems.setInt(2, items.getUnitPrice());
        insertItems.setInt(3,items.getQuantity());


    }
}
