package luis.mvi.uzumbank.presentation.screens.main.transfer.cardtransfer.checktransfer.verifytransfer

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import luis.mvi.uzumbank.R
import luis.mvi.uzumbank.databinding.ScreenTransferVerifyBinding
import java.util.Locale

/*
created by Xo'jaakbar on 22.11.2023 at 22:37
*/

@AndroidEntryPoint
class TransferVerifyScreen:Fragment(R.layout.screen_transfer_verify) {
    val binding: ScreenTransferVerifyBinding by viewBinding(ScreenTransferVerifyBinding::bind)
    private val viewModel: TransferVerifyViewModel by viewModels<TransferVerifyViewModelImpl>()
    private var countDownTimer: CountDownTimer? = null
    private val MAX_TIME: Long = 60000
    private var mTimeLeftInMillis = MAX_TIME
    lateinit var textCountDownTimer: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.toastLiveData.observe(viewLifecycleOwner,toastObserver)
        binding.btnBack.setOnClickListener { viewModel.back() }
        binding.edtCode.doAfterTextChanged {
            if (binding.edtCode.text.toString().length==6){
                viewModel.transferVerified(binding.edtCode.text.toString())
            }
        }
        viewModel.notificationCodeLiveData.observe(viewLifecycleOwner,makeNotificationObserver)
        viewModel.makeNotification()
        textCountDownTimer = view.findViewById(R.id.count_down_timer)
    }

    private val toastObserver = Observer<String>{
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private val makeNotificationObserver = Observer<String>{
        makeNotification(it)
    }

    private fun makeNotification(code:String){
        lifecycleScope.launch {
            delay(2000)
            binding.edtCode.text = Editable.Factory.getInstance().newEditable(code)
        }
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Code"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel("TransferVerify", name, importance)
            mChannel.description = "transfer"
            val notificationManager = ContextCompat.getSystemService(
                requireContext(),
                NotificationManager::class.java
            ) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)

            val builder = NotificationCompat.Builder(requireContext(),"Code")
                .setSmallIcon(R.drawable.uzum_logo)
                .setContentTitle("Uzumbank")
                .setContentText("Код подтверждения: $code")
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
        }*/
        startTimer()
    }

    private val myPermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {  }

    private fun startTimer() {

        countDownTimer?.cancel()

        countDownTimer = object : CountDownTimer(mTimeLeftInMillis, 1000) {
            override fun onTick(l: Long) {
                mTimeLeftInMillis = l
                updateCountDownText()
            }

            override fun onFinish() {
                mTimeLeftInMillis = MAX_TIME
                FancyToast.makeText(
                    requireContext(),
                    "Не удалось выполнить перевод",
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
            textCountDownTimer.text = "Отправить повторно $format"
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
        countDownTimer = null
    }

}