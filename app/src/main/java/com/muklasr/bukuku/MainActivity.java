package com.muklasr.bukuku;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvBooks;
    private ArrayList<Book> list = new ArrayList<>();
    private Button btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        btnAbout = findViewById(R.id.btnAbout);
        rvBooks = findViewById(R.id.rvBook);
        rvBooks.setHasFixedSize(true);

        list.addAll(BooksData.getListData());
        showRecycler();

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(i);
            }
        });
    }

    private void showRecycler() {
        rvBooks.setLayoutManager(new LinearLayoutManager(this));
        BookAdapter bookAdapter = new BookAdapter(list);
        rvBooks.setAdapter(bookAdapter);

        bookAdapter.setOnItemClickCallback(new BookAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Book data) {
                Intent i = new Intent(getApplicationContext(), DetailActivity.class);
                i.putExtra("title", data.getTitle());
                i.putExtra("desc", data.getDesc());
                i.putExtra("writer", data.getWriter());
                i.putExtra("publisher", data.getPublisher());
                i.putExtra("year", data.getYear());
                i.putExtra("genre", data.getGenre());
                i.putExtra("image", data.getImage());
                startActivity(i);
            }
        });
    }
}
