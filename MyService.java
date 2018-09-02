package src.unlocked;

import android.Manifest;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.telephony.SmsManager;
import android.widget.Toast;

public class MyService extends Service  implements SensorEventListener {
    SensorManager sm;
    Sensor proxSensor;
    float distance;
    private ConfigureUtils configureUtils;
    SmsManager smsManager=SmsManager.getDefault();
     String smsNumberToSend;
     String sms;
     String time;
     String km;
    long times;
    float kms;

    @Override
    public void onCreate() {
        super.onCreate();
        // REGISTER RECEIVER THAT HANDLES SCREEN ON AND SCREEN OFF LOGIC
        Toast.makeText(this, "Service Started ....", Toast.LENGTH_SHORT).show();
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new ScreenReceiver();
        registerReceiver(mReceiver, filter);
        configureUtils = new ConfigureUtils(this);
        configureUtils.open();
       /*
*/            }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Thread thread = new Thread(new MyThreadClass(startId));
        smsNumberToSend= configureUtils.fetchNumber();
        sms= configureUtils.fetchMessage();
        time=configureUtils.fetchTime();
        km=configureUtils.fetchDistance();
        times = Long.parseLong(time);
        kms=Float.parseFloat(km);
          /*smsNumberToSend = MainActivity.mMyAppsBundle.getString("key");
        sms = MainActivity.mMyAppsBundle.getString("key1");
        time = MainActivity.mMyAppsBundle.getString("key2");
        km = MainActivity.mMyAppsBundle.getString("key3");*/
        boolean screenOn = intent.getBooleanExtra("screen_state", false);
        thread.start();
       /* NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent bIntent = new Intent(MyService.this, MainActivity.class);
        PendingIntent pbIntent = PendingIntent.getActivity(MyService.this, 0 , bIntent, 0);
        NotificationCompat.Builder bBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Title")
                        .setContentText("Subtitle")
                        .setAutoCancel(true)
                        .setOngoing(true)
                        .setContentIntent(pbIntent);
        barNotif = bBuilder.build();
        this.startForeground(1, barNotif);
*/
        if(!screenOn) {
//your line
        } else {
            Toast.makeText(this, "Screen is ON ....", Toast.LENGTH_SHORT).show();
            WakefulBroadcastReceiver.completeWakefulIntent(intent);

            sm = (SensorManager) getSystemService(SENSOR_SERVICE);
            proxSensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            sm.registerListener(this, proxSensor, SensorManager.SENSOR_DELAY_UI);
            if (distance < 1) {
                Toast.makeText(this, "Welcome User", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Invalid User", Toast.LENGTH_SHORT).show();

                smsManager.sendTextMessage(smsNumberToSend, null, sms, null, null);
                LocationManager gps = (LocationManager)
                        getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //  public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return 3;
                }
                gps.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 60 * 60 * times, 1000*kms, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {

                        smsManager.sendTextMessage(smsNumberToSend, null, "Last Location is " +location.getLatitude() + "," + location.getLongitude(), null, null);
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {

                    }
                });
            }
        }

        return START_REDELIVER_INTENT;

    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Destroyed ....", Toast.LENGTH_LONG).show();

        sm.unregisterListener(this);
        configureUtils.close();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        distance = event.values[0];
        Toast.makeText(this, "Working...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    final class MyThreadClass implements Runnable {
        int service_id;

        MyThreadClass(int service_id) {
            this.service_id = service_id;
        }

        @Override
        public void run() {/*final Configure globalVariable = (Configure) getApplicationContext();
            smsNumberToSend = globalVariable.getsmsNumberToSend();
            sms=globalVariable.getsms();
            time=globalVariable.gettime();
            km=globalVariable.getkm();
            times = Long.parseLong(time);
            kms=Float.parseFloat(km);*/

        }

    }

}
