import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from  'rxjs/operators/map';
import { Movie} from './movie';
import { Observable } from 'rxjs/Observable'
@Injectable({
  providedIn: 'root'
})
export class MovieService {
  tmdbendpoint:string;
  imagePrefix:string;
  apiKey:string;
  watchListEndpoint:string;
  springEndPoint:string;
  constructor(private http :HttpClient) {
    this.apiKey="api_key=cac123b62d54b36212fbd06dfd65c65b";
    this.imagePrefix="https://image.tmdb.org/t/p/w500";
    this.tmdbendpoint="https://api.themoviedb.org/3";
   //this.watchListEndpoint="http://localhost:3000/watchlist"
    this.springEndPoint="http://localhost:8089/api/v1/movieservice/movie"
   }

   getMovies(type: string, page: number =1): Observable<Array<Movie>>{
     console.log(type);
    const endPoint= `${this.tmdbendpoint}/movie/${type}?${this.apiKey}&page=${page}`
    return this.http.get(endPoint).pipe(
      map(this.pickMovieResults),
      map(this.transferPosterPath.bind(this))
    )
   }
  //  getPopularmovies(page : number = 1):Observable<Array<Movie>>{
  //    const popularMoviesEndpoint= `${this.tmdbendpoint}/popular?${this.apiKey}&page=${page}`
  //   return this.http.get(popularMoviesEndpoint).pipe(
  //     map(this.pickMovieResults),
  //     map(this.transferPosterPath.bind(this))
  //   );
  //  }
  //  getTopRatedmovies(page : number = 1):Observable<Array<Movie>>{
  //   const topRatedMoviesEndpoint= `${this.tmdbendpoint}/top_rated?${this.apiKey}&page=${page}`
  //  return this.http.get(topRatedMoviesEndpoint).pipe(
  //    map(this.pickMovieResults),
  //    map(this.transferPosterPath.bind(this))
  //  );
  // }
   transferPosterPath(movies): Array<Movie>{
   return movies.map((movie)=>{
     movie.poster_path = `${this.imagePrefix}${movie.poster_path}`;
     return movie;
   })
 
   }


   searchMovie(searchKey: string,page: number = 1): Observable<Array<Movie>> {
    if (searchKey.length > 0) {
      const searchEndpoint = `${this.tmdbendpoint}/search/movie?${this.apiKey}&page=${page}&include_adult=false&query=${searchKey}`;
      return this.http.get(searchEndpoint).pipe(
        
        map(this.pickMovieResults),
        map(this.transferPosterPath.bind(this))
      );
    }
  }

   pickMovieResults(response){
      return response['results'];
   }
   addMoviesToWatchList(movie){
      return this.http.post(this.springEndPoint,movie);
   }
   saveWatchListMovies(movie){
     return this.http.post(this.springEndPoint,movie)
   }

   getWatchListedMovies():Observable<Array<Movie>>{
     return this.http.get<Array<Movie>>(this.springEndPoint+'s');
   }
   
   deleteFromMyWatchList(movie:Movie){
     const url= `${this.springEndPoint}/${movie.id}`;
     console.log(url);
     return this.http.delete(url,{responseType:'text'});
   }
   updateWatchlist(movie) {
    const url= `${this.springEndPoint}/${movie.id}`;
    return this.http.put(url, movie);
  }

}
