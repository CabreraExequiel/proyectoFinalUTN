import { Component, OnInit } from '@angular/core';
import { UsuarioService, Post } from '../../Servicios/service.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-foro',
  standalone: true,
  imports:[FormsModule, CommonModule],
  templateUrl: './foro.component.html',
  styleUrls: ['./foro.component.css']
})
export class ForoComponent implements OnInit {
  posts: Post[] = [];
  nuevoPost: Post = {
    autor: '',
    contenido: ''
  };

  constructor(private usuarioService: UsuarioService) {}

  ngOnInit(): void {
    this.cargarPosts();
  }

  cargarPosts(): void {
    this.usuarioService.getPosts().subscribe({
      next: (data) => this.posts = data,
      error: (err) => console.error('Error al cargar posts', err)
    });
  }

  crearPost(): void {
    if (!this.nuevoPost.autor || !this.nuevoPost.contenido) return;

    this.usuarioService.crearPost(this.nuevoPost).subscribe({
      next: (post) => {
        this.posts.unshift(post);  // Agregar al inicio de la lista
        this.nuevoPost = { autor: '', contenido: '' };  // Limpiar el formulario
      },
      error: (err) => console.error('Error al crear post', err)
    });
  }
}
