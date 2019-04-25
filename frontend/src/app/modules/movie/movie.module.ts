import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ThumbnailComponent } from '../movie/components/thumbnail/thumbnail.component';
import { HttpClientModule } from '@angular/common/http';
import { MovieService } from  './movie.service';
import { MatCardModule } from '@angular/material/card';
import { MovieRouterModule } from './movie-router.module';
import { ContainerComponent } from 'src/app/modules/movie/components/container/container.component';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { MatButton, MatButtonModule } from '@angular/material/button';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MovieDialogComponent} from './components/movieDialog/movieDialog.component'
import {MatInputModule} from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { SearchComponent } from './components/search/search.component';
import { TokenInterceptor } from 'src/app/modules/movie/interceptor.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
@NgModule({
  declarations: [ThumbnailComponent,MovieDialogComponent, ContainerComponent, WatchlistComponent, TmdbContainerComponent, SearchComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    MovieRouterModule,
    MatCardModule,
    FormsModule,
   MatButtonModule,
   MatSnackBarModule,MatInputModule
  ],
  entryComponents:[MovieDialogComponent],
  providers:[MovieService,{
    provide: HTTP_INTERCEPTORS,
    useClass:TokenInterceptor,
    multi:true
  }],
  exports: [ThumbnailComponent,
  MovieRouterModule,MovieDialogComponent]
})
export class MovieModule { }
