package net.victor.firebasepushnotifcation.firebase

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import net.victor.firebasepushnotifcation.MainActivity
import net.victor.firebasepushnotifcation.R

class MyFirebaseMessagingService: FirebaseMessagingService() {
    val tag = "FirebaseMesage"

    /**
     * cuando se instala por primera vez te manda este
     */
    override fun onNewToken(token: String?) {
        Log.d(tag, "recive new: $token")

//        sendRegistrationToServer(token);
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(tag, "recive old: ${remoteMessage.from}")

        remoteMessage.let {
            showNotification(remoteMessage.notification?.title, remoteMessage.notification?.body)
        }
    }

    private fun showNotification(title: String?, body: String?){

        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(body)
            .setSound(soundUri)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }
}