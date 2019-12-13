package com.example.squares;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    View[][] tiles = new View[4][4];
    int dark, bright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources r = getResources();
        dark = r.getColor(R.color.dark);
        bright = r.getColor(R.color.bright);

        tiles[0][0] = findViewById(R.id.a0);
        tiles[0][1] = findViewById(R.id.a1);
        tiles[0][2] = findViewById(R.id.a2);
        tiles[0][3] = findViewById(R.id.a3);

        tiles[1][0] = findViewById(R.id.b0);
        tiles[1][1] = findViewById(R.id.b1);
        tiles[1][2] = findViewById(R.id.b2);
        tiles[1][3] = findViewById(R.id.b3);

        tiles[2][0] = findViewById(R.id.c0);
        tiles[2][1] = findViewById(R.id.c1);
        tiles[2][2] = findViewById(R.id.c2);
        tiles[2][3] = findViewById(R.id.c3);

        tiles[3][0] = findViewById(R.id.d0);
        tiles[3][1] = findViewById(R.id.d1);
        tiles[3][2] = findViewById(R.id.d2);
        tiles[3][3] = findViewById(R.id.d3);
        randFill();


    }

    public void changeColor(View v){
        ColorDrawable color = (ColorDrawable) v.getBackground();
        if (color.getColor() == dark)
            v.setBackgroundColor(bright);
        else v.setBackgroundColor(dark);


    }

    public void randFill() {
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++) {
                int rand = 0 + (int) (Math.random() * 10);
                if (rand % 2 == 0) {
                    tiles[i][j].setBackgroundColor(bright);
                }
                else {
                    tiles[i][j].setBackgroundColor(dark);
                }
            }
        }




    }

    public void onClick(View v){

        String[] tag = v.getTag().toString().split(",");
        int x = Integer.parseInt(tag[0]), y = Integer.parseInt(tag[1]);

        changeColor(v);
        for (int i = 0; i < 4; i++) {
            changeColor(tiles[x][i]);
            changeColor(tiles[i][y]);
        }

        boolean flag = true;
        ColorDrawable testColor = (ColorDrawable) tiles[0][0].getBackground(), currentColor;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                currentColor = (ColorDrawable) tiles[i][j].getBackground();
                if (currentColor.getColor() != testColor.getColor()) {
                    flag = false;
                    break;
                }
            }
        }

        if (flag == true){
            Toast end = Toast.makeText(this, "Поздравляем! Вы победили!", Toast.LENGTH_LONG);
            end.show();
            randFill();
        }

    }
}
