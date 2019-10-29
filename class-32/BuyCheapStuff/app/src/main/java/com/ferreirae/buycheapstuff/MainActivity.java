package com.ferreirae.buycheapstuff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amazonaws.amplify.generated.graphql.CreateBuyableItemMutation;
import com.amazonaws.amplify.generated.graphql.ListBuyableItemsQuery;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nonnull;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import type.CreateBuyableItemInput;

public class MainActivity extends AppCompatActivity implements BuyableItemAdapter.OnBuyableItemInteractionListener {

    private String enteredItemName = null;
    private static final String TAG = "ferreirae.MainActivity";

    private AWSAppSyncClient awsAppSyncClient;

    private List<BuyableItem> buyableItems;

    private RecyclerView recyclerView;

    public void putDataOnPage(String data) {
        TextView headerTextView = findViewById(R.id.hiTextView);
        headerTextView.setText(data);
    }
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

       awsAppSyncClient = AWSAppSyncClient.builder()
               .context(getApplicationContext())
               .awsConfiguration(new AWSConfiguration(getApplicationContext()))
               .build();

//       runAddBuyableItemMutation();
        runBuyableItemQuery();
        this.buyableItems = new LinkedList<BuyableItem>();
//        buyableItems.add(new BuyableItem("gold paperclip", 1000000));
//        buyableItems.add(new BuyableItem("silver paperclip", 800000));
//        buyableItems.add(new BuyableItem("bronze paperclip", 600000));

        // render the buyable items to the screen, in the RecyclerView
        // https://developer.android.com/guide/topics/ui/layout/recyclerview
        recyclerView = findViewById(R.id.results);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BuyableItemAdapter(this.buyableItems, this));

        // when the button is clicked, show the thing whose id is results

        // grab the button, using its ID and the generated R (resource) info
        Button button = findViewById(R.id.button);
        // add the event listener to the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View event) {
                runBuyableItemQuery();

                // hide keyboard
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow((null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                // update text of the thing to be whatever was typed in

                // grab what was typed in
                EditText editText = findViewById(R.id.editText);
                enteredItemName = editText.getText().toString();
                // set the text of the thing to be our buyable items
//                TextView buyableItemsTextView = findViewById(R.id.itemTitle);
//                buyableItemsTextView.setText(MainActivity.this.buyableItems.toString());

                // show the results
                System.out.println("it was clicked!");
                MainActivity.this.findViewById(R.id.results).setVisibility(View.VISIBLE);
            }
        });

        // get data from the internet
        // https://square.github.io/okhttp/
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://taskmaster-api.herokuapp.com/tasks")
                .build();

        // callback: a function to specify what should happen after the request is done/the response is here
        client.newCall(request).enqueue(new LogDataWhenItComesBackCallback(this));


    }

    public void runAddBuyableItemMutation(){
        CreateBuyableItemInput createBuyableItemInput = CreateBuyableItemInput.builder()
                .title("Dog Sweater")
                .priceInCents((int) (Math.random() * 1000000000))
                .build();
        awsAppSyncClient.mutate(CreateBuyableItemMutation.builder().input(createBuyableItemInput).build())
                .enqueue(addItemMutationCallback);
    }

    private GraphQLCall.Callback<CreateBuyableItemMutation.Data> addItemMutationCallback = new GraphQLCall.Callback<CreateBuyableItemMutation.Data>() {
        @Override
        public void onResponse(@Nonnull com.apollographql.apollo.api.Response<CreateBuyableItemMutation.Data> response) {
            Log.i(TAG, "Logged some results");
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e(TAG, e.toString());
        }
    };

    public void runBuyableItemQuery(){
        awsAppSyncClient.query(ListBuyableItemsQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(getBuyableItemsCallback);
    }



    private GraphQLCall.Callback<ListBuyableItemsQuery.Data> getBuyableItemsCallback = new GraphQLCall.Callback<ListBuyableItemsQuery.Data>() {
        @Override
        public void onResponse(@Nonnull final com.apollographql.apollo.api.Response<ListBuyableItemsQuery.Data> response) {

            Handler handlerForMainThread = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message inputMessage) {
                    // grab data out of Message object and pass to actualMainActivityInstance
                    List<ListBuyableItemsQuery.Item> items = response.data().listBuyableItems().items();
                    Log.i(TAG, items.toString());
//            MainActivity.this.buyableItems = item
//            ListBuyableItemsQuery.Item item = items.get(0);
                    MainActivity.this.buyableItems = new LinkedList<BuyableItem>();
                    for(ListBuyableItemsQuery.Item item : items){
                        MainActivity.this.buyableItems.add(new BuyableItem(item));
                    }
                    MainActivity.this.recyclerView.getAdapter().notifyDataSetChanged();
                }
            };

            handlerForMainThread.obtainMessage().sendToTarget();

        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e(TAG, e.toString());
        }
    };


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

class LogDataWhenItComesBackCallback implements Callback {

    MainActivity actualMainActivityInstance;

    public LogDataWhenItComesBackCallback(MainActivity actualMainActivityInstance) {
        this.actualMainActivityInstance = actualMainActivityInstance;
    }
    private static final String TAG = "ferreirae.Callback";
    // OkHttp will call this if the request fails
    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        Log.e(TAG, "internet error");
        Log.e(TAG, e.getMessage());
    }

    // OkHttp will call this if the request succeeds
    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        String responseBody = response.body().string();
        Log.i(TAG, responseBody);
        //actualMainActivityInstance.putDataOnPage(responseBody);
        // defining a class that extends Handler with the curly braces!
        Handler handlerForMainThread = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message inputMessage) {
                // grab data out of Message object and pass to actualMainActivityInstance
                actualMainActivityInstance.putDataOnPage((String)inputMessage.obj);
            }
        };
        Message completeMessage =
                handlerForMainThread.obtainMessage(0, responseBody);
        completeMessage.sendToTarget();
    }
}
