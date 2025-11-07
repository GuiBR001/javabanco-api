package com.chatbot.api;

import com.chatbot.dao.HistoricoMensagemDAO;
import com.chatbot.model.HistoricoMensagem;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Path("/historico")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HistoricoResource {

    private final HistoricoMensagemDAO dao = new HistoricoMensagemDAO();

    @GET
    public List<HistoricoMensagem> listarTudo() {
        try {
            return dao.readAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @GET
    @Path("/log/{idLog}")
    public List<HistoricoMensagem> listarPorLog(@PathParam("idLog") int idLog) {
        try {
            return dao.findByLog(idLog);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @POST
    public Response criar(HistoricoMensagem msg) {
        try {
            if (msg.getDataHora() == null) {
                msg.setDataHora(LocalDateTime.now());
            }
            dao.create(msg);
            return Response.status(Response.Status.CREATED).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
