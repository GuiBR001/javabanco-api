package com.chatbot.dao;

import com.chatbot.conexao.ConexaoOracle;
import com.chatbot.model.Faq;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FaqDAO {

    public void create(Faq f) throws SQLException {
        String sql = "INSERT INTO chg_faq_chatbot (pergunta, resposta, id_categoria) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, f.getPergunta());
            st.setString(2, f.getResposta());
            st.setInt(3, f.getIdCategoria());
            st.executeUpdate();
        }
    }

    public List<Faq> readAll() throws SQLException {
        String sql = "SELECT id_faq, pergunta, resposta, id_categoria FROM chg_faq_chatbot ORDER BY id_faq";
        List<Faq> list = new ArrayList<>();
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Faq f = new Faq(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                list.add(f);
            }
        }
        return list;
    }

    public List<Faq> findByCategoria(int idCategoria) throws SQLException {
        String sql = "SELECT id_faq, pergunta, resposta, id_categoria FROM chg_faq_chatbot WHERE id_categoria = ? ORDER BY id_faq";
        List<Faq> list = new ArrayList<>();
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, idCategoria);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Faq f = new Faq(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                    list.add(f);
                }
            }
        }
        return list;
    }

    public void update(Faq f) throws SQLException {
        String sql = "UPDATE chg_faq_chatbot SET pergunta = ?, resposta = ?, id_categoria = ? WHERE id_faq = ?";
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, f.getPergunta());
            st.setString(2, f.getResposta());
            st.setInt(3, f.getIdCategoria());
            st.setInt(4, f.getIdFaq());
            st.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM chg_faq_chatbot WHERE id_faq = ?";
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        }
    }
}