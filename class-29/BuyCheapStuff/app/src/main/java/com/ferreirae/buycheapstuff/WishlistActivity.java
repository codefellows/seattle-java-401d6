//package com.ferreirae.buycheapstuff;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.room.Room;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class WishlistActivity extends AppCompatActivity implements BuyableItemAdapter.OnBuyableItemInteractionListener {
//
//    public AppDatabase db;
//
//    private List<BuyableItem> buyableItems;
//    BuyableItemAdapter buyableItemAdapter;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_wishlist);
//
//        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "buy-cheap-stuff")
//                .allowMainThreadQueries()
//                .build();
//
//        buyableItems = new ArrayList<BuyableItem>();
//        //TODO: find all photos that belong to this wishlist
//        buyableItems.add(new BuyableItem("Ginger Photos", 100000));
//        buyableItemAdapter = new BuyableItemAdapter(buyableItems, this);
//
//        final RecyclerView recyclerView = findViewById(R.id.results);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(buyableItemAdapter);
//
//    }
//
//    @Override
//    public void potato(BuyableItem item) {
//        Intent goToBuyActivityIntent = new Intent(this, BuyItem.class);
//        goToBuyActivityIntent.putExtra("item", item.getTitle());
//        WishlistActivity.this.startActivity(goToBuyActivityIntent);
//    }
//
//}
