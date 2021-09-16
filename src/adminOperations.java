import java.sql.Connection;
import java.sql.SQLException;

public interface adminOperations {
    void modifyItems(int id, Items items) throws SQLException;
    void addNewitems(Items items) throws SQLException;
}
