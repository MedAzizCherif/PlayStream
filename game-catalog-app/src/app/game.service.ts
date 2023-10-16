import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  private apiUrl = 'https://api.rawg.io/api/games?key=b4407fbff49c49858889aee5d55e37c7';

  constructor(private http: HttpClient) {}

  getGames(): Observable<any> {
    return this.http.get(this.apiUrl);
  }
  getGamesFromBackend(): Observable<any[]> {
    return this.http.get<any[]>('http://localhost:8091/api/games');
  }
  
}
