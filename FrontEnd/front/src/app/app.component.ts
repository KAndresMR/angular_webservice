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

  usuario: User = { cedula: '', nombre: '', consumo: 0, deudaPendiente: 0 };
  cedulaBuscar: string = '';
  cedula: string = ''; // Cédula ingresada por el usuario
  usuarioData: any = null; // Datos del usuario obtenidos del servicio
  error: string | null = ''; // Mensaje de error en caso de fallo

  constructor(private userService: UserService) { }

  crearUsuario() {
    this.userService.createUser(this.usuario).subscribe();
  }

  actualizarUsuario() {
    this.userService.updateUser(this.usuario.cedula, this.usuario).subscribe();
  }

  eliminarUsuario() {
    this.userService.deleteUser(this.usuario.cedula).subscribe();
  }

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

  get() { 
    this.userService.get(this.cedula).subscribe(data => {
      this.usuarioData = data;
    });
  }




  
}
