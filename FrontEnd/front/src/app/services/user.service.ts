import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  // URL base de la API de usuarios
  apiUrl: string = "http://localhost:8080/jakarta-1.0-SNAPSHOT/api/usuarios"; 

  constructor(private http: HttpClient) { }

  getUser(cedula: string): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${cedula}`).pipe(
      catchError((error)=>{
        console.error('Error al obtener los datos del usuario:', error);
        return throwError(() => new Error('Error al obtener los datos del usuario'));
      })
    ); // Realiza una solicitud GET a la API
  }

  get(cedula: string): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${cedula}`);
  }

  // Crear un nuevo usuario
  createUser(user: User): Observable<string> {
    return this.http.post<string>(this.apiUrl, user);
  }

  updateUser(cedula: string, user: User): Observable<string> {
    return this.http.put<string>(`${this.apiUrl}/${cedula}`, user);
  }

  deleteUser(cedula: string): Observable<string> {
    return this.http.delete<string>(`${this.apiUrl}/${cedula}`);
  }
  
}
