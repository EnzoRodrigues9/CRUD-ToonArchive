import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/login';

  constructor(private http: HttpClient) {}

  login(usuario: string, senha: string) {
    return this.http.post<{ token: string }>(this.apiUrl, { usuario, senha })
      .pipe(
        tap(res => {
          localStorage.setItem('token', res.token);
        })
      );
  }

  logout() {
    localStorage.removeItem('token');
  }

  getToken() {
    return localStorage.getItem('token');
  }

  isLogged() {
    return this.getToken() !== null;
  }
}
