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

import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;

import com.headless.MainActivity;

public class MyReceiver extends BroadcastReceiver {

    public static final String SMS_BUNDLE = "pdus";

    public void onReceive(Context context, Intent intent) {
        Bundle intentExtras = intent.getExtras();
        SmsMessage [] messages = null;
        String strMessage = "";


        Log.w("ABCCC", "DEFFGGG");

            if (intentExtras != null) {
              Object [] pdus = (Object[]) intentExtras.get("pdus");
              messages = new SmsMessage[pdus.length];

              for (int i = 0; i < messages.length; i++) {
                  messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                  strMessage += "SMS From: " + messages[i].getOriginatingAddress();
                  strMessage += " : ";
                  strMessage += messages[i].getMessageBody();
                  strMessage += "\n";
              }

              Log.w("WHY", "THOOOOOOOOO");

              Toast.makeText(context, "Message Received!", Toast.LENGTH_SHORT).show();

              if (MainActivity.active) {
//                  MainActivity inst = MainActivity.instance();
//                  inst.updateInbox(strMessage);
              } else {
                  Intent i = new Intent(context, MyTaskService.class);
                  i.putExtra("hasInternet", "fucking idk");
                  // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                  context.getApplicationContext().startService(i);
                  // context.startService(i);
                  HeadlessJsTaskService.acquireWakeLockNow(context);
              }
            }
    }
}
