import { Component, AfterViewInit, HostListener } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { UsuarioService } from '../../Servicios/service.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']  // corregí esto
})
export class HomeComponent implements AfterViewInit {

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

  ngAfterViewInit(): void {
    this.controlarSidebar(); // Al iniciar
  }

  @HostListener('window:resize', [])
  onResize() {
    this.controlarSidebar(); // Al cambiar el tamaño
  }

  controlarSidebar() {
    const sidebar = document.getElementById('sidebarMenu');
    const boton = document.getElementById('sidebarToggleBtn');

    if (!sidebar || !boton) return;

    const ancho = window.innerWidth;
    const visible = sidebar.classList.contains('show');

    if (ancho < 1570 && visible) {
      boton.click(); // Colapsar si es chico y está visible
    }

    if (ancho >= 1570 && !visible) {
      boton.click(); // Expandir si es grande y está oculto
    }
  }
}
