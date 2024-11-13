package com.JDDev.swiote;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Random;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private List<Quote> quotes;
    private Random random = new Random();

    public CardAdapter(List<Quote> quotes) {
        this.quotes = quotes;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Quote quote = quotes.get(position);
        holder.quoteText.setText(quote.getQuote());
        holder.bookTitleText.setText(quote.getBookTitle());

        // Generate a pastel light color
        int red = 200 + random.nextInt(56); // Between 200-255
        int green = 200 + random.nextInt(56); // Between 200-255
        int blue = 200 + random.nextInt(56); // Between 200-255
        int color = Color.rgb(red, green, blue);

        holder.itemView.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {
        TextView quoteText;
        TextView bookTitleText;

        CardViewHolder(View itemView) {
            super(itemView);
            quoteText = itemView.findViewById(R.id.cardText);
            bookTitleText = itemView.findViewById(R.id.bookTitleText);
        }
    }
}
