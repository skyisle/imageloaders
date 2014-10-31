package com.the42apps.imageloaders;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;

/**
 * Created by skyisle on 10/30/14.
 */
public class AppUtil {
  static void startAppInfoActivity(Activity activity) {
    Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.setData(Uri.parse("package:" + activity.getPackageName()));
    activity.startActivity(intent);
  }

  static void restartApp(FragmentActivity ctx) {
    PackageManager pm = ctx.getPackageManager();
    Intent intentForPackage = pm.getLaunchIntentForPackage(ctx.getPackageName());
    int pendingIntentId = 123456;
    PendingIntent pendingIntent = PendingIntent.getActivity(ctx, pendingIntentId, intentForPackage, PendingIntent.FLAG_CANCEL_CURRENT);
    AlarmManager mgr = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
    mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent);

    android.os.Process.killProcess(android.os.Process.myPid());
  }
}
