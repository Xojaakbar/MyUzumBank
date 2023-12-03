package luis.mvi.uzumbank.presentation.screens.verifysignup

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Vibrator
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import luis.mvi.uzumbank.R
import luis.mvi.uzumbank.databinding.ScreenVerifyBinding
import luis.mvi.uzumbank.utils.SharedPref
import java.util.Locale
import javax.inject.Inject

/*
created by Xo'jaakbar on 06.11.2023 at 0:44
*/

@AndroidEntryPoint
class SignUpVerifyScreen:Fragment(R.layout.screen_verify) {
    private val binding by viewBinding(ScreenVerifyBinding::bind)
    private val viewModel: SignUpVerifyViewModel by viewModels<SignUpVerifyViewModelImpl>()
    private var countDownTimer: CountDownTimer? = null
    private val MAX_TIME: Long = 60000
    private var mTimeLeftInMillis = MAX_TIME
    private val scope = CoroutineScope(Dispatchers.Main)

    @Inject
    lateinit var sharedPref: SharedPref

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startTimer()
        listeners()
        viewModel.setCode()
        viewModel.toastLiveData.observe(viewLifecycleOwner,toastObserver)
        viewModel.progressLiveData.observe(viewLifecycleOwner,progressObserver)
        viewModel.incorrectLiveData.observe(viewLifecycleOwner,incorrectLiveDataObserver)
        viewModel.resendLiveData.observe(viewLifecycleOwner,resendObserver)
        viewModel.codeLiveData.observe(viewLifecycleOwner,codeObserver)
    }

    private fun listeners(){
        binding.edtCode.doAfterTextChanged {
            if (it?.length==6) { viewModel.verify(it.toString()) }
        }
        binding.btnBack.setOnClickListener { viewModel.back() }
    }

   /* private fun makeNotification(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Code"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val mChannel = NotificationChannel("Verify", name, importance)
        mChannel.description = "notify verify"
        val notificationManager = getSystemService(requireContext(),NotificationManager::class.java) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)

            val builder = NotificationCompat.Builder(requireContext(),"Verify")
                .setSmallIcon(R.drawable.uzum_logo)
                .setContentTitle("Verification code: ")
                .setContentText(sharedPref.code)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build()

            with(NotificationManagerCompat.from(requireContext())) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (ContextCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.POST_NOTIFICATIONS
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        myPermissionRequest.launch(Manifest.permission.POST_NOTIFICATIONS)

                    }else{
                        Toast.makeText(requireContext(), "Разрешите, чтобы мы могли отправить уведомление!", Toast.LENGTH_SHORT).show()
                    }
                }
                notify(importance, builder)
            }
        }

        startTimer()
    }*/

    private val resendObserver = Observer<Unit>{
//        makeNotification()
    }

    private val progressObserver = Observer<Boolean> {
        if (it){
            binding.progressbar.visibility = View.VISIBLE
            binding.progressbar2.visibility = View.VISIBLE
            binding.progressbar3.visibility = View.VISIBLE
            binding.progressbar4.visibility = View.VISIBLE
        }else{
            binding.progressbar.visibility = View.GONE
            binding.progressbar2.visibility = View.GONE
            binding.progressbar3.visibility = View.GONE
            binding.progressbar4.visibility = View.GONE
        }
    }

    private val codeObserver = Observer<String>{
        binding.edtCode.text = Editable.Factory.getInstance().newEditable(it)
    }

    private val incorrectLiveDataObserver = Observer<Unit> {
        val vibrator = requireActivity().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        // Проверка на доступность вибрации
        if (vibrator.hasVibrator()) {
            // Вибрация на 500 миллисекунд
            vibrator.vibrate(100)
        }

        binding.edtCode.text.clear()
        FancyToast.makeText(
            requireContext(),
            "Неверный КОД",
            FancyToast.LENGTH_SHORT,
            FancyToast.ERROR,
            true
        ).show()
    }

    private val toastObserver = Observer<String>{
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private val myPermissionRequest = registerForActivityResult(ActivityResultContracts.RequestPermission()) {  }

    private fun startTimer() {

        countDownTimer?.cancel()

        countDownTimer = object : CountDownTimer(mTimeLeftInMillis, 1000) {
            override fun onTick(l: Long) {
                mTimeLeftInMillis = l
                updateCountDownText()
            }

            override fun onFinish() {
                mTimeLeftInMillis = MAX_TIME
                viewModel.resend()
                FancyToast.makeText(
                    requireContext(),
                    "Время этого кода вышло, мы отправим вам другой код",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                ).show()
            }
        }.start()
    }

    private fun updateCountDownText() {
        val minutes: Int = (mTimeLeftInMillis / 1000).toInt() / 60
        val seconds: Int = mTimeLeftInMillis.toInt() / 1000 % 60
        val format = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        binding.countDownTimer.text = "Повторная отправка через $format сек"
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
        countDownTimer = null
    }

}
