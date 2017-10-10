package com.headless.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.headless.service.MyTaskService;
import com.facebook.react.HeadlessJsTaskService;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;
import com.headless.MainActivity;

public class MyReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        Bundle intentExtras = intent.getExtras();
        SmsMessage [] messages = null;
        String strMessage = "";

        Log.w("onReceiveInvoked", "onReceiveInvoked");

            if (intentExtras != null) {
              Log.w("intentExtrasNotNull", "intentExtrasNotNull");

              Object [] pdus = (Object[]) intentExtras.get("pdus");
              messages = new SmsMessage[pdus.length];
              for (int i = 0; i < messages.length; i++) {
                  messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                  strMessage += "SMS From: " + messages[i].getOriginatingAddress();
                  strMessage += " : ";
                  strMessage += messages[i].getMessageBody();
                  strMessage += "\n";
              }

              Toast.makeText(context, "Message Received!", Toast.LENGTH_SHORT).show();

              // context.startService(i);
              // HeadlessJsTaskService.acquireWakeLockNow(context);

              if (!MainActivity.active) {
                  Intent i = new Intent(context, MyTaskService.class);
                  i.putExtra("SMS:", strMessage);
                  // context.getApplicationContext().startService(i);
                  context.startService(i);
                  HeadlessJsTaskService.acquireWakeLockNow(context);
              }
            } else {
              Log.w("intentExtrasISNull", "intentExtrasISNull");
            }
    }
}
