package com.example.vova.diarchyofthewalls.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.Button;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import com.example.vova.diarchyofthewalls.core.Entity;

import com.example.vova.diarchyofthewalls.R;
import com.example.vova.diarchyofthewalls.core.GameWorld;
import com.example.vova.diarchyofthewalls.core.Player;

public class GameActivity extends AppCompatActivity {

    public  int progress = 0;
    public  ProgressBar pbHorizontal;
    public  TextView tvProgressHorizontal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final GameWorld gw =(GameWorld)  findViewById(R.id.gameWorld);
        final Player p =gw.p;
        final Entity e =gw.e;

        e.x=100;
        pbHorizontal = (ProgressBar) findViewById(R.id.pb_horizontal);
        tvProgressHorizontal = (TextView) findViewById(R.id.tv_progress_horizontal);

        final Button Pause = (Button) findViewById(R.id.PauseBt);
        final ImageButton lef = (ImageButton) findViewById(R.id.LeftBt);
        final ImageButton rig = (ImageButton) findViewById(R.id.RightBt);
        final Button ite = (Button) findViewById(R.id.itemBt);
        final Button Eqi = (Button) findViewById(R.id.EqBt);
        final Button Chal = (Button) findViewById(R.id.ChangeLvlBt);
        final Button Chas = (Button) findViewById(R.id.ChangeBt);

        final Intent pau = new Intent(this, PauseActivity.class);
        final Intent item = new Intent(this, ItemActivity.class);
        final Intent battle = new Intent(this, BattleActivity.class);

        Chal.setEnabled(false);
        Chas.setEnabled(false);

        Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(pau);
            }
        });

        ite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(item);
            }
        });

        lef.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent Event) {
                switch (Event.getAction()) {
                    case MotionEvent.ACTION_BUTTON_PRESS:
                        p.x=p.x-p.speedX;
                        if (p.x==100)
                            Chal.setEnabled(true);
                        if (p.x==150)
                            Chas.setEnabled(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
        rig.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent Event) {
                switch (Event.getAction()) {
                    case MotionEvent.ACTION_BUTTON_PRESS:
                        p.x=p.x+p.speedX;
                        if (p.x==e.x)
                            startActivity(battle);
                        if (e.life)
                            e.death();
                        if (p.x==100)
                            Chal.setEnabled(true);
                        if (p.x==150)
                            Chas.setEnabled(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
    }

    public  void postProgress(int progress) {
        String strProgress = String.valueOf(progress) + " %";
        pbHorizontal.setProgress(progress);
        tvProgressHorizontal.setText("@string/Hunger" + strProgress);
    }
    
}
