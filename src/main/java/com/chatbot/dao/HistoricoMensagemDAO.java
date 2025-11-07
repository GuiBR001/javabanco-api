package com.chatbot.dao;

import com.chatbot.conexao.ConexaoOracle;
import com.chatbot.model.HistoricoMensagem;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoricoMensagemDAO {

    public void create(HistoricoMensagem h) throws SQLException {
        String sql = "INSERT INTO chg_historico_mensagens (id_log, origem_mensagem, conteudo, data_hora) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, h.getIdLog());
            st.setString(2, h.getOrigemMensagem());
            st.setString(3, h.getConteudo());
            st.setTimestamp(4, Timestamp.valueOf(h.getDataHora()));
            st.executeUpdate();
        }
    }

    public List<HistoricoMensagem> readAll() throws SQLException {
        String sql = "SELECT id_mensagem, id_log, origem_mensagem, conteudo, data_hora FROM chg_historico_mensagens ORDER BY id_mensagem";
        List<HistoricoMensagem> list = new ArrayList<>();
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                LocalDateTime dt = rs.getTimestamp(5) != null ? rs.getTimestamp(5).toLocalDateTime() : null;
                HistoricoMensagem h = new HistoricoMensagem(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), dt);
                list.add(h);
            }
        }
        return list;
    }

    public List<HistoricoMensagem> findByLog(int idLog) throws SQLException {
        String sql = "SELECT id_mensagem, id_log, origem_mensagem, conteudo, data_hora FROM chg_historico_mensagens WHERE id_log = ? ORDER BY id_mensagem";
        List<HistoricoMensagem> list = new ArrayList<>();
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, idLog);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    LocalDateTime dt = rs.getTimestamp(5) != null ? rs.getTimestamp(5).toLocalDateTime() : null;
                    HistoricoMensagem h = new HistoricoMensagem(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), dt);
                    list.add(h);
                }
            }
        }
        return list;
    }

    public void update(HistoricoMensagem h) throws SQLException {
        String sql = "UPDATE chg_historico_mensagens SET id_log = ?, origem_mensagem = ?, conteudo = ?, data_hora = ? WHERE id_mensagem = ?";
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, h.getIdLog());
            st.setString(2, h.getOrigemMensagem());
            st.setString(3, h.getConteudo());
            st.setTimestamp(4, Timestamp.valueOf(h.getDataHora()));
            st.setInt(5, h.getIdMensagem());
            st.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM chg_historico_mensagens WHERE id_mensagem = ?";
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        }
    }
}