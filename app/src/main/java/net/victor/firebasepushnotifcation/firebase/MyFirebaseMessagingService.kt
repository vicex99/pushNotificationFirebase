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
     * when the token was installed the first time
     */
    override fun onNewToken(token: String?) {
        Log.d(tag, "recive new: $token")

    }

    /**
     * when the token was installed the following times
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(tag, "recive old: ${remoteMessage.from}")

        remoteMessage.let {
            showNotification(remoteMessage.notification?.title, remoteMessage.notification?.body)
        }
    }

    /**
     * showNotification muestra en una notificacion emergente los datos que le pasan por parametro
     *
     * @title - title message
     * @body - context text message
     */
    private fun showNotification(title: String?, body: String?){

        // create one transition in the app
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        // set the transition when the user click in the notfication
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        // get default notification sound
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        /*
        create notification

        use title, body, sound, intent
         */
        val notificationBuilder = NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(body)
            .setSound(soundUri)
            .setContentIntent(pendingIntent)

        // set when the notification is created
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }
}