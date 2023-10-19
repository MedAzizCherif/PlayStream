import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GameListComponent } from './game-list/game-list.component';
import { GameDetailComponent } from './game-detail/game-detail.component';

const routes: Routes = [
{ path: '', redirectTo: '/games', pathMatch: 'full' },
{ path: 'games', component: GameListComponent },
{ path: 'games/:id', component: GameDetailComponent },];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
