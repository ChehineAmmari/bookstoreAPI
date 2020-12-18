import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { Observable } from 'rxjs';

import { Book } from '../models/book';

@Injectable({
    providedIn: 'root'
})
export class BookService {
    uri: string = 'http://localhost:8080/bookstore/api/book';

    constructor(private http: HttpClient) { }

    getAll(): Observable<Book[]> {
        console.log('get all service');
        return this.http.get<Book[]>(`${this.uri}/getAll`);
    }

    get(isbn: number): Observable<Book> {
        return this.http.get<Book>(`${this.uri}/get/${isbn}`);
    }

    add(ISBN: number, title: string, authorName: string, releaseDate: Date, price: number): Observable<Book> {
        console.log('add service');
        let book: Book = new Book(ISBN,title,price,releaseDate,authorName);
        return this.http.post<Book>(`${this.uri}/add`,book);
    }

    delete(isbn: number) {
        console.log('delete service');
        let params = new HttpParams().set("ISBN",isbn.toString());
        return this.http.get(`${this.uri}/delete`,{params});
    }

    update(ISBN: number, title: string, authorName: string, releaseDate: Date, price: number): Observable<Book> {
        console.log('update service');
        let book: Book = new Book(ISBN,title,price,releaseDate,authorName);
        return this.http.post<Book>(`${this.uri}/update`,book);
    }

}
