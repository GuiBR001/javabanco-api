package com.chatbot.api;

import com.chatbot.dao.CategoriaDAO;
import com.chatbot.model.Categoria;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.sql.SQLException;
import java.util.List;

@Path("/categorias")
public class CategoriaResource {

    private final CategoriaDAO dao = new CategoriaDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Categoria> listar() throws SQLException {
        return dao.readAll();
    }
}
