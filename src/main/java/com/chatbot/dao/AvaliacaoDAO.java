package com.chatbot.dao;

import com.chatbot.conexao.ConexaoOracle;
import com.chatbot.model.Avaliacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoDAO {

    public void create(Avaliacao a) throws SQLException {
        String sql = "INSERT INTO chg_avaliacoes_chatbot (id_log, nota, comentario) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, a.getIdLog());
            st.setInt(2, a.getNota());
            st.setString(3, a.getComentario());
            st.executeUpdate();
        }
    }

    public List<Avaliacao> readAll() throws SQLException {
        String sql = "SELECT id_avaliacao, id_log, nota, comentario FROM chg_avaliacoes_chatbot ORDER BY id_avaliacao";
        List<Avaliacao> list = new ArrayList<>();
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Avaliacao a = new Avaliacao(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
                list.add(a);
            }
        }
        return list;
    }

    public int countByNota(int nota) throws SQLException {
        String sql = "SELECT COUNT(*) FROM chg_avaliacoes_chatbot WHERE nota = ?";
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, nota);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return 0;
    }

    public double averageNota() throws SQLException {
        String sql = "SELECT AVG(nota) FROM chg_avaliacoes_chatbot";
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            if (rs.next()) return rs.getDouble(1);
        }
        return 0.0;
    }

    public void update(Avaliacao a) throws SQLException {
        String sql = "UPDATE chg_avaliacoes_chatbot SET id_log = ?, nota = ?, comentario = ? WHERE id_avaliacao = ?";
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, a.getIdLog());
            st.setInt(2, a.getNota());
            st.setString(3, a.getComentario());
            st.setInt(4, a.getIdAvaliacao());
            st.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM chg_avaliacoes_chatbot WHERE id_avaliacao = ?";
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        }
    }
}