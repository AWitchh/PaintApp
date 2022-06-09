package com.example.paint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity2 extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId ()==android.R.id.home)
        {
            onBackPressed ();
        }
        return super.onOptionsItemSelected (item);
    }

    ImageButton tree,bird;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main2);
        getSupportActionBar().setTitle("Let's Draw");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tree = findViewById (R.id.tree);
        bird = findViewById (R.id.bird);

        tree.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this,MainActivity4.class);
                startActivity(intent);
            }
        });
        bird.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity2.this,MainActivity5.class);
                    startActivity(intent);
                }


        });
    }
}