import { Component } from '@angular/core';
import { ThumbnailComponent } from  './modules/movie/components/thumbnail/thumbnail.component'
import { AuthenticationService } from 'src/app/modules/authentication/authentication.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-root',
  template: `
  <mat-toolbar  color="primary">
  <span>Movie Cruiser</span>
  <button mat-button [routerLink]="['/movies/popular']">Popular Movie</button>
  <button mat-button [routerLink]="['/movies/top-rated']">Top Rated Movie</button>
  <button mat-button [routerLink]="['/movies/watchlist']">Watch List</button>
  <button mat-button class="search-button"[routerLink]="['/movies/search']">Search</button>
  <button mat-button (click)="Logout()">Logout</button>
  </mat-toolbar>
  <router-outlet></router-outlet>`,
  styles: []
})
export class AppComponent {
  title = 'movie-cruise-application';
 constructor(private auth:AuthenticationService,private routes:Router){}

 Logout(){
   this.auth.deleteToken();
   this.routes.navigate(['/login'])
 }
}
