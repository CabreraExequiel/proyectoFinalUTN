import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-landing-page',
  standalone: true,
   imports: [CommonModule, FormsModule],
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent {
  user = {
    nombre: '',
    apellido: '',
    correo: '',
    password: ''
  };

  constructor(private http: HttpClient) {}

  onRegister() {
    this.http.post('http://localhost:8080/api/auth/register', this.user)
      .subscribe({
        next: res => alert('Registro exitoso'),
        error: err => alert('Error al registrar: ' + err.error.message)
      });
  }
}
