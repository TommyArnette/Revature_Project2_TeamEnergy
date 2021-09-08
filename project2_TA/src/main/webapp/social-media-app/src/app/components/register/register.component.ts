import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  username: string = "";
  password: string = "";
  firstName: string = "";
  lastName: string = "";
  email: string = "";

  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }
}
