import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private apiUrlVer = 'http://localhost:8080/ver/usuario';
  private apiUrlNuevo = 'http://localhost:8080/user/register';
  private apiUrlLogin = 'http://localhost:8080/user/login';

  

  constructor(private http: HttpClient) {}


/*
  login(correo: string, password: string): Observable<string> {
  const body = { correo, password };
  return this.http.post(this.apiUrlLogin, body, { 
    responseType: 'text',
    withCredentials: true  // 👈 Aquí añadimos el soporte para credenciales
  });
}
*/
  login(correo: string, password: string): Observable<{ token: string, usuario: any }> {
  const body = { correo, password };
  return this.http.post<{ token: string, usuario: any }>(this.apiUrlLogin, body,{ 
    withCredentials: true  // 👈 Aquí añadimos el soporte para credenciales
  });
}
  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  logout() {
    localStorage.removeItem('token');
  }
  

  verUsuarios(): Observable<any[]> {
  return this.http.get<any[]>(this.apiUrlVer, { 
    withCredentials: true 
  });
}

  crearUsuario(usuario: any): Observable<any> {
    return this.http.post<any>(this.apiUrlNuevo, usuario);
  }


  
}

