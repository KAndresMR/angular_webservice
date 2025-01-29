package com.jakarta.services;

import java.util.HashMap;
import java.util.Map;

import com.jakarta.models.User;

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

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON) // Para manejar JSON en los métodos POST y PUT
public class UserService {

    private static Map<String, User> usuariosDB = new HashMap<>();

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

    @POST
    public Response createUser(User user) {
        if (usuariosDB.containsKey(user.getCedula())) {
            return Response.status(Response.Status.CONFLICT).entity("El usuario ya existe").build();
        }
        usuariosDB.put(user.getCedula(), user);
        return Response.status(Response.Status.CREATED).entity(user).build();
        /*
            {
            "cedula": "987654321",
            "nombre": "María López",
            "consumo": 300.45,
            "deudaPendiente": 75.80
            }
         */
    }

    @PUT
    @Path("/{cedula}")
    public Response updateUser(@PathParam("cedula") String cedula, User updatedUser) {
        if (!usuariosDB.containsKey(cedula)) {
            return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
        }
        usuariosDB.put(cedula, updatedUser);
        return Response.ok(updatedUser).build();
    }
	
    @DELETE
    @Path("/{cedula}")
    public Response deleteUser(@PathParam("cedula") String cedula) {
        User removedUser = usuariosDB.remove(cedula);
        if (removedUser == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
        }
        return Response.ok("Usuario eliminado exitosamente").build();
    }
}
