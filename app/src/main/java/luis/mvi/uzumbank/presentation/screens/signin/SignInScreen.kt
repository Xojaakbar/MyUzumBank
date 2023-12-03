package luis.mvi.uzumbank.presentation.screens.signin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import luis.mvi.uzumbank.R
import luis.mvi.uzumbank.databinding.ScreenSignInBinding


/*
created by Xo'jaakbar on 22.09.2023 at 4:23
*/

@AndroidEntryPoint
class SignInScreen: Fragment(R.layout.screen_sign_in) {
    private val viewModel:SignInViewModel by viewModels<SignInViewModelImpl>()
    private val binding by viewBinding(ScreenSignInBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.showToastLiveData.observe(viewLifecycleOwner,showToastObserver)
        viewModel.progressLiveData.observe(viewLifecycleOwner,progressObserver)
        binding.btnContinue.setOnClickListener {
            val phone = "+998"+binding.editNumber.text.toString()
            val password = binding.editPassword.text.toString()
            Log.d("phone", "onViewCreated: $phone")
            viewModel.signIn(phone,password)
        }
        binding.btnSignUp.setOnClickListener { viewModel.goToSignUp() }
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

    private val showToastObserver = Observer<String>{
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
}