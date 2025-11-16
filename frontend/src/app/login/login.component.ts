import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';   // <-- IMPORTAR AQUI

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],   // <-- ADICIONE CommonModule
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  apelido = '';
  senha = '';
  mensagemErro = '';

  constructor(private http: HttpClient, private router: Router) {}

  fazerLogin() {
    this.http.post('http://localhost:8080/login', {
      apelido: this.apelido,
      senha: this.senha
    }).subscribe({
      next: (resp: any) => {
        localStorage.setItem('token', resp.token);
        localStorage.setItem('apelido', resp.apelido);
        this.router.navigate(['/']);
      },
      error: () => {
        this.mensagemErro = 'Usuário ou senha inválidos.';
      }
    });
  }
}
