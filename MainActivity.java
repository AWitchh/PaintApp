package com.example.paint;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button part1,part2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        part1 = findViewById (R.id.Part1);
        part2 = findViewById (R.id.Part2);
        askPermission();

        part1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);


            }
        });
        part2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                startActivity(intent);



            }

        });

}

    private void askPermission() {
        Dexter.withContext(this)
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener () {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                    }
                }).check();
    }
}