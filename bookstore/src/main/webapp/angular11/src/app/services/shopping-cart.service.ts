import { Injectable } from '@angular/core';
import { Book } from '../models/book';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class ShoppingCartService {

  uri: string = 'http://localhost:8089/SpringMVCHibernateCRUDExample/api/book';

  books: Book[] = [];
  quantities: number[] = [];

  constructor(private http: HttpClient) { }

  add(book: Book, quantity: number) {
    this.books = JSON.parse(localStorage.getItem('books')) == null ? [] : JSON.parse(localStorage.getItem('books'));
    this.quantities = JSON.parse(localStorage.getItem('quantities')) == null ? [] : JSON.parse(localStorage.getItem('quantities'));
    let i = -1;
    let j = 0;

    this.books.forEach(b => {
        if(b.isbn == book.isbn) {
            i = j;
        }
        j++;
    });
      if(i >= 0) {
          this.quantities[i] += quantity;
          if(this.quantities[i] > 5){
              this.quantities[i] = 5;
          }
      }else {
          this.books.push(book);
          this.quantities.push(quantity);
      }
    localStorage.setItem('books',JSON.stringify(this.books));
    localStorage.setItem('quantities',JSON.stringify(this.quantities));
  }

  getAllBooks(): Book[] {
    return JSON.parse(localStorage.getItem('books'));
    //return this.books;
  }

  getAllQuantities(): number[] {
    return JSON.parse(localStorage.getItem('quantities'));
    //return this.quantities;
  }

  calculate(ISBN?: number, qte?: number) {
    return this.http.get<number>(`${this.uri}/calculate?ISBN=${ISBN}&qte=${qte}`);
  }

  purchase() {
    localStorage.removeItem('books');
    localStorage.removeItem('quantities');
  }

  remove(isbn: number): void {
    let q = [];
    let i: number = 0;

    this.books = this.getAllBooks().filter((book,index) => {
        if(book.isbn != isbn) {
            return book;
        }else{
          i = index;
        }
    });

    this.getAllQuantities().forEach((qte,index) =>{
      if(i != index){
        q.push(qte);
      }
    })

    this.quantities = q;
    

    localStorage.setItem('books',JSON.stringify(this.books));
    localStorage.setItem('quantities',JSON.stringify(this.quantities));
    //console.log(JSON.parse(localStorage.getItem('books')));
  }

}
