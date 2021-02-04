package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {

    private RecyclerView booksRecView;
    private BookRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        adapter = new BookRecViewAdapter(this);
        booksRecView = findViewById(R.id.booksRecView);

        booksRecView.setAdapter(adapter);
        // Cannot use the grid layout with the way that it is configured currently. Only works with the linear layout
//        booksRecView.setLayoutManager(new GridLayoutManager(this, 2));
        booksRecView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Book> books = new ArrayList<>();
        books.add((new Book(1, "1Q84", "Haruki Murakami", 1350,
                "https://publishingperspectives.com/wp-content/uploads/2014/09/cover-1Q84.jpg",
                "A work of maddening brilliance",
                "Long Description")));
        books.add((new Book(2, "The Myth of Sisyphus", "Albert Camus", 250,
                "https://images-na.ssl-images-amazon.com/images/I/41wRI29K-iL._SX301_BO1,204,203,200_.jpg",
                "The Myth of Sisyphus is a 1942 philosophical essay by Albert Camus. The English translation by Justin O'Brien was first published in 1955. Influenced by philosophers such as Søren Kierkegaard, Arthur Schopenhauer, and Friedrich Nietzsche, Camus introduces his philosophy of the absurd.",
                "Long Description")));
        adapter.setBooks(books);
    }
}