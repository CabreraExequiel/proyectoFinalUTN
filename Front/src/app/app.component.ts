  import { Component } from '@angular/core';
  import { RouterOutlet } from '@angular/router';
  import { HomeComponent } from "./Componentes/home/home.component";
  import { UsuarioComponent } from "./Componentes/usuario/usuario.component";

  @Component({
    selector: 'app-root',
    standalone: true,
    imports: [HomeComponent],
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']

  })
  export class AppComponent {
    title = 'proyectoFinalUTN';
  }
