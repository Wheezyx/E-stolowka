import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  navigateToOrder(): void {
    this.router.navigateByUrl('/order');
  }

  navigateToAccountPage(): void {
    this.router.navigateByUrl('/account');
  }

  navigateToInformationPage(): void {
    this.router.navigateByUrl('/information')
  }

}
