import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';

class Cadastro {
  public id?: string;
  public usuario: string = "";
  public senha: string = "";
  public email: string = "";
}

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule, CommonModule, HttpClientModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class AppComponent {
  protected readonly title = signal('cadastro');
  public cadastro: Cadastro = new Cadastro();
  public cadastros: Cadastro[] = [];
  public editando: boolean = false;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.listar();
  }

  listar() {
    this.http.get<Cadastro[]>('http://localhost:8080/usuario').subscribe({
      next: (dados) => this.cadastros = dados,
      error: (err) => console.error("Erro ao listar:", err)
    });
  }

  salvar() {
    if (this.editando && this.cadastro.id) {
      // Atualizar usuário existente
      this.http.put(`http://localhost:8080/usuario/${this.cadastro.id}`, this.cadastro).subscribe({
        next: () => {
          console.log("Usuário atualizado com sucesso!");
          this.listar();
          this.cancelarEdicao();
        },
        error: (err) => console.error("Erro ao atualizar:", err)
      });
    } else {
      // Criar novo usuário
      this.http.post('http://localhost:8080/usuario', this.cadastro).subscribe({
        next: () => {
          console.log("Usuário salvo com sucesso!");
          this.listar();
          this.cadastro = new Cadastro();
        },
        error: (err) => console.error("Erro ao salvar:", err)
      });
    }
  }

  editar(c: Cadastro) {
    this.cadastro = { ...c }; // Copia o objeto para o formulário
    this.editando = true;
  }

  cancelarEdicao() {
    this.editando = false;
    this.cadastro = new Cadastro();
  }

  excluir(id?: string) {
    if (!id) return;
    if (confirm("Tem certeza que deseja excluir este usuário?")) {
      this.http.delete(`http://localhost:8080/usuario/${id}`).subscribe({
        next: () => {
          console.log("Usuário excluído com sucesso!");
          this.listar();
        },
        error: (err) => console.error("Erro ao excluir:", err)
      });
    }
  }
}
