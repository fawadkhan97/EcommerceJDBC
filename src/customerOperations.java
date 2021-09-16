import java.sql.SQLException;

public interface customerOperations {

    void createOrder (int itemid  ) throws SQLException;
    void createOrder_items(int itemid, int quantity) throws SQLException;
    void printOrderDetails(int orderid)  throws SQLException;

    void markOrderCompleted()  throws SQLException;
    void printInvoice()  throws SQLException;
}
