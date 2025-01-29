package com.jakarta.services;

import com.jakarta.dao.UserDAO;
import com.jakarta.models.User;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Stateless
@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON) // Para manejar JSON en los métodos POST y PUT
public class UserService {

    @Inject
    private UserDAO userDAO;

    @POST
    public Response createUser(User user) {
        userDAO.save(user);
        return Response.status(Response.Status.CREATED).entity("Usuario creado correctamente").build();
        /*
            {
            "cedula": "987654321",
            "nombre": "María López",
            "consumo": 300.45,
            "deudaPendiente": 75.80
            }
         */
    }

    /*@GET
    @Path("/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("cedula") String cedula) {
        try {
            User user = userDAO.findByCedula(cedula);
            if (user == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("{\"error\": \"Usuario no encontrado\"}").build();
            }
            return Response.ok(user).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("{\"error\": \"Error interno en el servidor: " + e.getMessage() + "\"}").build();
        }
    }*/

    @GET
	@Path("/{cedula}")
	public Response getUsuarioData(@PathParam("cedula") String cedula) {
		// Simular la obtención de datos desde una base de datos
		User data = obtenerDatosUsuario(cedula);

		if (data == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
		}

		return Response.ok(data).build();
	}

    // Método para simular la obtención de datos
    private User obtenerDatosUsuario(String cedula) {
        // Simulación: Supongamos que tenemos un usuario con cédula "123456789"
        if ("123456789".equals(cedula)) {
            return new User(cedula, "Juan Pérez", 450.75, 120.50);
        } else {
            return null; // Si no se encuentra, retorna null
        }
    }


    @PUT
    @Path("/{cedula}")
    public Response updateUser(@PathParam("cedula") String cedula, User user) {
        User existingUser = userDAO.findByCedula(cedula);
        if (existingUser == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
        }
        userDAO.update(user);
        return Response.ok("Usuario actualizado correctamente").build();
    }

    @DELETE
    @Path("/{cedula}")
    public Response deleteUser(@PathParam("cedula") String cedula) {
        userDAO.delete(cedula);
        return Response.ok("Usuario eliminado correctamente").build();
    }
}
