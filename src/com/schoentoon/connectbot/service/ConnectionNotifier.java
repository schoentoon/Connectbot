/*
 * ConnectBot: simple, powerful, open-source SSH client for Android
 * Copyright 2010 Kenny Root, Jeffrey Sharkey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.schoentoon.connectbot.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;

import com.schoentoon.connectbot.ConsoleActivity;
import com.schoentoon.connectbot.R;
import com.schoentoon.connectbot.bean.HostBean;
import com.schoentoon.connectbot.util.HostDatabase;

/**
 * @author Kenny Root
 *
 * Based on the concept from jasta's blog post.
 */
public class ConnectionNotifier {
	private static final int ONLINE_NOTIFICATION = 1;
	private static final int ACTIVITY_NOTIFICATION = 2;

	private static final ConnectionNotifier instance = new ConnectionNotifier();

	public static ConnectionNotifier getInstance() {
		return instance;
	}

	protected NotificationManager getNotificationManager(Context context) {
		return (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
	}

	protected NotificationCompat.Builder newNotification(Context context) {
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		builder.setSmallIcon(R.drawable.notification_icon);
		return builder;
	}

	protected Notification newActivityNotification(Context context, HostBean host) {
		NotificationCompat.Builder builder = newNotification(context);

		Resources res = context.getResources();

		String contentText = res.getString(
				R.string.notification_text, host.getNickname());

		Intent notificationIntent = new Intent(context, ConsoleActivity.class);
		notificationIntent.setAction("android.intent.action.VIEW");
		notificationIntent.setData(host.getUri());

		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				notificationIntent, 0);

		builder.setContentTitle(res.getString(R.string.app_name));
		builder.setContentIntent(contentIntent);
		builder.setContentText(contentText);
		builder.setTicker(contentText);
		builder.setAutoCancel(true);
		builder.setWhen(System.currentTimeMillis());

		int ledARGB = 0;
		if (HostDatabase.COLOR_RED.equals(host.getColor()))
			ledARGB = Color.RED;
		else if (HostDatabase.COLOR_GREEN.equals(host.getColor()))
			ledARGB = Color.GREEN;
		else if (HostDatabase.COLOR_BLUE.equals(host.getColor()))
			ledARGB = Color.BLUE;
		else
			ledARGB = Color.WHITE;
		builder.setLights(ledARGB, 300, 1000);

		return builder.getNotification();
	}

	protected Notification newRunningNotification(Context context) {
		NotificationCompat.Builder builder = newNotification(context);
		builder.setOngoing(true);

		builder.setContentIntent(PendingIntent.getActivity(context,
				ONLINE_NOTIFICATION,
				new Intent(context, ConsoleActivity.class), 0));

		Resources res = context.getResources();

		builder.setContentTitle(res.getString(R.string.app_name));
		builder.setContentText(res.getString(R.string.app_is_running));

		return builder.getNotification();
	}

	public void showActivityNotification(Service context, HostBean host) {
		getNotificationManager(context).notify(ACTIVITY_NOTIFICATION, newActivityNotification(context, host));
	}

	public void hideActivityNotification(Service context) {
		getNotificationManager(context).cancel(ACTIVITY_NOTIFICATION);
	}

	public void showRunningNotification(Service context) {
		context.startForeground(ONLINE_NOTIFICATION, newRunningNotification(context));
	}
	public void hideRunningNotification(Service context) {
		context.stopForeground(true);
	}
}
