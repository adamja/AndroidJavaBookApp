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

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        adapter = new BookRecViewAdapter(this, "allBooks");
        booksRecView = findViewById(R.id.booksRecView);

        booksRecView.setAdapter(adapter);
        // Cannot use the grid layout with the way that it is configured currently. Only works with the linear layout
//        booksRecView.setLayoutManager(new GridLayoutManager(this, 2));
        booksRecView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setBooks(Utils.getInstance().getAllBooks());
    }

    // This animation is applied when the back button is pressed. The animation is not great
    // but it does apply
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
    }
}