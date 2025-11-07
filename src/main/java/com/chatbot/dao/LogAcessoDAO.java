package com.chatbot.dao;

import com.chatbot.conexao.ConexaoOracle;
import com.chatbot.model.LogAcesso;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogAcessoDAO {

    public void create(LogAcesso l) throws SQLException {
        String sql = "INSERT INTO chg_log_acessos (data_hora_inicio, data_hora_fim, duracao) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setTimestamp(1, Timestamp.valueOf(l.getDataHoraInicio()));
            if (l.getDataHoraFim() != null) st.setTimestamp(2, Timestamp.valueOf(l.getDataHoraFim()));
            else st.setNull(2, Types.TIMESTAMP);
            if (l.getDuracao() != null) st.setInt(3, l.getDuracao());
            else st.setNull(3, Types.INTEGER);
            st.executeUpdate();
        }
    }

    public List<LogAcesso> readAll() throws SQLException {
        String sql = "SELECT id_log, data_hora_inicio, data_hora_fim, duracao FROM chg_log_acessos ORDER BY id_log";
        List<LogAcesso> list = new ArrayList<>();
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                LocalDateTime inicio = rs.getTimestamp(2) != null ? rs.getTimestamp(2).toLocalDateTime() : null;
                LocalDateTime fim = rs.getTimestamp(3) != null ? rs.getTimestamp(3).toLocalDateTime() : null;
                LogAcesso l = new LogAcesso(rs.getInt(1), inicio, fim, rs.getObject(4) != null ? rs.getInt(4) : null);
                list.add(l);
            }
        }
        return list;
    }

    public void update(LogAcesso l) throws SQLException {
        String sql = "UPDATE chg_log_acessos SET data_hora_inicio = ?, data_hora_fim = ?, duracao = ? WHERE id_log = ?";
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setTimestamp(1, Timestamp.valueOf(l.getDataHoraInicio()));
            if (l.getDataHoraFim() != null) st.setTimestamp(2, Timestamp.valueOf(l.getDataHoraFim()));
            else st.setNull(2, Types.TIMESTAMP);
            if (l.getDuracao() != null) st.setInt(3, l.getDuracao());
            else st.setNull(3, Types.INTEGER);
            st.setInt(4, l.getIdLog());
            st.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM chg_log_acessos WHERE id_log = ?";
        try (Connection conn = ConexaoOracle.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        }
    }
}