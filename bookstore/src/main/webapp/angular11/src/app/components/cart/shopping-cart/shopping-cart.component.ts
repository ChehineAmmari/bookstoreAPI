import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/models/book';
import { BookService } from 'src/app/services/book.service';
import { ShoppingCartService } from 'src/app/services/shopping-cart.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  availableBooks: Book[]

  books: Book[];
  quantities: number[];
  total = 0;

  constructor(private cartService: ShoppingCartService, private bookService: BookService) { }

  ngOnInit(): void {

    this.books = this.cartService.getAllBooks();
    this.quantities = this.cartService.getAllQuantities();

    

    if(this.books != null){
        this.bookService.getAll().subscribe(books => {
            this.availableBooks = books;
            this.books.forEach(book => {
                if(this.availableBooks.map((b) => b.isbn).indexOf(book.isbn) < 0 ) {
                    this.delete(book.isbn);
                    this.books = this.cartService.getAllBooks();
                }
            })

            for(let i=0; i<this.books.length; i++) {
                this.cartService.calculate(this.books[i].isbn, this.quantities[i]).subscribe(res => {
                    this.total += res;
                });
            }
        });

        
    }
  }

  purchase() {
      this.cartService.purchase();
      this.books = this.cartService.getAllBooks();
      this.total = 0;
  }

  delete(isbn: number): void {

    this.cartService.remove(isbn);
    this.books = this.cartService.getAllBooks();
    this.quantities = this.cartService.getAllQuantities();
    let newTotal = 0;
    for(let i=0; i<this.books.length; i++) {
        this.cartService.calculate(this.books[i].isbn, this.quantities[i]).subscribe(res => {
            if(this.books.length <= 0)
                newTotal = 0;
            newTotal += res;
            this.total = newTotal;
        });
    }
    this.total = newTotal;
}

}
