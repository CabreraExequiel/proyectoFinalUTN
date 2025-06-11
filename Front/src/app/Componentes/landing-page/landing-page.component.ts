import { Component } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UsuarioService } from '../../Servicios/service.service';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-landing-page',
  standalone: true,
   imports: [CommonModule, FormsModule, HttpClientModule,RouterModule],
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

  nuevoUsuario = { nombre: '', apellido: '', correo: '', password: '' };

  usuarios: { nombre: string; apellido: string; correo: string; password: string }[] = [];

  constructor(private usuarioService: UsuarioService, private router: Router, private httpClient: HttpClient) {}
  
  
  ngOnInit() {
    const token = localStorage.getItem('token');
    if (token) {
      this.router.navigate(['/home']);
      return;
    }
    this.obtenerUsuarios();
  }

  obtenerUsuarios() {
    this.usuarioService.verUsuarios().subscribe(data => {
      this.usuarios = data;
    });
  }

  crear() {
    if (this.nuevoUsuario.nombre.trim() !== '') {
      this.usuarioService.crearUsuario(this.nuevoUsuario).subscribe(() => {
        this.obtenerUsuarios();
        this.nuevoUsuario.nombre = '';
        this.nuevoUsuario.apellido = '';
        this.nuevoUsuario.correo = '';
        this.nuevoUsuario.password = '';
      });
    }
    this.router.navigate(['/login'])
  }
}
