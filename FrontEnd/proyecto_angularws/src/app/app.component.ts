import { Component, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService } from './services/user.service';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';

/**
 * Componente principal de la aplicación.
 * 
 * - `selector`: Define el nombre del componente que se usa en el HTML.
 * - `imports`: Importa módulos necesarios para el componente.
 * - `templateUrl`: Archivo HTML asociado al componente.
 * - `styleUrl`: Archivo de estilos asociado al componente.
 */
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  cedula: string = ''; // Cédula ingresada por el usuario
  usuarioData: any = null; // Datos del usuario obtenidos del servicio
  error: string = ''; // Mensaje de error en caso de fallo

  /**
   * Constructor del componente.
   * 
   * @param userservice Servicio para obtener datos del usuario
   */
  constructor(private userservice: UserService) { }

  /**
   * Método para consultar los datos del usuario.
   * Llama al servicio para obtener los datos basados en la cédula ingresada.
   */
  consultar() {
    this.userservice.getUsuarioData(this.cedula).subscribe(
      data => {
        this.usuarioData = data; // Asigna los datos del usuario
        this.error = ''; // Limpia el mensaje de error
      },
      error => {
        this.error = 'Usuario no encontrado'; // Establece un mensaje de error
        this.usuarioData = null; // Limpia los datos del usuario
      }
    );
  }
}