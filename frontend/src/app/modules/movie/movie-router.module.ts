import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import {SearchComponent} from './components/search/search.component';
import {ContainerComponent} from './components/container/container.component';
import {TmdbContainerComponent} from './components/tmdb-container/tmdb-container.component';
import { WatchlistComponent } from 'src/app/modules/movie/components/watchlist/watchlist.component';
import { AuthenticationService } from 'src/app/modules/authentication/authentication.service';
import { AuthGuardService } from 'src/app/authGuard.service';
const movieRoutes: Routes = [

  {
    path: 'movies',
    canActivate: [AuthGuardService],
    children:[
      {
        path:'',
        redirectTo:'/movies/popular',
        pathMatch: 'full',
       // canActivate: [AuthGuardService]
      },
      {
        path:'popular',
        component:TmdbContainerComponent,
       // canActivate: [AuthGuardService],
        data:{
          movieType:'popular'
        },
      },
        {
        path:'top-rated',
        component:TmdbContainerComponent,
       // canActivate: [AuthGuardService],
        data:{
          movieType:'top_rated'
        }
      },
      {
        path:'watchlist',
        component:WatchlistComponent,
        //canActivate: [AuthGuardService]
      },
      {
        path:'search',
        component:SearchComponent,
       // canActivate: [AuthGuardService]
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(movieRoutes)],
  exports: [RouterModule]
})
export class MovieRouterModule { }
