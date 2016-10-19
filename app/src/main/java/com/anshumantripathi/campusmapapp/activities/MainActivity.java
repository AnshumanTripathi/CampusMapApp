package com.anshumantripathi.campusmapapp.activities;


import android.content.Intent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.anshumantripathi.campusmapapp.util.LocationContext;
import com.anshumantripathi.campusmapapp.R;

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
                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                        ; //Assuming you use a RelativeLayout
                        ImageView iv = new ImageView(getApplicationContext());
                        lp.setMargins(envX, envY, 0, 0);
                        iv.setLayoutParams(lp);
//                        iv.setImageDrawable(getResources().getDrawable(R.drawable.pin));
                        Canvas canvas = new Canvas();
                        Bitmap pin = BitmapFactory.decodeResource(getResources(), R.drawable.pin);
                        canvas.drawBitmap(pin, envX, envY, null);
                        v.draw(canvas);
                        break;
                    case MotionEvent.ACTION_UP:
                        int color = getHotspotColor(R.id.imageOverlay, envX, envY);
                        if (closeMatch(Color.RED, color, 40)) {
                            Toast.makeText(MainActivity.this, "Engineering Building", Toast.LENGTH_SHORT).show();
                        } else if (closeMatch(Color.GREEN, color, 40)) {
                            Toast.makeText(MainActivity.this, "Student Union Building", Toast.LENGTH_SHORT).show();
                        } else if (closeMatch(Color.YELLOW, color, 40)) {
                            Toast.makeText(MainActivity.this, "Library Building", Toast.LENGTH_SHORT).show();
                        }
                        imageView.setImageResource(R.drawable.campus_image);
                        imageView.setTag(R.drawable.campus_image);

                        Intent in = new Intent(getApplicationContext(), BuildingDetailActivity.class);
                        startActivity(in);

                        break;
                }
                return true;
            }
        });

        Button locationButton = (Button) findViewById(R.id.location);
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LocationContext context = LocationContext.getInstance();
                context.displayGpsStatus();
                LocationManager manager = (LocationManager) MainActivity.this.getSystemService(Context.LOCATION_SERVICE);
                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {

                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                };

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
        System.out.println((int) Math.abs(Color.red(color1) - Color.red(color2)));
        if ((int) Math.abs(Color.red(color1) - Color.red(color2)) > tolerance)
            return false;
        System.out.println((int) Math.abs(Color.green(color1) - Color.green(color2)));
        if ((int) Math.abs(Color.green(color1) - Color.green(color2)) > tolerance)
            return false;
        if ((int) Math.abs(Color.blue(color1) - Color.blue(color2)) > tolerance)
            return false;
        return true;
    }
}

