import { Component, OnInit } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService} from '../../movie.service';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'movie-tmdb-container',
  template: `<movie-container [movies]="movies"></movie-container>`,
  styleUrls: ['./tmdb-container.component.css']
})
export class TmdbContainerComponent implements OnInit {
  movies:Array<Movie>;
  movieType:string;
  constructor(private movieService:MovieService,private route:ActivatedRoute) { 
    this.movies =[];
     this.route.data.subscribe((data)=>{
       this.movieType=data.movieType
       console.log(this.movieType)
     })
   }

   ngOnInit() {
    this.movieService.getMovies(this.movieType).subscribe(
      (movies)=>{
        this.movies.push(...movies)
      }
    )
  }

}
