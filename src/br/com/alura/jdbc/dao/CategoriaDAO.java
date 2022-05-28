package br.com.alura.jdbc.dao;

import br.com.alura.jdbc.modelo.Categoria;
import br.com.alura.jdbc.modelo.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private Connection connection;

    public CategoriaDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Categoria> listar() {
        try {
            List<Categoria> categorias = new ArrayList<>();

            String sql = "SELECT ID, NOME FROM CATEGORIA;";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute();

                try (ResultSet result = statement.getResultSet()) {
                    while (result.next()) {
                        Categoria categoria =
                                new Categoria(result.getInt(1), result.getString(2));

                        categorias.add(categoria);
                    }
                }
            }
            return categorias;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Categoria> listarComProdutos() {
        try {
            Categoria ultima = null;
            List<Categoria> categorias = new ArrayList<>();

            String sql = "SELECT C.ID, C.NOME, P.ID, P.NOME, P.DESCRICAO FROM CATEGORIA C INNER JOIN PRODUTO P ON C.ID = P.CATEGORIA_ID";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.execute();

                try (ResultSet result = statement.getResultSet()) {
                    while (result.next()) {
                        if (ultima == null || !ultima.getNome().equals(result.getString(2))) {
                            Categoria categoria =
                                    new Categoria(result.getInt(1), result.getString(2));
                            ultima = categoria;
                            categorias.add(categoria);
                        }
                        Produto produto =
                                new Produto(result.getInt(3), result.getString(4), result.getString(5));
                        ultima.adicionar(produto);
                    }
                }
            }
            return categorias;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
