import { Component, OnInit } from '@angular/core';
import {Movie} from '../../movie';
import {MovieService} from '../../movie.service'
@Component({
  selector: 'movie-watchlist',
  template: `<movie-container [movies]="movies" [usewatchListApi]='usewatchListApi'></movie-container>`,
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {
movies:Array<Movie>
usewatchListApi=true;
  constructor(private movieService:MovieService) { 
    this.movies=[];
  }

  ngOnInit() {
    this.movieService.getWatchListedMovies().subscribe((movies)=>{
      console.log(movies);
      this.movies.push(...movies)
    });

  }

}
