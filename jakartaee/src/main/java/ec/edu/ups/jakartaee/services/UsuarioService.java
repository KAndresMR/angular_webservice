package ec.edu.ups.jakartaee.services;

import ec.edu.ups.jakartaee.model.Usuario;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioService {

	@GET
	@Path("/{cedula}")
	public Response getUsuarioData(@PathParam("cedula") String cedula) {
		// Simular la obtención de datos desde una base de datos
		Usuario data = obtenerDatosUsuario(cedula);

		if (data == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
		}

		return Response.ok(data).build();
	}
	
	// Método para simular la obtención de datos
    private Usuario obtenerDatosUsuario(String cedula) {
        // Simulación: Supongamos que tenemos un usuario con cédula "123456789"
        if ("123456789".equals(cedula)) {
            return new Usuario(cedula, "Juan Pérez", 450.75, 120.50);
        } else {
            return null; // Si no se encuentra, retorna null
        }
    }
}
