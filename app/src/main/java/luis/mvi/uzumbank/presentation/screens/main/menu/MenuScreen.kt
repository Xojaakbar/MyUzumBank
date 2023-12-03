package luis.mvi.uzumbank.presentation.screens.main.menu

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import luis.mvi.uzumbank.R
import luis.mvi.uzumbank.data.local.MenuCategoryData
import luis.mvi.uzumbank.databinding.ScreenMenuBinding
import luis.mvi.uzumbank.presentation.adapters.MenuAdapter

@AndroidEntryPoint
class MenuScreen : Fragment(R.layout.screen_menu) {
    private val binding by viewBinding(ScreenMenuBinding::bind)
    private val viewModel:MenuViewModel by viewModels<MenuViewModelImpl>()
    private val adapter = MenuAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.statusBarColor = Color.parseColor("#F5F6F8")
        setStatusBarItemsColor()
        viewModel.recyclerLiveData.observe(viewLifecycleOwner,adapterObserver)
        viewModel.initAdapter()
        binding.recyclerviewMenu.adapter = adapter
    }

    private val adapterObserver = Observer<List<MenuCategoryData>>{
        adapter.submitList(it)
    }

    fun setStatusBarItemsColor() {
        val window = requireActivity().window
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.apply {
            isAppearanceLightStatusBars = true
            isAppearanceLightNavigationBars = true
        }
    }
}