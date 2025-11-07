package com.chatbot.api;

import com.chatbot.dao.AvaliacaoDAO;
import com.chatbot.model.Avaliacao;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Path("/avaliacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvaliacaoResource {

    private final AvaliacaoDAO dao = new AvaliacaoDAO();

    @GET
    public List<Avaliacao> listar() {
        try {
            return dao.readAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @POST
    public Response criar(Avaliacao avaliacao) {
        try {
            dao.create(avaliacao);
            return Response.status(Response.Status.CREATED).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/media")
    @Produces(MediaType.TEXT_PLAIN)
    public String media() {
        try {
            double m = dao.averageNota();
            return String.valueOf(m);
        } catch (SQLException e) {
            e.printStackTrace();
            return "0";
        }
    }
}
