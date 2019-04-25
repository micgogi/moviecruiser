import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatButtonModule, MatButton } from '@angular/material/button'
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { MovieModule } from 'src/app/modules/movie/movie.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations'
import { MatToolbarModule, MatToolbar } from '@angular/material/toolbar';
import {MatDialogModule} from '@angular/material/dialog';

import { AuthenticationModule } from 'src/app/modules/authentication/authentication.module';
import { AuthGuardService } from 'src/app/authGuard.service';
const appRoutes:Routes = [

  // {
  //   path:'',
  //   redirectTo: 'movies',
  //   pathMatch:'full'
  // }
    {
       path:'',
       redirectTo: '/login',
       pathMatch: 'full'
     }
]



 @NgModule({
   declarations: [
     AppComponent,
    
   ],
   imports :[MovieModule,
    AuthenticationModule,
  BrowserModule,
  BrowserAnimationsModule,
  MatToolbarModule,
  MatDialogModule,
  MatButtonModule,
RouterModule.forRoot(appRoutes)],
   providers: [AuthGuardService],
   bootstrap: [AppComponent]
 })
export class AppModule { }