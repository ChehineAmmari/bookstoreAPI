import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AddBookComponent } from './components/book/add-book/add-book.component';
import { GetBookComponent } from './components/book/get-book/get-book.component';
import { HomeComponent } from './components/home/home.component';
import { ShoppingCartComponent } from './components/cart/shopping-cart/shopping-cart.component';

const routes: Routes = [
    {
        path: '',
        component: HomeComponent
    },
    {
        path: 'add', 
        component: AddBookComponent
    },
    {
        path: 'get/:isbn', 
        component: GetBookComponent
    },
    {
        path: 'shopping-cart',
        component: ShoppingCartComponent
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }