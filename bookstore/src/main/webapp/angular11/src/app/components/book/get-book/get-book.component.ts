import { Route } from '@angular/compiler/src/core';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/models/book';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-get-book',
  templateUrl: './get-book.component.html',
  styleUrls: ['./get-book.component.css']
})
export class GetBookComponent implements OnInit {

    book: Book = {isbn: null, title: '', authorName: '', releaseDate: null, price: null};

    constructor(private route: ActivatedRoute, private bookService: BookService) { }

    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.bookService.get(params.isbn).subscribe(book => {
                this.book = book;
            })
        })
    }

    update(ISBN: number, title: string, authorName: string, releaseDate: Date, price: number): void {
        this.bookService.update(ISBN,title,authorName,releaseDate,price).subscribe((book) => {
            console.log('Book added from add-book.component.ts',book);
        })
    }

}
