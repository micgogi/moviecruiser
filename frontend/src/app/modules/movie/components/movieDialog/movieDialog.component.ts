import { Component, OnInit, Inject } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MovieService } from '../../movie.service';
import { Movie } from '../../movie';

@Component({
  selector: 'movie-movie-dialog',
  templateUrl: './movieDialog.component.html',
  styleUrls: ['./movieDialog.component.css']
})
export class MovieDialogComponent implements OnInit {

  movie: Movie;
  comments: string;
  actionType: string;

  constructor(private snacBar: MatSnackBar, private dialogRef: MatDialogRef<MovieDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any, private movieService: MovieService) {
    
    this.comments = data.obj.comments;
    this.movie = data.obj;
    this.actionType = data.actionType;
  }

  ngOnInit() {
  }

  onNoClick() {
    this.dialogRef.close();
  }

  updateComments() {
    this.movie.comments = this.comments;
    this.dialogRef.close();
    this.movieService.updateWatchlist(this.movie).subscribe(() => {
      this.snacBar.open("Movie updated to Watchlist successfully", "", {
        duration: 2000
      });
    })
  }
}
