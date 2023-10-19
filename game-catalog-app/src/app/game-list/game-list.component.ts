import { Component, OnInit } from '@angular/core';
import { GameService } from '../game.service';
import { Router } from '@angular/router';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';


@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.css']
})
export class GameListComponent implements OnInit {
  games: any[] = [];

  constructor(private gameService: GameService, private router: Router, private sanitizer: DomSanitizer) {}
 

  ngOnInit(): void {
    this.gameService.getGamesFromBackend().subscribe(data => {
      console.log(data);
      this.games = data;
      this.games.forEach(game => console.log(game.imageUrl)); // Log imageUrl for debugging
    });
  }
  sanitizeImageUrl(imageUrl: string): SafeUrl {
    // Sanitize the image URL to mark it as safe
    return this.sanitizer.bypassSecurityTrustUrl(imageUrl);
  }
  
  
  
  

  navigateToDetail(id: number) {
  this.router.navigate(['/games', id]);
}
}
