

import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import {CommonModule } from '@angular/common';
import {FormsModule} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import {MatIconModule} from '@angular/material/icon';
import {MatFormFieldModule,MatIcon} from '@angular/material';
import {MatInputModule} from '@angular/material';
import { AuthenticationService } from './authentication.service';
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import { AuthenticationRouterModule } from './/authentication-router.module';
import {MatCardModule} from '@angular/material/card';

@NgModule({
  imports: [
   CommonModule,
   FormsModule,
   MatFormFieldModule,
   MatInputModule,
   MatButtonModule,
   MatIconModule,
   MatCardModule,
    HttpClientModule,
    AuthenticationRouterModule
  ],
  declarations: [
    RegistrationComponent,
    LoginComponent
  ],
  providers: [AuthenticationService],
  exports: [
    AuthenticationRouterModule
  ]
})
export class AuthenticationModule { }