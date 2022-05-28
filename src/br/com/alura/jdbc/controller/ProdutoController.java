package br.com.alura.jdbc.controller;

import br.com.alura.jdbc.dao.ProdutoDAO;
import br.com.alura.jdbc.factory.ConnectionFactory;
import br.com.alura.jdbc.modelo.Categoria;
import br.com.alura.jdbc.modelo.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class ProdutoController {

    private ProdutoDAO produtoDAO;

    public ProdutoController() {
        Connection connection = new ConnectionFactory().recuperarConexao();
        this.produtoDAO = new ProdutoDAO(connection);
    }

    public void salvar(Produto produto) {
        this.produtoDAO.salvar(produto);
    }

    public void salvarComCategoria(Produto produto) {
        this.produtoDAO.salvarComCategoria(produto);
    }

    public List<Produto> listar() {
        return  this.produtoDAO.listar();
    }

    public List<Produto> buscar(Categoria ct) {
        return this.produtoDAO.buscar(ct);
    }

    public void deletar(Integer id) {
        this.produtoDAO.deletar(id);
    }

    public void alterar(String nome, String descricao, Integer id) {
        this.produtoDAO.alterar(nome, descricao, id);
    }
}
