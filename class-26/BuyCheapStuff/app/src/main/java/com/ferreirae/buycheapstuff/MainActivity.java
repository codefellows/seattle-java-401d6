package com.ferreirae.buycheapstuff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // gets called automatically when the MainActivity is created/shown for the first time
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // these will always be here, every time, thanks Android
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // maybe set up event listeners, or fetch data that you need to render the app

        // when the button is clicked, show the thing whose id is results

        // grab the button, using its ID and the generated R (resource) info
        Button button = findViewById(R.id.button);
        // add the event listener to the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View event) {
                // update text of the thing to be whatever was typed in

                // grab what was typed in
                EditText editText = findViewById(R.id.editText);
                String textThatWasTypedIn = editText.getText().toString();
                // set the text of the thing to be that text
                TextView textView = findViewById(R.id.itemTitle);
                textView.setText(textThatWasTypedIn);

                // show the results
                System.out.println("it was clicked!");
                MainActivity.this.findViewById(R.id.results).setVisibility(View.VISIBLE);
            }
        });

        // set up event listener for going to other activity
        Button buyActivityButton = findViewById(R.id.goToBuyActivityButton);
        buyActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View event) {
                // go to the other activity
                // Create the intent to go to that activity and start it!
//                Intent goToBuyActivityIntent = new Intent(MainActivity.this, BuyItem.class);
//                MainActivity.this.startActivity(goToBuyActivityIntent);

                Intent shareThingToBuyIntent = new Intent(Intent.ACTION_SEND);
                EditText editText = findViewById(R.id.editText);
                String textThatWasTypedIn = editText.getText().toString();
                shareThingToBuyIntent.putExtra(Intent.EXTRA_TEXT, textThatWasTypedIn);
                shareThingToBuyIntent.setType("text/plain");
                MainActivity.this.startActivity(Intent.createChooser(shareThingToBuyIntent, "buy with..."));

            }
        });
    }
}
