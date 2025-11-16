import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';  // 🔥 Importar RouterModule

import { CadastroService } from '../../services/cadastro.service';


@Component({
  selector: 'app-cadastro',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterModule], // 🔥 ADICIONE AQUI
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent {

  usuario = {
    apelido: '',
    email: '',
    senha: ''
  };

  constructor(
    private cadastroService: CadastroService,
    private router: Router
  ) {}

  cadastrar() {
    console.log('Enviando cadastro:', this.usuario);

    this.cadastroService.cadastrar(this.usuario).subscribe({
      next: () => {
        alert('Cadastro realizado com sucesso!');
        this.router.navigate(['/login']);
      },
      error: (err) => {
        console.error('Erro ao cadastrar:', err);
        alert('Erro ao cadastrar usuário.');
      }
    });
  }
}
