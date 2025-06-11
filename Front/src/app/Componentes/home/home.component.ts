import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { UsuarioService } from '../../Servicios/service.service';


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ CommonModule, RouterModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
   constructor(private usuarioService: UsuarioService, private router: Router) {}

  ngOnInit() {
    const token = localStorage.getItem('token');
    if (!token) {
      this.router.navigate(['/login']);
    }
  }
  logout() {
    this.usuarioService.logout(); // Elimina el token
    this.router.navigate(['/login']); // Redirige al login
  }

}
