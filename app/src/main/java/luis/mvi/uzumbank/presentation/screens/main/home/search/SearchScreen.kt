package luis.mvi.uzumbank.presentation.screens.main.home.search

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import luis.mvi.uzumbank.R

/*
created by Xo'jaakbar on 11.11.2023 at 0:59
*/
@AndroidEntryPoint
class SearchScreen:Fragment(R.layout.screen_search) {
    private val viewModel: SearchViewModel by viewModels<SearchViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.statusBarColor = requireContext().getColor(R.color.default_qanaqadir)
        setStatusBarItemsColor()
        view.findViewById<ImageView>(R.id.btn_back_to_home).setOnClickListener { viewModel.back() }
    }

    private fun setStatusBarItemsColor() {
        val window = requireActivity().window
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.apply {
            isAppearanceLightStatusBars = true
            isAppearanceLightNavigationBars = true
        }
    }
}