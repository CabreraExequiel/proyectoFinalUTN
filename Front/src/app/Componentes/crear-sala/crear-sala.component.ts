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
      tipo: ['football', Validators.required],
      horario: ['', Validators.required],
      lugar: ['', Validators.required],
      descripcion: ['', [Validators.required, Validators.maxLength(200)]],
      limite: [5, Validators.required]
    });
  }

  async onSubmit() {
    if (this.salaForm.invalid) return;

    const payload = {
      nombre_sala: this.salaForm.value.nombre,
      deporte: this.salaForm.value.tipo,
      descripcion: this.salaForm.value.descripcion,
      limite_integrantes: this.salaForm.value.limite,
      ubicacion: this.salaForm.value.lugar
    };

    try {
      const response = await lastValueFrom(
        this.http.post('http://localhost:8080/sala/deporte/crear', payload,{
          headers: {
            'Authorization': `${localStorage.getItem('token')}`
          },
          withCredentials: true
        })
      );
      console.log('Sala creada:', response);
      this.router.navigate(['/home']);
    } catch (err) {
      console.error('Error al crear sala:', err);
      alert('Error al crear la sala. Intenta nuevamente.');
    }
  }
}


