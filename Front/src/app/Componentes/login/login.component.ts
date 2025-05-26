import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { UsuarioService } from '../../Servicios/service.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
correo: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private usuarioService: UsuarioService, private router: Router) {}

  onLogin() {
    this.usuarioService.login(this.correo, this.password).subscribe({
      next: (token) => {
        localStorage.setItem('token', token); // Guardamos el JWT
        this.router.navigate(['/home']); // Redireccionamos (ajusta la ruta)
      },
      error: (err) => {
        this.errorMessage = 'Correo o contraseña incorrectos';
      },
    });
  }
  volverLanding() {
  this.router.navigate(['/']);
}
}

