import { Component, OnInit } from '@angular/core';

import { ArrayType } from '@angular/compiler/src/output/output_ast';
import { MovieService} from '../../movie.service';
import { Input } from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar'
import {MatDialogModule} from '@angular/material/dialog';
import { Output,EventEmitter } from '@angular/core';
import { MovieDialogComponent } from 'src/app/modules/movie/components/movieDialog/movieDialog.component';
import { MatDialog } from '@angular/material/dialog';
import { Movie } from 'src/app/modules/movie/movie';
@Component({
  selector: 'movie-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {
  @Input() 
  movie:Movie;
  @Input()
  usewatchListApi : boolean;

  @Output()
  addMovie = new EventEmitter();
   @Output()
    deleteMovie = new EventEmitter();
   
   constructor(private movieService:MovieService,private dialog: MatDialog,
    private snackbar:MatSnackBar) { 
   
     }

  ngOnInit() {
   
    
  }
  addToWatchList()
{
  this.addMovie.emit(this.movie);
  // this.movieService.addMoviesToWatchList(this.movie).subscribe((movie)=>{
  // this.snackbar.open("Movie Added To WatchLis",'',{
  //   duration:1000
  // });
  // })
}
deleteFromWatchList(){
  
  this.deleteMovie.emit(this.movie);
}

updateFromWatchList(actionType)
{
  let dialogRef = this.dialog.open(MovieDialogComponent, {
    width: '400px', data: {obj: this.movie, actionType: actionType}
  });
  dialogRef.afterClosed().subscribe(result => {
  });

}
}
