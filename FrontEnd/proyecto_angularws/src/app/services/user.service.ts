import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

/**
 * Servicio para interactuar con la API de usuarios.
 * 
 * - `HttpClient`: Utilizado para realizar solicitudes HTTP.
 */
@Injectable({
  providedIn: 'root' // Indica que el servicio está disponible en toda la aplicación
})
export class UserService {

  // URL base de la API de usuarios
  apiUrl: string = "http://localhost:8080/jakartaee-0.0.1-SNAPSHOT/api/usuarios"; 

  /**
   * Constructor del servicio que inyecta el HttpClient.
   * 
   * @param http El HttpClient para realizar solicitudes HTTP.
   */
  constructor(private http: HttpClient) { }

  /**
   * Obtiene los datos de usuario a partir de la cédula.
   * 
   * @param cedula La cédula del usuario que se desea consultar.
   * @return Un Observable que emite los datos del usuario.
   */
  getUsuarioData(cedula: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/${cedula}`); // Realiza una solicitud GET a la API
  }
}