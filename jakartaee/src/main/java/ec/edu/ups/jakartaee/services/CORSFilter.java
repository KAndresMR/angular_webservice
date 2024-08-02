package ec.edu.ups.jakartaee.services;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebFilter;

/**
 * Filtro CORS (Cross-Origin Resource Sharing) para habilitar el acceso a recursos
 * desde dominios externos.
 * 
 * Anotaciones:
 * - @WebFilter: Indica que esta clase es un filtro que se aplicará a todas las solicitudes.
 */
@WebFilter("/*")
public class CORSFilter implements Filter {

    /**
     * Método de inicialización del filtro. No se usa en este caso.
     * 
     * @param filterConfig Configuración del filtro.
     * @throws ServletException Si ocurre un error durante la inicialización.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Método para agregar los encabezados CORS a la respuesta HTTP.
     * 
     * @param servletRequest La solicitud del cliente.
     * @param servletResponse La respuesta del servidor.
     * @param filterChain La cadena de filtros para pasar la solicitud a la siguiente etapa.
     * @throws IOException Si ocurre un error durante la escritura de los encabezados.
     * @throws ServletException Si ocurre un error en el filtro.
     */
    @Override
    public void doFilter(jakarta.servlet.ServletRequest servletRequest, jakarta.servlet.ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // Agrega encabezados CORS a la respuesta
        response.setHeader("Access-Control-Allow-Origin", "*"); // Permite el acceso desde cualquier origen
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD"); // Métodos permitidos
        response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization"); // Encabezados permitidos
        response.setHeader("Access-Control-Allow-Credentials", "true"); // Permite credenciales en solicitudes CORS
        response.setHeader("Access-Control-Max-Age", "3600"); // Tiempo en segundos para almacenar en caché la respuesta CORS

        // Pasa la solicitud y respuesta a la siguiente etapa del filtro
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Método para limpiar recursos al destruir el filtro. No se usa en este caso.
     */
    @Override
    public void destroy() {
    }
}