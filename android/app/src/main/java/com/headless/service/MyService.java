// package com.headless.service;
//
// import android.content.Intent;
// import android.os.Bundle;
// import com.facebook.react.HeadlessJsTaskService;
// import com.facebook.react.bridge.Arguments;
// import com.facebook.react.jstasks.HeadlessJsTaskConfig;
// import javax.annotation.Nullable;
//
// import android.app.Service;
// import android.content.IntentFilter;
//
// import android.content.BroadcastReceiver;
// import android.content.Context;
// import android.content.Intent;
//
// import com.headless.service.MyTaskService;
//
// import com.facebook.react.HeadlessJsTaskService;
// import android.telephony.TelephonyManager;
//
// import android.net.ConnectivityManager;
// import android.app.ActivityManager;
// import java.util.List;
// import android.net.NetworkInfo;
//
// import android.os.Bundle;
// import android.telephony.SmsMessage;
//
// import android.util.Log;
//
// import android.os.Binder;
// import android.os.IBinder;
//
// import android.content.BroadcastReceiver;
// import android.content.Context;
// import android.content.Intent;
//
// import com.headless.service.MyTaskService;
//
// import com.facebook.react.HeadlessJsTaskService;
// import android.telephony.TelephonyManager;
//
// import android.net.ConnectivityManager;
// import android.app.ActivityManager;
// import java.util.List;
// import java.util.TimerTask;
// import java.util.Timer;
// import java.util.logging.Handler;
//
// import android.net.NetworkInfo;
//
// import android.os.Bundle;
// import android.telephony.SmsMessage;
//
// import android.util.Log;
//
// import android.os.CountDownTimer;
//
//
// public class MyService extends Service {
//
//   @Nullable
//   @Override
//   public IBinder onBind(Intent intent) {
//       return null;
//   }
//
//   @Override
//   public int onStartCommand(Intent intent, int flags, int startId) {
//
//       TimerTask task = new TimerTask(){
//           @Override
//           public void run(){
//               Log.i("testing", "abcxyz");
//               new AttemptUpdate().execute();
//           }
//
//       };
//
//       private final Handler handler = new Handler();
//
//         private Runnable updateData = new Runnable(){
//             public void run(){
//                  //call the service here
//                  ////// set the interval time here
//                  handler.postDelayed(updateData,10000);
//             }
//         }
//
//       new CountDownTimer(1000,10000)
//       {
//           @Override
//           public void onTick(long millisUntilFinished) {
//               Log.i("tick", "tock");
//               sendBroadcast(new Intent("fromservice"));
//
//           }
//
//           @Override
//           public void onFinish() {
//
//           }
//       }.start();
//       return START_STICKY;
//   }
// }
