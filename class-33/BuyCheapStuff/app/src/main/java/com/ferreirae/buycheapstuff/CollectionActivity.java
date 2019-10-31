package com.ferreirae.buycheapstuff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amazonaws.amplify.generated.graphql.CreateBuyableItemMutation;
import com.amazonaws.amplify.generated.graphql.CreateCollectionMutation;
import com.amazonaws.amplify.generated.graphql.ListBuyableItemsQuery;
import com.amazonaws.amplify.generated.graphql.ListCollectionsQuery;
import com.amazonaws.amplify.generated.graphql.UpdateCollectionMutation;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import javax.annotation.Nonnull;

import type.CreateBuyableItemInput;
import type.CreateCollectionInput;
import type.UpdateCollectionInput;

public class CollectionActivity extends AppCompatActivity {

    AWSAppSyncClient awsAppSyncClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        this.awsAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();


    }

    public void onAddButtonPressed(View v) {
        // Add a BuyableItem to the collection that we created
        // by creating a BuyableItem whose CollectionId is that collection's id
        awsAppSyncClient.query(ListCollectionsQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(new GraphQLCall.Callback<ListCollectionsQuery.Data>() {
                    @Override
                    public void onResponse(@Nonnull Response<ListCollectionsQuery.Data> response) {
                        CreateBuyableItemInput input = CreateBuyableItemInput.builder()
                                .buyableItemCollectionId(response.data().listCollections().items().get(0).id())
                                .title("cool new item in the fall collection")
                                .priceInCents(800000)
                                .build();
                        CreateBuyableItemMutation mutation = CreateBuyableItemMutation.builder().input(input).build();
                        awsAppSyncClient.mutate(mutation).enqueue(new GraphQLCall.Callback<CreateBuyableItemMutation.Data>() {
                            @Override
                            public void onResponse(@Nonnull Response<CreateBuyableItemMutation.Data> response) {
                                Log.i("ferreirae", "added it");
                            }

                            @Override
                            public void onFailure(@Nonnull ApolloException e) {

                            }
                        });
                    }

                    @Override
                    public void onFailure(@Nonnull ApolloException e) {

                    }
                });

        // Original code to create a new collection
//        CreateCollectionInput input = CreateCollectionInput.builder()
//                .title("Fall")
//                .build();
//        CreateCollectionMutation createCollectionMutation = CreateCollectionMutation.builder().input(input).build();
//        awsAppSyncClient.mutate(createCollectionMutation).enqueue(new GraphQLCall.Callback<CreateCollectionMutation.Data>() {
//            @Override
//            public void onResponse(@Nonnull Response<CreateCollectionMutation.Data> response) {
//                Log.i("ferreirae", "successful");
//            }
//
//            @Override
//            public void onFailure(@Nonnull ApolloException e) {
//                Log.i("ferreirae", "failure");
//            }
//        });
    }
}
