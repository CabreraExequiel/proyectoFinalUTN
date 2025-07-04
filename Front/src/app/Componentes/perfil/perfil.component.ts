import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';

interface UsuarioInfo {
  nombre: string;
  apellido: string;
  correo: string;
  descripcion_usuario: string;
  imagen_perfil: string;
  imagen_portada: string;
}

interface SalaDeporte {
  id_sala: number;
  nombre_sala: string;
  descripcion: string;
  deporte: string;
}

@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './perfil.component.html',
  styleUrl: './perfil.component.css'
})
export class PerfilComponent implements OnInit {
  usuario: UsuarioInfo | null = null;
  imagenPerfilCargada: string = 'https://st3.depositphotos.com/6672868/13701/v/450/depositphotos_137014128-stock-illustration-user-profile-icon.jpg';
  imagenPortadaCargada: string = 'https://proplantillas.com/wp-content/plugins/elementor/assets/images/placeholder.png';

  editarDescripcion = false;
  nuevaDescripcion = '';

  salas: SalaDeporte[] = [];

  constructor(
    private http: HttpClient,
    private sanitizer: DomSanitizer,
    private router: Router
  ) {}

  ngOnInit() {
    const token = localStorage.getItem('token');
    if (!token) {
      this.router.navigate(['/login']);
      return;
    }

    this.cargarUsuario();
    this.cargarImagenPerfil();
    this.cargarImagenPortada();
    this.cargarSalas();
  }

  cargarUsuario() {
    const token = localStorage.getItem('token');
    this.http.get<UsuarioInfo>('http://localhost:8080/custom/usuario/info', {
      headers: {
        'Accept': 'application/json',
        'Authorization': token ?? ''
      },
      withCredentials: true
    }).subscribe({
      next: data => this.usuario = data,
      error: err => console.error('Error al cargar info de usuario', err)
    });
  }

  cargarSalas() {
    const token = localStorage.getItem('token');
    this.http.get<SalaDeporte[]>('http://localhost:8080/sala/perfil/mostrar', {
      headers: {
        'Authorization': token ?? '',
        'Accept': 'application/json'
      },
      withCredentials: true
    }).subscribe({
      next: data => this.salas = data,
      error: err => console.error('Error al cargar salas desde /sala/perfil/mostrar', err)
    });
  }

  irASala(id_sala: number) {
    this.router.navigate([`/sala/${id_sala}`]);
  }

  cargarImagenPerfil() {
    const token = localStorage.getItem('token');
    if (!token) return;

    this.http.get('http://localhost:8080/custom/usuario/foto_perfil', {
      responseType: 'text',
      withCredentials: true,
      headers: {
        'Authorization': token
      }
    }).subscribe({
      next: (ruta: string) => {
        const rutaLimpia = this.limpiarYConvertirRuta(ruta);
        this.imagenPerfilCargada = rutaLimpia ?? this.imagenPerfilCargada;
      },
      error: () => {
        this.imagenPerfilCargada = 'https://st3.depositphotos.com/6672868/13701/v/450/depositphotos_137014128-stock-illustration-user-profile-icon.jpg';
      }
    });
  }

  cargarImagenPortada() {
    const token = localStorage.getItem('token');
    if (!token) return;

    this.http.get('http://localhost:8080/custom/usuario/foto_portada', {
      responseType: 'text',
      withCredentials: true,
      headers: {
        'Authorization': token
      }
    }).subscribe({
      next: (ruta: string) => {
        const rutaLimpia = this.limpiarYConvertirRuta(ruta);
        this.imagenPortadaCargada = rutaLimpia ?? this.imagenPortadaCargada;
      },
      error: () => {
        this.imagenPortadaCargada = 'https://proplantillas.com/wp-content/plugins/elementor/assets/images/placeholder.png';
      }
    });
  }

  private limpiarYConvertirRuta(ruta: string | null): string | null {
    if (!ruta || typeof ruta !== 'string') return null;
    const limpia = ruta.trim().replace(/^"|"$/g, '').replace(/\\/g, '/');
    const nombreArchivo = limpia.split('/').pop();
    if (!nombreArchivo || nombreArchivo.trim() === '') return null;
    return `http://localhost:8080/uploads/${nombreArchivo}`;
  }

  guardarDescripcion() {
    if (!this.nuevaDescripcion.trim()) {
      alert('La descripción no puede estar vacía.');
      return;
    }

    const token = localStorage.getItem('token');
    if (!token) {
      alert('No autorizado. Por favor inicia sesión.');
      return;
    }

    const payload = {
      descripcion_usuario: this.nuevaDescripcion.trim()
    };

    this.http.put('http://localhost:8080/custom/usuario/editar', payload, {
      headers: {
        'Content-Type': 'application/json',
        'Authorization': token
      },
      withCredentials: true
    }).subscribe({
      next: () => {
        if (this.usuario) {
          this.usuario.descripcion_usuario = this.nuevaDescripcion.trim();
        }
        this.editarDescripcion = false;
        alert('Descripción actualizada correctamente');
      },
      error: err => {
        console.error('Error al actualizar la descripción', err);
        alert('Error al actualizar la descripción');
      }
    });
  }

  cancelarEdicion() {
    this.editarDescripcion = false;
    this.nuevaDescripcion = this.usuario?.descripcion_usuario ?? '';
  }

  activarEdicion() {
    this.editarDescripcion = true;
    this.nuevaDescripcion = this.usuario?.descripcion_usuario ?? '';
  }

  subirImagen(event: Event, tipo: 'perfil' | 'portada') {
    const token = localStorage.getItem('token');
    if (!token) {
      alert('No autorizado. Por favor inicia sesión.');
      return;
    }

    const input = event.target as HTMLInputElement;
    if (!input.files || input.files.length === 0) {
      alert('No se seleccionó ningún archivo.');
      return;
    }

    const archivo = input.files[0];
    const formData = new FormData();
    formData.append(tipo === 'perfil' ? 'imagen_perfil' : 'imagen_portada', archivo);

    this.http.put('http://localhost:8080/custom/usuario/editar', formData, {
      headers: { 'Authorization': token },
      withCredentials: true
    }).subscribe({
      next: () => {
        alert(`${tipo === 'perfil' ? 'Foto de perfil' : 'Portada'} actualizada correctamente.`);
        tipo === 'perfil' ? this.cargarImagenPerfil() : this.cargarImagenPortada();
      },
      error: err => {
        console.error(`Error al actualizar la imagen de ${tipo}`, err);
        alert('Error al subir la imagen');
      }
    });
  }
}
