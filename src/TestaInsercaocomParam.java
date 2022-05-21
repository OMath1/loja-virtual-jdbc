import java.sql.*;

public class TestaInsercaocomParam {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.recuperarConexao()) {
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO produto (nome, descricao) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {

                addVariable("Macbook pro", "chip M1 max", statement);
                addVariable("Teclado Logitech", "Teclado resistente", statement);

                connection.commit();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ROLLBACK EXECUTADO");
                connection.rollback();
            }
        }

    }

    private static void addVariable(String nome, String descricao, PreparedStatement statement) throws SQLException {
        statement.setString(1, nome);
        statement.setString(2, descricao);

//        if (nome.equals("Teclado Logitech")) {
//            throw new RuntimeException("Nao foi possivel add o produto");
//        }

        statement.execute();

        try (ResultSet resultSet = statement.getGeneratedKeys()) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                System.out.println("O ID criado foi: " + id);
            }
        }
    }
}