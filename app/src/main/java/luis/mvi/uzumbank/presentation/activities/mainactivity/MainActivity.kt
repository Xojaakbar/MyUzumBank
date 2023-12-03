package luis.mvi.uzumbank.presentation.activities.mainactivity

import android.Manifest
import android.app.Dialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import luis.mvi.uzumbank.R
import luis.mvi.uzumbank.databinding.ActivityMainBinding
import luis.mvi.uzumbank.presentation.screens.signup.SignUpScreen
import luis.mvi.uzumbank.utils.navigator.AppNavigator
import luis.mvi.uzumbank.utils.navigator.NavigationHandler
import java.time.LocalDateTime
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    private var dialog: Dialog? = null
    private lateinit var navController: NavController

    @Inject
    lateinit var navigationHandler: NavigationHandler

    @Inject
    lateinit var navigator: AppNavigator

    @RequiresApi(Build.VERSION_CODES.O)
    private var resumeTime = LocalDateTime.now()

    @RequiresApi(Build.VERSION_CODES.O)
    private var pausedTime = LocalDateTime.now()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomSheetDialog()
        viewModel.connectionLiveData.observe(this, internetConnectionObserver)
        viewModel.passcodeLiveData.observe(this, passcodeObserver)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.findNavController()
        val bottomNavView = binding.bottomNavbarMain
        setupWithNavController(bottomNavView, navController)
        val navHostController = navHostFragment.navController

        navigationHandler
            .navigationBuffer
            .onEach {
                it(navHostController)
            }.launchIn(lifecycleScope)

        navHostFragment.navController.addOnDestinationChangedListener { controller, destionation, _ ->
            Log.d("destination_of_screen", "${destionation.id} \n${destionation.id}")

            binding.bottomNavbarMain.isVisible =
                controller.currentDestination?.id == R.id.homeScreen ||
                    controller.currentDestination?.id == R.id.paymentScreen ||
                    controller.currentDestination?.id == R.id.transferScreen ||
                    controller.currentDestination?.id == R.id.chatScreen ||
                    controller.currentDestination?.id == R.id.menuScreen
//
//            when (controller.currentDestination?.id) {
//                R.id.homeScreen -> {
//                    showBottomNav()
//                }
//
//                R.id.transferScreen -> {
//                    showBottomNav()
//                }
//
//                R.id.paymentScreen -> {
//                    showBottomNav()
//                }
//
//                R.id.chatScreen -> {
//                    showBottomNav()
//                }
//
//                R.id.menuScreen -> {
//                    showBottomNav()
//                }
//
//                else -> {
//                    hideBottomNav()
//                }
//            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onPause() {
        super.onPause()
        pausedTime = LocalDateTime.now()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        resumeTime = LocalDateTime.now()
        viewModel.passcodeActivation(resumeTime, pausedTime)
    }

    private fun initBottomSheetDialog() {
        dialog = Dialog(this@MainActivity)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setContentView(R.layout.bottomsheet_disconnect_internet)
        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.window!!.setGravity(Gravity.BOTTOM)
        dialog!!.window!!.attributes.windowAnimations = R.style.DialogAnimation
        dialog!!.findViewById<TextView>(R.id.hide_dialog).setOnClickListener {
            dialog!!.hide()
        }
    }

    private val internetConnectionObserver = Observer<Boolean> {
        if (it) {
            dialog?.hide()
        } else {
            dialog!!.show()
        }
    }

    private val passcodeObserver = Observer<Unit> {
        hideBottomNav()
        findNavController(R.id.fragmentContainerView).navigate(R.id.passcodeScreen2)
    }

    private fun showBottomNav() {
        binding.bottomNavbarMain.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding.bottomNavbarMain.visibility = View.GONE
    }

    private fun makeNotification(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Code"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel("Verify", name, importance)
            mChannel.description = "notify verify"
            val notificationManager = ContextCompat.getSystemService(
                this@MainActivity,
                NotificationManager::class.java
            ) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)

            with(NotificationManagerCompat.from(this@MainActivity)) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (ContextCompat.checkSelfPermission(
                            this@MainActivity,
                            Manifest.permission.POST_NOTIFICATIONS
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        myPermissionRequest.launch(Manifest.permission.POST_NOTIFICATIONS)

                    }else{
                        Toast.makeText(this@MainActivity, "Разрешите, чтобы мы могли отправить коды уведомлением!", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private val myPermissionRequest = registerForActivityResult(ActivityResultContracts.RequestPermission()) {  }

}