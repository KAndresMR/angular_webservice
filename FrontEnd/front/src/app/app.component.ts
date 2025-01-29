import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { UserService } from './services/user.service';
import { User } from './models/user.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'front';

  cedula: string = ''; // Cédula ingresada por el usuario
  usuarioData: any = null; // Datos del usuario obtenidos del servicio
  error: string | null = ''; // Mensaje de error en caso de fallo

  constructor(private userService: UserService) { }

  consultar() {
    if (!this.cedula || this.cedula.trim() === '') {
      this.error = 'Debe ingresar una cédula válida.';
      this.usuarioData = null;
      return;
    }
    this.userService.getUser(this.cedula).subscribe({
      next: (data) => {
        this.usuarioData = data;
        this.error = null;
      },
      error: (err) => {
        console.error(err);
        this.error = 'No se pudo obtener la información del usuario.';
        this.usuarioData = null;
      },
    });
  }

  crearUsuario() {
    const nuevoUsuario: User = {
      cedula: this.cedula,
      nombre: "Usuario Prueba",
      consumo: 250.5,
      deudaPendiente: 90.0
    };

    this.userService.createUser(nuevoUsuario).subscribe(
      (data) => alert("Usuario creado exitosamente"),
      (err) => alert("Error al crear usuario: " + err.message)
    );
  }

  actualizarUsuario() {
    if (!this.usuarioData) return;

    this.usuarioData.nombre = "Nombre actualizado";
    this.userService.updateUser(this.cedula, this.usuarioData).subscribe(
      (data) => alert("Usuario actualizado"),
      (err) => alert("Error al actualizar usuario: " + err.message)
    );
  }

  eliminarUsuario() {
    this.userService.deleteUser(this.cedula).subscribe(
      (msg) => alert("Usuario eliminado"),
      (err) => alert("Error al eliminar usuario: " + err.message)
    );
  }

  
}
