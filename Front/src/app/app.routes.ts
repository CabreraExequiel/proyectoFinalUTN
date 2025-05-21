import { Routes } from '@angular/router';
import { LandingPageComponent } from './Componentes/landing-page/landing-page.component';
import { HomeComponent } from './Componentes/home/home.component';
import { RunningComponent } from './Componentes/running/running.component';
import { FootballComponent } from './Componentes/football/football.component';
import { BasketballComponent } from './Componentes/basketball/basketball.component';

export const routes: Routes = [
  { path: '', component: LandingPageComponent }, // landing en raíz
  {
    path: 'home',
    component: HomeComponent,
    children: [
      { path: 'football', component: FootballComponent },
      { path: 'run', component: RunningComponent },
      { path: 'basket', component: BasketballComponent },
      { path: '', redirectTo: 'football', pathMatch: 'full' }
    ]
  },
  { path: '**', redirectTo: '' } // cualquier ruta desconocida redirige a landing (ruta raíz)
];


