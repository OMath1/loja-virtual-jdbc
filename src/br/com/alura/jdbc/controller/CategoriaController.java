package br.com.alura.jdbc.controller;

import br.com.alura.jdbc.dao.CategoriaDAO;
import br.com.alura.jdbc.factory.ConnectionFactory;
import br.com.alura.jdbc.modelo.Categoria;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CategoriaController {

    private CategoriaDAO categoriaDAO;

    public CategoriaController() {
        Connection connection = (Connection) new ConnectionFactory().recuperarConexao();
        this.categoriaDAO = new CategoriaDAO(connection);
    }

    public List<Categoria> listar() {
        return this.categoriaDAO.listar();
    }
}
