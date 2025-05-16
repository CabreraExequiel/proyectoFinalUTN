import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private apiUrlVer = 'http://localhost:8080/ver/usuario';
  private apiUrlNuevo = 'http://localhost:8080/nuevo/usuario';

  constructor(private http: HttpClient) {}

  verUsuarios(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrlVer);
  }

  crearUsuario(usuario: any): Observable<any> {
    return this.http.post<any>(this.apiUrlNuevo, usuario);
  }
}
