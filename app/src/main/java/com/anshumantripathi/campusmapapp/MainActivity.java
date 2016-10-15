package com.anshumantripathi.campusmapapp;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView campusImage = (ImageView) findViewById(R.id.campusImage);
        campusImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int action = event.getAction();
                int envX = (int) event.getX();
                int envY = (int) event.getY();
                ImageView imageView = (ImageView) v.findViewById(R.id.campusImage);

                switch (action) {
                    case MotionEvent.ACTION_UP:
                        int color = getHotspotColor(R.id.imageOverlay, envX, envY);
                        if(closeMatch(Color.RED,color,40)) {
                            Toast.makeText(MainActivity.this, "Engineering Building", Toast.LENGTH_SHORT).show();
                        }else if(closeMatch(Color.GREEN,color,40)){
                            Toast.makeText(MainActivity.this,"Student Union Building",Toast.LENGTH_SHORT).show();
                        }else if(closeMatch(Color.YELLOW,color,40)){
                            Toast.makeText(MainActivity.this,"Library Building",Toast.LENGTH_SHORT).show();
                        }
                        imageView.setImageResource(R.drawable.campus_image);
                        imageView.setTag(R.drawable.campus_image);
                        break;
                }
                return true;
            }
        });

    }

    public int getHotspotColor(int hotspotId, int x, int y) {
        ImageView img = (ImageView) findViewById(hotspotId);
        img.setDrawingCacheEnabled(true);
        Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
        img.setDrawingCacheEnabled(false);
        return hotspots.getPixel(x, y);
    }

    public boolean closeMatch(int color1, int color2, int tolerance) {
        System.out.println((int) Math.abs (Color.red (color1) - Color.red (color2)));
        if ((int) Math.abs (Color.red (color1) - Color.red (color2)) > tolerance )
            return false;
        System.out.println((int) Math.abs(Color.green(color1) - Color.green(color2)));
        if ((int) Math.abs(Color.green(color1) - Color.green(color2)) > tolerance)
            return false;
        if ((int) Math.abs (Color.blue (color1) - Color.blue (color2)) > tolerance )
            return false;
        return true;
    }
}
