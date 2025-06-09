import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Post {
  id?: number;
  autor: string;
  contenido: string;
  fechaPublicacion?: string;
}

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private apiUrlVer = 'http://localhost:8080/ver/usuario';
  private apiUrlNuevo = 'http://localhost:8080/user/register';
  private apiUrlLogin = 'http://localhost:8080/user/login';
  private apiUrlPost = 'http://localhost:8080/api/foro';

  constructor(private http: HttpClient) {}

  // =====================
  // POSTS
  // =====================

  // Obtener todos los posts
  getPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(this.apiUrlPost);
  }

  // Crear un nuevo post
  crearPost(post: Post): Observable<Post> {
    return this.http.post<Post>(this.apiUrlPost, post);
  }

  // =====================
  // AUTENTICACIÓN
  // =====================

  login(correo: string, password: string): Observable<{ token: string, usuario: any }> {
    const body = { correo, password };
    return this.http.post<{ token: string, usuario: any }>(
      this.apiUrlLogin,
      body,
      { withCredentials: true }  // Opcional: si usás cookies en el backend
    );
  }

  logout(): void {
    localStorage.removeItem('token');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  // =====================
  // USUARIOS
  // =====================

  // Ver todos los usuarios
  verUsuarios(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrlVer, {
      withCredentials: true // Solo si el backend usa sesiones/cookies
    });
  }

  // Registrar nuevo usuario
  crearUsuario(usuario: any): Observable<any> {
    return this.http.post<any>(this.apiUrlNuevo, usuario);
  }
}
