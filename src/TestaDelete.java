import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestaDelete {
    public static void main(String[] args) throws SQLException {
        int numeroDeRemocao = 2;

        ConnectionFactory connectionFactory = new ConnectionFactory();

        try (Connection connection = connectionFactory.recuperarConexao()) {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM produto WHERE ID > ?");
            statement.setInt(1, numeroDeRemocao);
            statement.execute();

            int updateCount = statement.getUpdateCount();
            System.out.println(updateCount);
        }
    }
}
