package com.example.lionandtiger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private GridLayout gridLayout;

    enum Player{
        ONE ,
        TWO,
        NO
    }
    Player CurrentPlayer=Player.ONE;
    //Player winners=Player.NO;
    Player[] playerChoice=new Player[9];
    private boolean gameOver=false;
    private Button resetBTN;

    int[][] winnersRow_colum={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* playerChoice[0]=Player.NO;
        playerChoice[1]=Player.NO;
        playerChoice[2]=Player.NO;
        playerChoice[3]=Player.NO;
        playerChoice[4]=Player.NO;
        playerChoice[5]=Player.NO;
        playerChoice[6]=Player.NO;
        playerChoice[7]=Player.NO;
        playerChoice[8]=Player.NO;*/

        setPlayerChoiceArray();


        resetBTN=(Button)findViewById(R.id.resetbtn);
        gridLayout=findViewById(R.id.gridlayout);

        resetBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGameAgain();
            }
        });
    }

    public void onImageTab(View view) {
        ImageView tappedImageView = (ImageView) view;
        int tiTag = Integer.parseInt(tappedImageView.getTag().toString());
        if (playerChoice[tiTag] == Player.NO && gameOver == false) {
            tappedImageView.setTranslationX(-2000);

            playerChoice[tiTag] = CurrentPlayer;
            if (CurrentPlayer == Player.ONE) {

                tappedImageView.setImageResource(R.drawable.tiger);
                CurrentPlayer = Player.TWO;

            } else if (CurrentPlayer == Player.TWO) {
                tappedImageView.setImageResource(R.drawable.lion);
                CurrentPlayer = Player.ONE;
            }
            tappedImageView.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(3000);

            //Toast.makeText(this,tappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();

            for (int[] winnerColums : winnersRow_colum) {

                if (playerChoice[winnerColums[0]] == playerChoice[winnerColums[1]] &&
                        playerChoice[winnerColums[1]] == playerChoice[winnerColums[2]] &&
                        playerChoice[winnerColums[0]] != Player.NO) {
                    gameOver=true;
                    resetBTN.setVisibility(View.VISIBLE);
                    String Winner = "";

                    if (CurrentPlayer == Player.ONE) {
                        Winner = "Player Two";
                    } else if (CurrentPlayer == Player.TWO) {
                        Winner = "Player One";
                    }
                    Toast.makeText(this, Winner +"   is Winner ", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }
    private void resetGameAgain()
    {
        for (int index=0;index < gridLayout.getChildCount();index++){
            ImageView imageView=(ImageView) gridLayout.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);
        }
        CurrentPlayer=Player.ONE;

       /* playerChoice[0]=Player.NO;
        playerChoice[1]=Player.NO;
        playerChoice[2]=Player.NO;
        playerChoice[3]=Player.NO;
        playerChoice[4]=Player.NO;
        playerChoice[5]=Player.NO;
        playerChoice[6]=Player.NO;
        playerChoice[7]=Player.NO;
        playerChoice[8]=Player.NO;*/
        setPlayerChoiceArray();

        gameOver=false;
        resetBTN.setVisibility(View.INVISIBLE);

    }
    public void setPlayerChoiceArray()
    {
        for(int index=0 ; index < playerChoice.length ; index++){

            playerChoice[index]=Player.NO;
        }
    }

}
