package com.example.ben.fallas;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

import java.util.Random;

public class MyActivity extends AppCompatActivity {
    private int usersOverallScore = 0;
    private int usersTurnScore = 0;
    private int computersOverallScore = 0;
    private int computersTurnScore = 0;
    boolean userTurn = true;
    boolean compTurn = false;

    Handler handler = new Handler(Looper.getMainLooper());

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void updateUserScore(View v) {

        TextView t = (TextView) findViewById(R.id.userScore);
        if(userTurn) {
            t.setText("Score: " + (usersOverallScore + usersTurnScore));
        }
        else {
            t.setText("Score: " + usersOverallScore);
        }
    }

    public void updateCompScore(View v) {
        TextView t = (TextView) findViewById(R.id.computerScore);
        if(compTurn) {
            t.setText("Computer Score: " + (computersOverallScore + computersTurnScore));
        }
        else {
            t.setText("Computer Score: " + computersOverallScore);
        }
        return;
    }

    public void updateScore(View v) {
        if(userTurn) {
            updateUserScore(v);
        }
        else {
            updateCompScore(v);
        }
    }
    public void hold(View v) {
        if(userTurn) {
            usersOverallScore += usersTurnScore;
            changePlayer(v);
        }
        else {
            computersOverallScore += computersTurnScore;
            changePlayer(v);
        }
    }

    public void reset(View v) {
        usersOverallScore = 0;
        computersOverallScore = 0;
        usersTurnScore = 0;
        computersTurnScore = 0;
        userTurn = true;
        compTurn = false;
        updateCompScore(v);
        updateUserScore(v);
        ImageView diceRolledImage = (ImageView) findViewById(R.id.diceImage);
        diceRolledImage.setImageResource(R.drawable.dice1);
    }

    public void changePlayer(View v) {
        Button r = (Button) findViewById(R.id.roll);
        Button h = (Button) findViewById(R.id.hold);
        Button rs = (Button) findViewById(R.id.reset);


        if(userTurn) {
            userTurn = false;
            compTurn = true;

            r.setEnabled(false);
            rs.setEnabled(false);
            h.setEnabled(false);
            compTurn(v);
            computersTurnScore = 0;

        }
        else {
            compTurn = false;
            userTurn = true;
            r.setEnabled(true);
            h.setEnabled(true);
            rs.setEnabled(true);
            usersTurnScore = 0;
        }
    }


    public void compTurn(final View v) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Random rand = new Random();
                int state = 0;
                //do {
                state = rand.nextInt(7) + 1;
                if (state >= 1 && state < 7) {
                    updateStatus(v, state);
                } else if (state == 7) {
                    hold(v);
                }
                if(state != 1 && state != 7) {
                    compTurn(v);
                }
                Log.w("myApp", "Computer rolled : " + state + "computer current Score: " + computersTurnScore + " computer overall score: " + computersOverallScore);
            }
        }, 1000);
    }

    public void updateStatus(View roll, int imageNum) {
        ImageView diceRolledImage = (ImageView) findViewById(R.id.diceImage);
        switch(imageNum) {
            case 1:
                diceRolledImage.setImageResource(R.drawable.dice1);
                if(userTurn) {
                    usersTurnScore = 0;
                    usersOverallScore = 0;

                }
                else {
                    computersTurnScore = 0;
                    computersOverallScore = 0;
                }
                //hold(roll);
                updateScore(roll);
                changePlayer(roll);
                break;
            case 2:
                diceRolledImage.setImageResource(R.drawable.dice2);

                if(userTurn) {
                    usersTurnScore += 2;
                }
                else {
                    computersTurnScore  +=2;
                }
                updateScore(roll);
                break;
            case 3:
                diceRolledImage.setImageResource(R.drawable.dice3);
                if(userTurn) {
                    usersTurnScore += 3;
                }
                else {
                    computersTurnScore += 3;
                }
                updateScore(roll);
                break;
            case 4:
                diceRolledImage.setImageResource(R.drawable.dice4);
                if(userTurn) {
                    usersTurnScore += 4;
                }
                else {
                    computersTurnScore += 4;
                }
                updateScore(roll);
                break;
            case 5:
                diceRolledImage.setImageResource(R.drawable.dice5);
                if(userTurn) {
                    usersTurnScore += 5;
                }
                else {
                    computersTurnScore += 5;
                }
                updateScore(roll);
                break;
            case 6:
                diceRolledImage.setImageResource(R.drawable.dice6);
                if(userTurn) {
                    usersTurnScore += 6;
                }
                else {
                    computersTurnScore += 6;
                }
                updateScore(roll);
                break;
        }
    }

    public void rollDice(View roll) {
        ImageView diceRolledImage = (ImageView) findViewById(R.id.diceImage);
        Random random = new Random();
        int imageNumber = random.nextInt(5) + 1;
        updateStatus(roll, imageNumber);

        Log.w("myApp: ", "Player tempScore: " + usersTurnScore + "Player overall Score: " + usersOverallScore);

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "My Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.ben.fallas/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "My Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.ben.fallas/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
