import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { lastValueFrom } from 'rxjs';

interface SportOption {
  value: string;
  label: string;
  icon: string;
}

@Component({
  selector: 'app-crear-sala',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterModule],
  templateUrl: './crear-sala.component.html',
  styleUrl: './crear-sala.component.css'
})
export class CrearSalaComponent {
  private fb = inject(FormBuilder);
  private http = inject(HttpClient);
  private router = inject(Router);

  salaForm: FormGroup;
  
  deportes: SportOption[] = [
    { value: 'football', label: 'Fútbol', icon: 'fas fa-futbol' },
    { value: 'running', label: 'Running', icon: 'fas fa-running' },
    { value: 'basketball', label: 'Básquet', icon: 'fas fa-basketball-ball' },
    { value: 'calistenia', label: 'Calistenia', icon: 'fas fa-dumbbell' },
    { value: 'volleyball', label: 'Vóley', icon: 'fas fa-volleyball-ball' }
  ];

  constructor() {
    this.salaForm = this.fb.group({
  nombre: ['', [Validators.required, Validators.maxLength(50)]],
  tipo: ['', Validators.required],
  horario: ['', Validators.required],
  lugar: ['', Validators.required],
  descripcion: ['', Validators.required],
  limite: [2, Validators.required],
  latitud: [null, [Validators.required, Validators.min(-90), Validators.max(90)]],
  longitud: [null, [Validators.required, Validators.min(-180), Validators.max(180)]]
});
  }

  ngOnInit() {
    const token = localStorage.getItem('token');

    if (!token) {
      this.router.navigate(['/login']);
      return;
    }
  }

  async onSubmit() {
  if (this.salaForm.invalid) return;

  const payloadSalaDeporte = {
    nombre_sala: this.salaForm.value.nombre,
    deporte: this.salaForm.value.tipo,
    descripcion: this.salaForm.value.descripcion,
    limite_integrantes: this.salaForm.value.limite,
    ubicacion: this.salaForm.value.lugar,
    latitud: this.salaForm.value.latitud,     
    longitud: this.salaForm.value.longitud,
    horario_sala: this.salaForm.value.horario   
  };

  try {
    // Crear sala deporte
    const responseSalaDeporte: any = await lastValueFrom(
      this.http.post('http://localhost:8080/sala/deporte/crear', payloadSalaDeporte, {
        headers: { 'Authorization': `${localStorage.getItem('token')}` },
        withCredentials: true
      })
    );
    console.log('Sala deporte creada:', responseSalaDeporte);


    const idSalaCreada = responseSalaDeporte.id || responseSalaDeporte.idSala || responseSalaDeporte.salaReunionId;

    if (idSalaCreada) {
      this.router.navigate(['/sala']);
    } else {
      // fallback en caso que no venga el id
      this.router.navigate(['/home']);
    }
  } catch (err) {
    console.error('Error al crear sala o sala reunión:', err);
    alert('Error al crear sala o sala reunión. Intenta nuevamente.');
  }
}


}


