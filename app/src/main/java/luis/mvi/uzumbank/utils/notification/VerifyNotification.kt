//package luis.mvi.uzumbank.utils.notification
//
//import android.Manifest
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.app.Service
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.os.Build
//import android.os.IBinder
//import androidx.core.app.NotificationCompat
//import androidx.core.app.NotificationManagerCompat
//import androidx.core.content.ContextCompat
//import dagger.hilt.android.qualifiers.ApplicationContext
//import luis.mvi.uzumbank.R
//import luis.mvi.uzumbank.utils.SharedPref
//import javax.inject.Inject
//
///*
//created by Xo'jaakbar on 06.11.2023 at 5:02
//*/
//
//class VerifyNotification @Inject constructor(
//    private val sharedPref: SharedPref,
//    @ApplicationContext appContext: ApplicationContext
//): Service() {
//
//    fun create(){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val name = "Code"
//            val descriptionText = sharedPref.code
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val mChannel = NotificationChannel("Verify", name, importance)
//            mChannel.description = descriptionText
//            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(mChannel)
//        }
//    }
//
//    override fun onBind(p0: Intent?): IBinder? = null
//
//    var builder = NotificationCompat.Builder(this, "Verify")
//        .setSmallIcon(R.drawable.bank)
//        .setContentTitle("Code")
//        .setContentText(sharedPref.code)
//        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//    fun makeNotification(){
//        with(NotificationManagerCompat.from(applicationContext)) {
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                if (ContextCompat.checkSelfPermission(
//                        applicationContext,
//                        Manifest.permission.POST_NOTIFICATIONS
//                    ) != PackageManager.PERMISSION_GRANTED
//                ) {
////                    myPermissionRequest.launch(Manifest.permission.POST_NOTIFICATIONS)
//                    return
//                }
//            }
//            notify(importance, builder.build())
//        }
//    }
////    private val myPermissionRequest =
////        registerForActivityResult(ActivityResultContracts.RequestPermission()) {  }
//}