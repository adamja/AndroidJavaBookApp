package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class BookActivity extends AppCompatActivity {

    private TextView txtBookName, txtAuthor, txtPages, txtDesc, txtLongDesc;
    private Button btnAddToCurrentlyReading, btnAddToWantToRead, btnAddToAlreadyRead, btnAddToFavourites;
    private ImageView imgBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

        String longDesc = "A young woman named Aomame follows a taxi driver’s enigmatic suggestion and begins to notice puzzling discrepancies in the world around her. She has entered, she realizes, a parallel existence, which she calls 1Q84 —“Q is for ‘question mark.’ A world that bears a question.” Meanwhile, an aspiring writer named Tengo takes on a suspect ghostwriting project. He becomes so wrapped up with the work and its unusual author that, soon, his previously placid life begins to come unraveled.\n" +
                "\n" +
                "As Aomame’s and Tengo’s narratives converge over the course of this single year, we learn of the profound and tangled connections that bind them ever closer: a beautiful, dyslexic teenage girl with a unique vision; a mysterious religious cult that instigated a shoot-out with the metropolitan police; a reclusive, wealthy dowager who runs a shelter for abused women; a hideously ugly private investigator; a mild-mannered yet ruthlessly efficient bodyguard; and a peculiarly insistent television-fee collector.\n" +
                "\n" +
                "A love story, a mystery, a fantasy, a novel of self-discovery, a dystopia to rival George Orwell’s — 1Q84 is Haruki Murakami’s most ambitious undertaking yet: an instant best seller in his native Japan, and a tremendous feat of imagination from one of our most revered contemporary writers.";

        Book book = new Book(1, "1Q84", "Haruki Murakami", 1350,
                "https://publishingperspectives.com/wp-content/uploads/2014/09/cover-1Q84.jpg",
                "A work of maddening brilliance",
                longDesc);

            setData(book);
    }

    private void setData(Book book) {
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDesc.setText(book.getShortDesc());
        txtLongDesc.setText(book.getLongDesc());

        Glide.with(this)
                .asBitmap()
                .load(book.getImageUrl())
                .into(imgBook);
    }

    private void initViews() {
        txtBookName = findViewById(R.id.txtBookName);
        txtAuthor = findViewById(R.id.txtAuthor);
        txtPages = findViewById(R.id.txtPages);
        txtDesc = findViewById(R.id.txtDesc);
        txtLongDesc = findViewById(R.id.txtLongDesc);

        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToWantToRead = findViewById(R.id.btnAddToWantToRead);
        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnAddToFavourites = findViewById(R.id.btnAddToFavourites);

        imgBook = findViewById(R.id.imgBook);
    }
}