import { Component, OnInit } from '@angular/core';
import { Book } from '../../models/book';
import { BookService } from '../../services/book.service';
import { ShoppingCartService } from 'src/app/services/shopping-cart.service';
import { ShoppingCartComponent } from '../cart/shopping-cart/shopping-cart.component';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

    books: Book[] = [];
    cart: string[] = [];

    constructor(private bookService: BookService, private cartService: ShoppingCartService) { }

    ngOnInit(): void {
        this.bookService.getAll().subscribe(books => {
            this.books = books;
        });
    }

    delete(isbn: number): void {
        this.bookService.delete(isbn).subscribe(() => {
            this.ngOnInit();
        });
    }

    addToCart(isbn: number,quantity): void {
        this.bookService.get(isbn).subscribe(book => {
            this.cartService.add(book,parseInt(quantity));
        });
    }

}
