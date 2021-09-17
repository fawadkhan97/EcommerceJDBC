import java.sql.SQLException;

public interface customerOperations {

    void createOrder () throws SQLException;
    void createOrder_items(int itemid, int quantity,int itemPrice) throws SQLException;
    void printOrderDetails(int orderid, int itemID)  throws SQLException;

    void markOrderCompleted()  throws SQLException;
    int printInvoice(int Orderid)  throws SQLException;
}
