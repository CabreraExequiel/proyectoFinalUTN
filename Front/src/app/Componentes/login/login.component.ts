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

  ngOnInit() {
    const token = localStorage.getItem('token');
    if (token) {
      this.router.navigate(['/home']);
      return;
    }
  }

  onLogin() {
  this.usuarioService.login(this.correo, this.password).subscribe({
    next: (response) => {
      localStorage.setItem('token', response.token);
      localStorage.setItem('nombreUsuario', response.usuario.nombre);
      this.router.navigate(['/home']);
    },
    error: () => {
      this.errorMessage = 'Correo o contraseña incorrectos';
    }
  });
}

  volverLanding() {
  this.router.navigate(['/']);
}
}

