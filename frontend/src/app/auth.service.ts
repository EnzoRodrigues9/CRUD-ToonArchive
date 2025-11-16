import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class AuthService {

  isLogado(): boolean {
    return !!localStorage.getItem('token');
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('apelido');
  }
}
