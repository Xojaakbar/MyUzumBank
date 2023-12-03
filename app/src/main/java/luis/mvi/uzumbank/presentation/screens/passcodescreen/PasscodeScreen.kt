package luis.mvi.uzumbank.presentation.screens.passcodescreen

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import luis.mvi.uzumbank.R
import luis.mvi.uzumbank.databinding.ScreenPasscodeBinding
import java.util.concurrent.Executor

/*
created by Xo'jaakbar on 01.10.2023 at 16:54
*/

@AndroidEntryPoint
class PasscodeScreen:Fragment(R.layout.screen_passcode) {

     private val binding by viewBinding(ScreenPasscodeBinding::bind)
     private val viewModel:PasscodeFragViewModel by viewModels<PasscodeFragViewModelImpl>()
     private val list = ArrayList<String>()
     private var dialog: Dialog? = null
     private var counter = 0

     private lateinit var executor: Executor
     private lateinit var biometricPrompt: BiometricPrompt
     private lateinit var promptInfo: BiometricPrompt.PromptInfo

     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          val callback = object : OnBackPressedCallback(true /* enabled by default */) {
               override fun handleOnBackPressed() {} }
          requireActivity().onBackPressedDispatcher.addCallback(this, callback)
     }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
          super.onViewCreated(view, savedInstanceState)
          requireActivity().window.statusBarColor = Color.WHITE
          setStatusBarItemsColor()
          listeners()
          counter=0
          initBottomSheetDialog()
          list.clear()
          viewModel.numbers(counter)
          viewModel.toastLiveData.observe(viewLifecycleOwner,incorrectObserver)
          viewModel.whatNumberLiveData.observe(viewLifecycleOwner,whatNumberObserver)
          viewModel.quitLiveData.observe(viewLifecycleOwner,quiteObserver)
          viewModel.showDialogLiveData.observe(viewLifecycleOwner,exitDialogObserver)
          viewModel.closeScreenLiveData.observe(viewLifecycleOwner,closeScreenObserver)

          executor = ContextCompat.getMainExecutor(requireContext())
          biometricPrompt = BiometricPrompt(this, executor,
               object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                         super.onAuthenticationSucceeded(result)
                         viewModel.biometricScan(true)
                    }
               })

          promptInfo = BiometricPrompt.PromptInfo.Builder()
               .setTitle("Запрос на использование биометрии")
               .setNegativeButtonText("Отмена")
               .setSubtitle("Используйте биометрические данные")
               .build()

          binding.fingerPrint.setOnClickListener {
               if (biometricsAvailable()) {
                    biometricPrompt.authenticate(promptInfo)
               }
          }

          if (biometricsAvailable()) {
               biometricPrompt.authenticate(promptInfo)
          }
     }

     private fun biometricsAvailable(): Boolean {
          val biometricManager = BiometricManager.from(requireContext())
          return when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)) {
               BiometricManager.BIOMETRIC_SUCCESS -> {
                    true
               }
               BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                    false
               }
               BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                    false
               }
               else -> false
          }
     }

     private val closeScreenObserver = Observer<Unit> {
          findNavController().popBackStack()
     }

     private val incorrectObserver = Observer<String>{

          val vibrator = requireActivity().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

          // Проверка на доступность вибрации
          if (vibrator.hasVibrator()) {
               // Вибрация на 500 миллисекунд
               vibrator.vibrate(700)
          }

          Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
          binding.first.isEnabled = true
          binding.second.isEnabled = true
          binding.third.isEnabled = true
          binding.fourth.isEnabled = true
          counter = 0
          list.clear()
     }

     private val quiteObserver = Observer<Unit> { requireActivity().finishAffinity() }

     private val whatNumberObserver = Observer<Int>{
          when(it){
               0-> {
                    binding.first.isEnabled  = true
                    binding.second.isEnabled = true
                    binding.third.isEnabled  = true
                    binding.fourth.isEnabled = true }

               1-> {
                    binding.first.isEnabled  = false
                    binding.second.isEnabled = true
                    binding.third.isEnabled  = true
                    binding.fourth.isEnabled = true }

               2-> {
                    binding.first.isEnabled  = false
                    binding.second.isEnabled = false
                    binding.third.isEnabled  = true
                    binding.fourth.isEnabled = true }

               3-> {
                    binding.first.isEnabled  = false
                    binding.second.isEnabled = false
                    binding.third.isEnabled  = false
                    binding.fourth.isEnabled = true
               }

               4-> {
                    binding.first.isEnabled  = false
                    binding.second.isEnabled = false
                    binding.third.isEnabled  = false
                    binding.fourth.isEnabled = false
                    viewModel.isCorrectPassword(list) }
          }
     }

     private fun enter(num:String){
          if (counter<4) {
               list.add(num)
               counter++
               viewModel.numbers(counter)
          }
     }

     private fun clear(){
          if (counter>0 && list.isNotEmpty()){
               counter--
               list.removeAt(counter)
               viewModel.numbers(counter)
          }
     }

     private fun listeners(){
          binding.number1.setOnClickListener { enter("1") }
          binding.number2.setOnClickListener { enter("2") }
          binding.number3.setOnClickListener { enter("3") }
          binding.number4.setOnClickListener { enter("4") }
          binding.number5.setOnClickListener { enter("5") }
          binding.number6.setOnClickListener { enter("6") }
          binding.number7.setOnClickListener { enter("7") }
          binding.number8.setOnClickListener { enter("8") }
          binding.number9.setOnClickListener { enter("9") }
          binding.zero.setOnClickListener    { enter("0") }
          binding.clear.setOnClickListener   { clear() }
          binding.fingerPrint.setOnClickListener {  }
          binding.quit.setOnClickListener    { viewModel.quite() }
          binding.exitAccount.setOnClickListener { viewModel.dialog() }
     }

     private fun setStatusBarItemsColor() {
          val window = requireActivity().window
          val insetsController = WindowCompat.getInsetsController(window, window.decorView)
          insetsController.apply {
               isAppearanceLightStatusBars = true
               isAppearanceLightNavigationBars = true
          }
     }

     private val exitDialogObserver = Observer<Unit> {
               dialog!!.show()
     }

     private fun initBottomSheetDialog() {
          dialog = Dialog(requireContext())
          dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
          dialog!!.setContentView(R.layout.bottomsheet_dialog_exit)
          dialog!!.window!!.setLayout(
               ViewGroup.LayoutParams.MATCH_PARENT,
               ViewGroup.LayoutParams.WRAP_CONTENT
          )
          dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
          dialog!!.window!!.setGravity(Gravity.BOTTOM)
          dialog!!.window!!.attributes.windowAnimations = R.style.DialogAnimation
          dialog!!.findViewById<TextView>(R.id.btn_cancel).setOnClickListener {
               dialog!!.hide()
          }
          dialog!!.findViewById<TextView>(R.id.btn_exit).setOnClickListener {
               viewModel.exitAccount()
               dialog!!.hide()
          }
     }
}

var isFirstTime = true