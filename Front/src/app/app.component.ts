  import { Component } from '@angular/core';
  import { RouterOutlet } from '@angular/router';
  import { HomeComponent } from "./Componentes/home/home.component";
  import { UsuarioComponent } from "./Componentes/usuario/usuario.component";
import { LandingPageComponent } from "./Componentes/landing-page/landing-page.component";

  @Component({
    selector: 'app-root',
    standalone: true,
    imports: [HomeComponent, LandingPageComponent],
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']

  })
  export class AppComponent {
    title = 'proyectoFinalUTN';
  }
