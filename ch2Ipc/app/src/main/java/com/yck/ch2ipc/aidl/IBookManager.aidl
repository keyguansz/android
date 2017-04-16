package com.yck.ch2ipc.aidl;

import com.yck.ch2ipc.aidl.Book;

interface IBookManager {
     List<Book> getBookList();
     void addBook(in Book book);
     void registerListener(IOnNewBookArrivedListener listener);
     void unregisterListener(IOnNewBookArrivedListener listener);
}