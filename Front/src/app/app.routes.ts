import { Routes } from '@angular/router';
import { LandingPageComponent } from './Componentes/landing-page/landing-page.component';
import { HomeComponent } from './Componentes/home/home.component';
import { RunningComponent } from './Componentes/running/running.component';
import { FootballComponent } from './Componentes/football/football.component';
import { BasketballComponent } from './Componentes/basketball/basketball.component';
import { InicioComponent } from './Componentes/inicio/inicio.component';
import { LoginComponent } from './Componentes/login/login.component';
import { CalisteniaComponent } from './Componentes/calistenia/calistenia.component';
import { VoleyComponent } from './Componentes/voley/voley.component';
import { PerfilComponent } from './Componentes/perfil/perfil.component';
import { SalasComponent } from './componentes-reutilizables/salas/salas.component';
import { CrearSalaComponent } from './Componentes/crear-sala/crear-sala.component';


export const routes: Routes = [
  { path: '', component: LandingPageComponent }, 
   { path: 'login', component: LoginComponent }, 
   { path: 'perfil', component: PerfilComponent }, 
   { path: 'salas/:id', component: SalasComponent },
   {
  path: 'sala/:id',
  loadComponent: () => import('./componentes-reutilizables/salas/salas.component').then(m => m.SalasComponent)
},




   
   
  {
    path: 'home',
    component: HomeComponent,
    children: [
      { path: 'inicio', component: InicioComponent },
      { path: 'football', component: FootballComponent },
      { path: 'run', component: RunningComponent },
      { path: 'basket', component: BasketballComponent },
      { path: 'calistenia', component: CalisteniaComponent},
      { path: 'voleyball', component: VoleyComponent},
      { path: 'crear_sala', component: CrearSalaComponent },
      { path: '', redirectTo: 'inicio', pathMatch: 'full' }
    ]
  },
  { path: '**', redirectTo: 'inicio' } 
];


