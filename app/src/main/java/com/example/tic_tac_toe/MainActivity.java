package com.example.tic_tac_toe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // 0: yellow, 1: red, 2: empty
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int activePlayer = 0;
    boolean gameActive = true;
    //int won=0;/////
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }
            else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(1440).setDuration(300);
            for (int[] winningPosition : winningPositions) {
                int won=0;

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2)
                {
                    // Somone has won!
                        gameActive = false;
                        String winner = "";
                        if (activePlayer == 1) {
                            winner = "Yellow";
                        }
                        else {
                            winner = "Red";
                        }
                        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                        winnerTextView.setText(winner + " has won!");
                        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
                        layout.setVisibility(view.VISIBLE);
                         System.out.println("++++++++++++++++"+won);
                }
                else   {//draw condition
                    int cnt=0;
                    for (int i=0;i<9;i++)
                     {
                        if (gameState[i]!=2)
                          {cnt++;}
                     }
                    System.out.println("-------------cnt "+cnt+" won "+won);
                    if (cnt==9 ){
                        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                        winnerTextView.setText(" Draw!");
                        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
                        layout.setVisibility(view.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view) {
        gameActive=true;
        LinearLayout layout=(LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(view.INVISIBLE);
        activePlayer=0;
        //gameState={2,2,2,2,2,2,2,2,};//2 = unplayed
        for (int i=0; i<gameState.length; i++)
         {gameState[i] = 2;}
        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i=0;i<gridLayout.getChildCount();i++){

            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}