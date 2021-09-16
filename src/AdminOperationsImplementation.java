import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminOperationsImplementation implements adminOperations {
    Connection connection = DBConnection.getDBConnection();

    @Override
    public void addNewitems(Items items) throws SQLException {
        final String insertItemsQuery = "INSERT INTO items" +
                "  (itemname, itemprice,itemquantity) VALUES " +
                " (?, ?,?);";
        PreparedStatement insertItems = connection.prepareStatement(insertItemsQuery);
        insertItems.setString(1, items.getItemName());
        insertItems.setInt(2, items.getItemprice());
        insertItems.setInt(3, items.getQuantity());

        System.out.println(insertItems);

        insertItems.executeUpdate();
    }


    @Override
    public void modifyItems(int id, Items items) throws SQLException {
        final String UpdateItemsQuery = "update items set itemname = ? , itemprice=?, itemquantity=?  where itemid = ?;";
        PreparedStatement insertItems = connection.prepareStatement(UpdateItemsQuery);
        insertItems.setString(1, items.getItemName());
        insertItems.setInt(2, items.getItemprice());
        insertItems.setInt(3, items.getQuantity());
        insertItems.setInt(4, id);
        System.out.println(insertItems);
        insertItems.executeUpdate();

    }
}
