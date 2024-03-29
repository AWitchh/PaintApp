package com.example.paint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.kyanogen.signatureview.SignatureView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity5 extends AppCompatActivity {

    int defaultColor;
    SignatureView signatureView;
    ImageButton imgEraser, imgColor, imgSave;
    SeekBar seekBar;
    ImageView bird;
    TextView txtPenSize;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId ()==android.R.id.home)
        {
            onBackPressed ();
        }
        return super.onOptionsItemSelected (item);
    }

    private static String filename;
    File path = new File (Environment.getExternalStorageDirectory ().getAbsolutePath () + "/Paint");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main5);
        getSupportActionBar().setTitle("Bird Drawing");

        signatureView = findViewById (R.id.signature_view);
        seekBar = findViewById (R.id.penSize);
        txtPenSize = findViewById (R.id.txtPenSize);
        imgColor = findViewById (R.id.btnColor);
        imgEraser = findViewById (R.id.btnEraser);
        imgSave = findViewById (R.id.btnSave);
        bird = findViewById (R.id.bird);


        SimpleDateFormat format = new SimpleDateFormat ("yyyyMMdd_HHmmss", Locale.getDefault ());
        String date = format.format (new Date ());
        filename = path + "/" + date + ".png";

        if (!path.exists ()) {
            path.mkdirs ();
        }
        defaultColor = ContextCompat.getColor (MainActivity5.this, R.color.black);

        seekBar.setOnSeekBarChangeListener (new SeekBar.OnSeekBarChangeListener () {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtPenSize.setText (i + "dp");
                signatureView.setPenSize (i);
                seekBar.setMax (50);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });
        imgColor.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                openColorPicker ();
            }
        });

        imgEraser.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                signatureView.clearCanvas ();
            }
        });

        imgSave.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (!signatureView.isBitmapEmpty ()) {
                    try {
                        saveImage ();
                    } catch (IOException e) {
                        e.printStackTrace ();
                        Toast.makeText (MainActivity5.this, "Couldn't Save Painting..!", Toast.LENGTH_SHORT).show ();

                    }
                }
            }
        });

    }

    private void saveImage() throws IOException {
        File file = new File (filename);
        Bitmap bitmap = signatureView.getSignatureBitmap ();
        ByteArrayOutputStream bos = new ByteArrayOutputStream ();
        bitmap.compress (Bitmap.CompressFormat.PNG, 0, bos);
        byte[] bitmapData = bos.toByteArray ();

        FileOutputStream fos = new FileOutputStream (file);
        fos.write (bitmapData);
        fos.flush ();
        fos.close ();
        Toast.makeText (this, "Painting Saved Successfully..!", Toast.LENGTH_SHORT).show ();
    }

    private void openColorPicker() {
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog (this, defaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener () {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultColor = color;
                signatureView.setPenColor (color);
            }
        });
        ambilWarnaDialog.show ();
    }

}