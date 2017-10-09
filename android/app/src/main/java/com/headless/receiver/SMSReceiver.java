package com.headless.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.headless.service.MyTaskService;

import com.facebook.react.HeadlessJsTaskService;
import android.telephony.TelephonyManager;

import android.net.ConnectivityManager;
import android.app.ActivityManager;
import java.util.List;
import android.net.NetworkInfo;

import android.os.Bundle;
import android.telephony.SmsMessage;

import android.util.Log;


public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        /**
          This part will be called everytime network connection is changed
          e.g. Connected -> Not Connected
        **/

        Log.i("trevorTag", "does this even happen");

        if (!isAppOnForeground((context))) {
            /**
              We will start our service and send extra info about
              network connections
            **/
            boolean hasInternet = isNetworkAvailable(context);
            Intent serviceIntent = new Intent(context, MyTaskService.class);

            serviceIntent.putExtra("hasInternet", hasInternet);
            Bundle myBundle = intent.getExtras();
            SmsMessage [] messages = null;
            String strMessage = "";

            if (myBundle != null)
            {
                Object [] pdus = (Object[]) myBundle.get("pdus");
                messages = new SmsMessage[pdus.length];

                for (int i = 0; i < messages.length; i++)
                {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    strMessage += "SMS From: " + messages[i].getOriginatingAddress();
                    strMessage += " : ";
                    strMessage += messages[i].getMessageBody();
                    strMessage += "\n";
                }

                serviceIntent.putExtra("message", strMessage);
                // Toast.makeText(context, strMessage, Toast.LENGTH_SHORT).show();
            }


            // HeadlessJsTaskService.acquireWakeLockNow(context);

            context.startService(serviceIntent);
            HeadlessJsTaskService.acquireWakeLockNow(context);
        }
    }

    private boolean isAppOnForeground(Context context) {
        /**
          We need to check if app is in foreground otherwise the app will crash.
         http://stackoverflow.com/questions/8489993/check-android-application-is-in-foreground-or-not
        **/
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses =
        activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        final String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance ==
            ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND &&
             appProcess.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
        context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }


}
