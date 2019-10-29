package com.ferreirae.buycheapstuff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BuyableItemAdapter.OnBuyableItemInteractionListener {

    private String enteredItemName = null;

    private List<BuyableItem> buyableItems;

    private Wishlist onlyWishlist;

    public AppDatabase db;

    BuyableItemAdapter buyableItemAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        // grab username from sharedprefs and use it to update the label
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String username = prefs.getString("username", "user");
        TextView nameTextView = findViewById(R.id.hiTextView);
        nameTextView.setText("Hi, " + username + "!");
    }

    // gets called automatically when the MainActivity is created/shown for the first time
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // these will always be here, every time, thanks Android
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // make a db
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "buy-cheap-stuff")
                .allowMainThreadQueries()
                .build();
        buyableItems = new ArrayList<BuyableItem>(db.buyableItemDao().getAll());
        buyableItemAdapter = new BuyableItemAdapter(buyableItems, this);
//        buyableItems = db.buyableItemDao().getAll();

        // render the buyable items to the screen, in the RecyclerView
        // https://developer.android.com/guide/topics/ui/layout/recyclerview
        final RecyclerView recyclerView = findViewById(R.id.results);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(buyableItemAdapter);

        // grab the button, using its ID and the generated R (resource) info
        Button button = findViewById(R.id.button);
        // add the event listener to the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View event) {
                // hide keyboard
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                // grab what was typed in
                EditText editText = findViewById(R.id.editText);
                enteredItemName = editText.getText().toString();

                // make a new buyable item
                BuyableItem newItem = new BuyableItem(enteredItemName,  (int) (Math.random() * 10000000));
                db.buyableItemDao().add(newItem);
                buyableItems.add(0, newItem);
                buyableItemAdapter.notifyItemInserted(0);
                recyclerView.getLayoutManager().scrollToPosition(0);

                // show the results
                System.out.println("it was clicked!");
                MainActivity.this.findViewById(R.id.results).setVisibility(View.VISIBLE);
            }
        });



        // Wishlist
        onlyWishlist = new Wishlist();
        Button wishlistButton = findViewById(R.id.wishlistButton);
        wishlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View event) {
                Intent goToWishListIntent = new Intent(MainActivity.this, WishlistActivity.class);
                goToWishListIntent.putExtra("wishlist", onlyWishlist.getName());
                MainActivity.this.startActivity(goToWishListIntent);
            }
        });
    }

    public void goToSettingsActivity(View v) {
        Intent i = new Intent(this, SettingsActivity.class);
        this.startActivity(i);
    }

    @Override
    public void potato(BuyableItem item) {
        Intent goToBuyActivityIntent = new Intent(this, BuyItem.class);

        // add some extra info about exactly what thing is being purchased
        goToBuyActivityIntent.putExtra("item", item.getTitle());
        MainActivity.this.startActivity(goToBuyActivityIntent);
    }
}
