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

export const routes: Routes = [
  { path: '', component: LandingPageComponent }, 
   { path: 'login', component: LoginComponent }, 
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
      { path: '', redirectTo: 'inicio', pathMatch: 'full' }
    ]
  },
  { path: '**', redirectTo: 'inicio' } 
];


