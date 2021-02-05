package com.example.bookapp;

// This is a singleton class. It has a private constructor and there can only ever be one instance
// of the class in the running application.

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.StringJoiner;

public class Utils {

    private static final String DB_KEY = "alternate_db";
    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOKS_KEY = "already_read_books";
    private static final String WANT_TO_READ_BOOKS_KEY = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS_KEY = "currently_reading_books";
    private static final String FAVOURITE_BOOKS_KEY = "favourite_books";

    private static Utils instance;
    private SharedPreferences sharedPreferences;

    // No longer required with shared preferences addition
//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> alreadyReadBooks;
//    private static ArrayList<Book> wantToReadBooks;
//    private static ArrayList<Book> currentlyReadingBooks;
//    private static ArrayList<Book> favouriteBooks;

    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences(DB_KEY, Context.MODE_PRIVATE);

        if (null == getAllBooks()) {
            initData();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (null == getAlreadyReadBooks()) {
            editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (null == getWantToReadBooks()) {
            editor.putString(WANT_TO_READ_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (null == getCurrentlyReadingBooks()) {
            editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (null == getFavouriteBooks()) {
            editor.putString(FAVOURITE_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    private void initData() {
        //TODO: add initial data

        ArrayList<Book> books = new ArrayList<>();

        books.add((new Book(1, "1Q84", "Haruki Murakami", 1350,
                "https://publishingperspectives.com/wp-content/uploads/2014/09/cover-1Q84.jpg",
                "A work of maddening brilliance",
                "Long Description")));
        books.add((new Book(2, "The Myth of Sisyphus", "Albert Camus", 250,
                "https://images-na.ssl-images-amazon.com/images/I/41wRI29K-iL._SX301_BO1,204,203,200_.jpg",
                "The Myth of Sisyphus is a 1942 philosophical essay by Albert Camus. The English translation by Justin O'Brien was first published in 1955. Influenced by philosophers such as Søren Kierkegaard, Arthur Schopenhauer, and Friedrich Nietzsche, Camus introduces his philosophy of the absurd.",
                "Long Description")));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));

//        editor.apply();  // apply is more gentle and will apply in a background thread and not block the user
        editor.commit();  // commit will apply synchronously and block the user thread
    }

    // synchronized makes the instance thread safe
    public static synchronized Utils getInstance(Context context) {
        if (null == instance) {
            instance = new Utils(context);
        }
        return instance;
    }

    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getFavouriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVOURITE_BOOKS_KEY, null), type);
        return books;
    }

    public Book getBookById(int id) {
        ArrayList<Book> books = getAllBooks();

        if (null != books) {
            for (Book b: books) {
                if (b.getId() == id) {
                    return b;
                }
            }
        }



        return null;
    }

    public boolean addToAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS_KEY);  // remove the current list from shared preferences
                editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(books));  // add the new list with removed item to shared preferences
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToWantToRead(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS_KEY);  // remove the current list from shared preferences
                editor.putString(WANT_TO_READ_BOOKS_KEY, gson.toJson(books));  // add the new list with removed item to shared preferences
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS_KEY);  // remove the current list from shared preferences
                editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(books));  // add the new list with removed item to shared preferences
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToFavourites(Book book) {
        ArrayList<Book> books = getFavouriteBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVOURITE_BOOKS_KEY);  // remove the current list from shared preferences
                editor.putString(FAVOURITE_BOOKS_KEY, gson.toJson(books));  // add the new list with removed item to shared preferences
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {  // we must compare the id to find the object because the object references change when serialising between the shared preferences...
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS_KEY);  // remove the current list from shared preferences
                        editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(books));  // add the new list with removed item to shared preferences
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromWantToRead(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {  // we must compare the id to find the object because the object references change when serialising between the shared preferences...
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS_KEY);  // remove the current list from shared preferences
                        editor.putString(WANT_TO_READ_BOOKS_KEY, gson.toJson(books));  // add the new list with removed item to shared preferences
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {  // we must compare the id to find the object because the object references change when serialising between the shared preferences...
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS_KEY);  // remove the current list from shared preferences
                        editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(books));  // add the new list with removed item to shared preferences
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromFavourites(Book book) {
        ArrayList<Book> books = getFavouriteBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {  // we must compare the id to find the object because the object references change when serialising between the shared preferences...
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVOURITE_BOOKS_KEY);  // remove the current list from shared preferences
                        editor.putString(FAVOURITE_BOOKS_KEY, gson.toJson(books));  // add the new list with removed item to shared preferences
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
