package com.example.sahelii;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.telephony.SmsManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.github.tbouron.shakedetector.library.ShakeDetector;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;


public class ServiceMine extends Service {

    boolean isRunning = false;

    FusedLocationProviderClient fusedLocationClient;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    SmsManager manager = SmsManager.getDefault();
    public  String myLocation;

    @Override
    public void onCreate() {
        super.onCreate();


        ShakeDetector.create(this, () -> {
            Log.d("shake", "device shaked");
            //if you want to play siren sound you can do it here
            //just create music player and play here
            //before playing sound please set volume to max

            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
            String number1 = sharedPreferences.getString("number1", "NONE");
            String number2 = sharedPreferences.getString("number2", "NONE");
            String number3 = sharedPreferences.getString("number3", "NONE");

            if ((!number1.equalsIgnoreCase("NONE"))&&(!number2.equalsIgnoreCase("NONE"))&&(!number3.equalsIgnoreCase("NONE"))) {
                manager.sendTextMessage(number1, null, "Im in Trouble!\nSending My Location :\n" + myLocation, null, null);
                manager.sendTextMessage(number2, null, "Im in Trouble!\nSending My Location :\n" + myLocation, null, null);
                manager.sendTextMessage(number3, null, "Im in Trouble!\nSending My Location :\n" + myLocation, null, null);
            }

        });

    }

    private LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            super.onLocationResult(locationResult);
            if (locationResult != null && locationResult.getLastLocation() != null) {
                double latitude = locationResult.getLastLocation().getLatitude();
                double longitude = locationResult.getLastLocation().getLongitude();
                Log.d("LOCATION UPDATE", latitude + " ," + longitude);
                            // Logic to handle location object
                            myLocation = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude;

            }
        }
    };

    private void startLocationService() {
        String channel_id = "location_notification_channel";
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent resultIntent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, resultIntent, PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channel_id);
        builder.setSmallIcon(R.drawable.girl_vector);
        builder.setContentTitle("Sahelii");
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setContentText("Shake Device to Send SOS");
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(false);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager != null && notificationManager.getNotificationChannel(channel_id) == null) {
                NotificationChannel notificationChannel = new NotificationChannel(channel_id, "Saheliii", NotificationManager.IMPORTANCE_HIGH);
                notificationChannel.setDescription("this channel is  used to fetch location ");
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        LocationRequest locationRequest = LocationRequest.create()
                .setInterval(4000)
                .setFastestInterval(2000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
        startForeground(1098,builder.build());
    }
    private void stopLocationService() {
        LocationServices.getFusedLocationProviderClient(this).removeLocationUpdates(locationCallback);
        stopForeground(true);
        stopSelf();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        if (intent.getAction().equalsIgnoreCase("STOP")) {
            if(isRunning) {
                stopLocationService();
            }
        } else {
            startLocationService();
        }

        return super.onStartCommand(intent,flags,startId);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
