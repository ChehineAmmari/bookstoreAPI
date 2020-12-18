import { Component, OnInit } from '@angular/core';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {

  constructor(private bookService: BookService) { }

  ngOnInit(): void {
  }

  add(ISBN: number, title: string, authorName: string, releaseDate: Date, price: number, event: Event): void {
    event.preventDefault();
      this.bookService.add(ISBN,title,authorName,releaseDate,price).subscribe((book) => {
          console.log('Book added from add-book.component.ts',book);
      })
  }

}
