package com.example.bookapp;

// This is a singleton class. It has a private constructor and there can only ever be one instance
// of the class in the running application.

import java.util.ArrayList;

public class Utils {

    private static Utils instance;

    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> favouriteBooks;

    private Utils() {
        if (null == allBooks) {
            allBooks = new ArrayList<>();
            initData();
        }

        if (null == alreadyReadBooks) {
            alreadyReadBooks = new ArrayList<>();
        }

        if (null == wantToReadBooks) {
            wantToReadBooks = new ArrayList<>();
        }

        if (null == currentlyReadingBooks) {
            currentlyReadingBooks = new ArrayList<>();
        }

        if (null == favouriteBooks) {
            favouriteBooks = new ArrayList<>();
        }
    }

    private void initData() {
        //TODO: add initial data

        allBooks.add((new Book(1, "1Q84", "Haruki Murakami", 1350,
                "https://publishingperspectives.com/wp-content/uploads/2014/09/cover-1Q84.jpg",
                "A work of maddening brilliance",
                "Long Description")));
        allBooks.add((new Book(2, "The Myth of Sisyphus", "Albert Camus", 250,
                "https://images-na.ssl-images-amazon.com/images/I/41wRI29K-iL._SX301_BO1,204,203,200_.jpg",
                "The Myth of Sisyphus is a 1942 philosophical essay by Albert Camus. The English translation by Justin O'Brien was first published in 1955. Influenced by philosophers such as Søren Kierkegaard, Arthur Schopenhauer, and Friedrich Nietzsche, Camus introduces his philosophy of the absurd.",
                "Long Description")));
    }

    // synchronized makes the instance thread safe
    public static synchronized Utils getInstance() {
        if (null == instance) {
            instance = new Utils();
        }
        return instance;
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getFavouriteBooks() {
        return favouriteBooks;
    }

    public Book getBookById(int id) {
        for (Book b: allBooks) {
            if (b.getId() == id) {
                return b;
            }
        }

        return null;
    }

    public boolean addToAlreadyRead(Book book) {
        return alreadyReadBooks.add(book);
    }

    public boolean addToWantToRead(Book book) {
        return wantToReadBooks.add(book);
    }

    public boolean addToCurrentlyReading(Book book) {
        return currentlyReadingBooks.add(book);
    }

    public boolean addToFavourites(Book book) {
        return favouriteBooks.add(book);
    }

    public boolean removeFromAlreadyRead(Book book) {
        return alreadyReadBooks.remove(book);
    }

    public boolean removeFromWantToRead(Book book) {
        return wantToReadBooks.remove(book);
    }

    public boolean removeFromCurrentlyReading(Book book) {
        return currentlyReadingBooks.remove(book);
    }

    public boolean removeFromFavourites(Book book) {
        return favouriteBooks.remove(book);
    }
}
