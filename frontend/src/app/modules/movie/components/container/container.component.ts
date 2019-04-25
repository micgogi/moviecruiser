import { Component, OnInit } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService} from '../../movie.service';
import { Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
@Component({
  selector: 'movie-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {
  @Input()
   movies:Array<Movie>;
   @Input()
   usewatchListApi:boolean;  
  constructor(private movieService:MovieService,private matSnackBar:MatSnackBar,private router:Router) { 
  
  }

  ngOnInit() {
   
  }


  addMovieToWatchList(movie){
    let message = `${movie.title} add to watch list`;
   this.movieService.addMoviesToWatchList(movie).subscribe((movie)=>{
  this.matSnackBar.open("Movie Added To WatchLis",'',{
     duration:1000
   });
   })
  }

  deleteFromWatchList(movie){
    console.log(movie);
    let message = `${movie.title} deleted from your watchlist`;
    
    this.movieService.deleteFromMyWatchList(movie).subscribe((movie)=>{
      this.matSnackBar.open(message,'',{
        duration:1000
      });
    });
    const index = this.movies.indexOf(movie);
    this.movies.splice(index,1);
  }
}
