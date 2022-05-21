import java.sql.*;

public class TestaListagem {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try (Connection connection = connectionFactory.recuperarConexao()) {

            try (PreparedStatement statement = connection.prepareStatement("select id, nome, descricao from produto")) {
                statement.execute();

                try (ResultSet result = statement.getResultSet()) {

                    while (result.next()) {
                        Integer id = result.getInt("ID");
                        System.out.println(id);
                        String nome = result.getString("NOME");
                        System.out.println(nome);
                        String descricao = result.getString("DESCRICAO");
                        System.out.println(descricao);
                    }
                }
            }
        }
    }
}
