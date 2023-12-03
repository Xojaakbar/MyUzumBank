package luis.mvi.uzumbank.presentation.screens.main.home.profile

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import luis.mvi.uzumbank.R
import luis.mvi.uzumbank.data.local.MenuCategoryData
import luis.mvi.uzumbank.databinding.ScreenProfileBinding
import luis.mvi.uzumbank.domain.models.ProfileResponseParam
import luis.mvi.uzumbank.presentation.adapters.ProfileAdapter

@AndroidEntryPoint
class ProfileScreen: Fragment(R.layout.screen_profile) {
    private val binding by viewBinding(ScreenProfileBinding::bind)
    private val viewModel: ProfileViewModel by viewModels<ProfileViewModelImpl>()
    private val adapter = ProfileAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.statusBarColor = Color.parseColor("#F5F6F8")
        setStatusBarItemsColor()
        viewModel.rvLiveData.observe(viewLifecycleOwner,recyclerItemsObserver)
        viewModel.profileInfoLiveData.observe(viewLifecycleOwner,profileInfoObserver)
        viewModel.toastLiveData.observe(viewLifecycleOwner,toastObserver)
        viewModel.getProfileInfo()
        binding.rvProfile.adapter = adapter
        binding.btnBack.setOnClickListener { viewModel.backToHome() }

    }

    private val recyclerItemsObserver = Observer<List<MenuCategoryData>>{
        adapter.submitList(it)
    }

    private val profileInfoObserver = Observer<ProfileResponseParam> {
      binding.txtName.text = it.first_name
    }

    fun setStatusBarItemsColor() {
        val window = requireActivity().window
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.apply {
            isAppearanceLightStatusBars = true
            isAppearanceLightNavigationBars = true
        }
    }
    private val toastObserver = Observer<String>{
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

}