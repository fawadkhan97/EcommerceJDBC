import java.sql.Connection;
import java.sql.SQLException;

public interface adminOperations {
    void modifyItems(String name) throws SQLException;
    void addNewitems(Items items) throws SQLException;
}
