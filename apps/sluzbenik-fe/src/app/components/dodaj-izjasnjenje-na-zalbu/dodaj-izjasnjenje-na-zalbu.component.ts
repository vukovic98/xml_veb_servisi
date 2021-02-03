import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-dodaj-izjasnjenje-na-zalbu',
  templateUrl: './dodaj-izjasnjenje-na-zalbu.component.html',
  styleUrls: ['./dodaj-izjasnjenje-na-zalbu.component.css']
})
export class DodajIzjasnjenjeNaZalbuComponent implements OnInit {

  addPostForm = new FormGroup({
    "sadrzaj": new FormControl('', Validators.required)
  });

  constructor(
    public dialogRef: MatDialogRef<DodajIzjasnjenjeNaZalbuComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit(): void {
  }

  submitPost() {
    this.data.sadrzaj = this.addPostForm.value.sadrzaj;
    this.dialogRef.close({data: this.data});
  }

}
