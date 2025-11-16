import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface UsuarioCadastro {
  apelido: string;
  email: string;
  senha: string;
}

@Injectable({
  providedIn: 'root'
})
export class CadastroService {

  private apiUrl = 'http://localhost:8080/usuarios';

  constructor(private http: HttpClient) {}

  cadastrar(usuario: UsuarioCadastro): Observable<any> {
    return this.http.post(this.apiUrl, usuario);
  }
}
