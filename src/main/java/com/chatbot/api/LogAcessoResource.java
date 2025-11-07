package com.chatbot.api;

import com.chatbot.dao.LogAcessoDAO;
import com.chatbot.model.LogAcesso;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Path("/logs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LogAcessoResource {

    private final LogAcessoDAO dao = new LogAcessoDAO();

    @GET
    public List<LogAcesso> listar() {
        try {
            return dao.readAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @POST
    public Response criar(LogAcesso log) {
        try {
            if (log.getDataHoraInicio() == null) {
                log.setDataHoraInicio(LocalDateTime.now());
            }
            dao.create(log);
            return Response.status(Response.Status.CREATED).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
