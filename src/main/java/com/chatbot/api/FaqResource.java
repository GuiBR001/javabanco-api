package com.chatbot.api;

import com.chatbot.dao.FaqDAO;
import com.chatbot.model.Faq;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Path("/faq")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FaqResource {

    private final FaqDAO dao = new FaqDAO();

    @GET
    public List<Faq> listar() {
        try {
            return dao.readAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @GET
    @Path("/categoria/{idCategoria}")
    public List<Faq> listarPorCategoria(@PathParam("idCategoria") int idCategoria) {
        try {
            return dao.findByCategoria(idCategoria);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @POST
    public Response criar(Faq faq) {
        try {
            dao.create(faq);
            return Response.status(Response.Status.CREATED).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, Faq faq) {
        try {
            faq.setIdFaq(id);
            dao.update(faq);
            return Response.ok().build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        try {
            dao.delete(id);
            return Response.noContent().build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
