package com.chatbot.dao;

import com.chatbot.conexao.ConexaoOracle;
import com.chatbot.model.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public void create(Categoria c) throws SQLException {
        String sql = "INSERT INTO chg_categorias_faq (nome_categoria, descricao) VALUES (?, ?)";
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, c.getNomeCategoria());
            st.setString(2, c.getDescricao());
            st.executeUpdate();
        }
    }

    public List<Categoria> readAll() throws SQLException {
        String sql = "SELECT id_categoria, nome_categoria, descricao FROM chg_categorias_faq ORDER BY id_categoria";
        List<Categoria> list = new ArrayList<>();
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Categoria c = new Categoria(rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(c);
            }
        }
        return list;
    }

    public void update(Categoria c) throws SQLException {
        String sql = "UPDATE chg_categorias_faq SET nome_categoria = ?, descricao = ? WHERE id_categoria = ?";
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, c.getNomeCategoria());
            st.setString(2, c.getDescricao());
            st.setInt(3, c.getIdCategoria());
            st.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM chg_categorias_faq WHERE id_categoria = ?";
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        }
    }
}