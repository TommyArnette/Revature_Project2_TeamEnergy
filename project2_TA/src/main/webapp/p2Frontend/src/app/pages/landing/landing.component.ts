import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {

  _username: string = "";
  _password: string = "";
  _firstName: string = "";
  _lastName: string = "";
  _email: string = "";
  _pictureUrl: string = "";

  constructor() { }

  ngOnInit(): void {
  }

}
