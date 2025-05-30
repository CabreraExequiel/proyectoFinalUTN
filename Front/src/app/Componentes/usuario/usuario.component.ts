import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UsuarioService } from '../../Servicios/service.service';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-usuario',
  standalone: true,
  imports: [CommonModule, FormsModule,HttpClientModule],
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})

export class UsuarioComponent implements OnInit {
  usuarios: any[] = [];
  nuevoUsuario = { nombre: '' , apellido: ''};

  constructor(private usuarioService: UsuarioService) {}

  ngOnInit() {
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
      });
    }
  }
}
