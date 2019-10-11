package com.muklasr.bukuku;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.CardViewViewHolder> {
    private ArrayList<Book> bookList;
    private OnItemClickCallback onItemClickCallback;

    public BookAdapter(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder cardViewViewHolder, int i) {
        Book book = bookList.get(i);
        Glide.with(cardViewViewHolder.itemView.getContext())
                .load(book.getImage())
                .apply(new RequestOptions().override(150,150))
                .into(cardViewViewHolder.img);

        cardViewViewHolder.tvTitle.setText(book.getTitle());
        cardViewViewHolder.tvDesc.setText(book.getDesc());

        cardViewViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(bookList.get(cardViewViewHolder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvTitle, tvDesc;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.card_photo);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
        }
    }
    interface OnItemClickCallback {
        void onItemClicked(Book data);
    }
}
