package luis.mvi.uzumbank.presentation.screens.signup

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import luis.mvi.uzumbank.R
import luis.mvi.uzumbank.databinding.ScreenSignupBinding

/*
created by Xo'jaakbar on 30.07.2023 at 14:05
*/

@AndroidEntryPoint
class SignUpScreen: Fragment(R.layout.screen_signup) {
    private val binding by viewBinding(ScreenSignupBinding::bind)
    private val viewModel: SignUpViewModel by viewModels<SignUpViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.toastLiveData.observe(viewLifecycleOwner,toastLiveData)
        viewModel.progressLiveData.observe(viewLifecycleOwner,progressObserver)

        binding.btnSignIn.setOnClickListener { viewModel.goToSignIn() }

        requireActivity().window.statusBarColor = Color.WHITE
        setStatusBarItemsColor()
        binding.btnContinue.setOnClickListener {
            viewModel.signUp(
                firstName = binding.firstName.text.toString(),
                lastName = binding.lastName.text.toString(),
                phone = "+998"+binding.phoneNumber.text.toString(),
                password = binding.password.text.toString(),
                confirmPassword = binding.confirmPassword.text.toString()
            )
        }
    }

    private fun setStatusBarItemsColor() {
        val window = requireActivity().window
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.apply {
            isAppearanceLightStatusBars = true
            isAppearanceLightNavigationBars = true
        }
    }

    private val toastLiveData = Observer<String>{
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private val progressObserver = Observer<Boolean> {
        if (it){
            binding.progressbar.visibility = View.VISIBLE
            binding.progressbar2.visibility = View.VISIBLE
            binding.progressbar3.visibility = View.VISIBLE
            binding.progressbar4.visibility = View.VISIBLE
        } else{
            binding.progressbar.visibility = View.GONE
            binding.progressbar2.visibility = View.GONE
            binding.progressbar3.visibility = View.GONE
            binding.progressbar4.visibility = View.GONE
        }
    }
}