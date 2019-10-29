package com.ferreirae.buycheapstuff;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class BuyableItemAdapter extends RecyclerView.Adapter<BuyableItemAdapter.BuyableItemViewHolder> {

    public List<BuyableItem> items;
    private OnBuyableItemInteractionListener listener;

    public BuyableItemAdapter(List<BuyableItem> items, OnBuyableItemInteractionListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public static class BuyableItemViewHolder extends RecyclerView.ViewHolder {
        BuyableItem item;
        TextView itemTitleView;
        TextView itemPriceView;
        public BuyableItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemTitleView = itemView.findViewById(R.id.title);
            this.itemPriceView = itemView.findViewById(R.id.price);
        }
    }



    // RecyclerView needs us to create a brand new row, from scratch, for holding data
    @NonNull
    @Override
    public BuyableItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_buyable_item, parent, false);
        final BuyableItemViewHolder holder = new BuyableItemViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.potato(holder.item);
            }
        });
        return holder;
    }

    // RecyclerView has a row (maybe previously used?) that needs to be updated for a particular location/index
    @Override
    public void onBindViewHolder(@NonNull BuyableItemViewHolder holder, int position) {
        BuyableItem itemAtPosition = this.items.get(position);
        holder.item = itemAtPosition;
        holder.itemTitleView.setText(itemAtPosition.getTitle());
        holder.itemPriceView.setText(itemAtPosition.getPriceAsANiceString());
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    // Make sure that my adapter can communicate with any Activity it's a part of that implements this interface
    public static interface OnBuyableItemInteractionListener {
        public void potato(BuyableItem item);
    }
}
