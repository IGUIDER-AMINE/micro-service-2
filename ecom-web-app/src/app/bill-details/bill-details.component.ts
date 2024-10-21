import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {DatePipe, DecimalPipe, NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-bill-details',
  standalone: true,
  imports: [
    NgIf,
    NgForOf,
    DatePipe,
    DecimalPipe
  ],
  templateUrl: './bill-details.component.html',
  styleUrl: './bill-details.component.css'
})
export class BillDetailsComponent {
  billId! : number;
  billDetails : any;
  constructor(private http : HttpClient,
              private router : Router,
              private route : ActivatedRoute) {
    this.billId  = this.route.snapshot.params["billId"];
  }

  ngOnInit() {
    this.http.get("http://localhost:8888/BILLING-SERVICE/fullBill/"+this.billId).subscribe({
      next : (data) => {
        console.log(data)
        this.billDetails = data;
      },
      error : (err) => {}
    })
  }

}

