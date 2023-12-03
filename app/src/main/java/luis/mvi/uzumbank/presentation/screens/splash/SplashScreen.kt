package luis.mvi.uzumbank.presentation.screens.splash

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import luis.mvi.uzumbank.R
import luis.mvi.uzumbank.databinding.ScreenSplashBinding

/*
created by Xo'jaakbar on 17.10.2023 at 23:28
*/

@AndroidEntryPoint
class SplashScreen: Fragment(R.layout.screen_splash) {
    private val binding by viewBinding(ScreenSplashBinding::bind)
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.openNavigateScreen()
        binding.img.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.zoom_out_fast))
    }
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
