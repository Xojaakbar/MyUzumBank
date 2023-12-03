package luis.mvi.uzumbank.presentation.screens.main.payment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import luis.mvi.uzumbank.R
import luis.mvi.uzumbank.data.local.CategoryData
import luis.mvi.uzumbank.data.local.FavouritePaysData
import luis.mvi.uzumbank.databinding.ScreenPaymentBinding
import luis.mvi.uzumbank.presentation.adapters.CategoriesAdapterGrid
import luis.mvi.uzumbank.presentation.adapters.CategoryAdapter
import luis.mvi.uzumbank.presentation.adapters.FavouritePaysAdapter
import javax.inject.Inject

@AndroidEntryPoint
class PaymentScreen : Fragment(R.layout.screen_payment) {
    val binding by viewBinding(ScreenPaymentBinding::bind)
    val  viewModel:PaymentViewModel by viewModels<PaymentViewModelImpl>()

    val categoryAdapter by lazy { CategoryAdapter() }
    val categoryGridAdapter by lazy { CategoriesAdapterGrid() }
    val favouritePaysAdapter by lazy { FavouritePaysAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.categoryListLiveData.observe(this,CategoryListLiveDataObserver)
        viewModel.favouriteListLiveData.observe(this,favouritePaysDataListLiveDataObserver)
        viewModel.rebuildLiveData.observe(this,rebuildLiveDataObserver)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBarItemsColor()
        binding.recyclerFavourite.adapter = favouritePaysAdapter
        binding.recyclerViewCategory.adapter = categoryAdapter
        requireActivity().window.statusBarColor = Color.WHITE
        binding.gridviewIcon.setOnClickListener {
           viewModel.rebuildCategoryList()
        }

    }

    val CategoryListLiveDataObserver = Observer<List<CategoryData>>{
        categoryAdapter.submitList(it)
        categoryGridAdapter.submitList(it)
    }

    val favouritePaysDataListLiveDataObserver = Observer<List<FavouritePaysData>>{
        favouritePaysAdapter.submitList(it)
    }

    val rebuildLiveDataObserver = Observer<Boolean> {
        if (it) {
            binding.recyclerViewCategory.layoutManager = GridLayoutManager(requireContext(),3)
            binding.recyclerViewCategory.adapter = categoryGridAdapter
            binding.gridviewIcon.setImageResource(R.drawable.list_icon)
        }
        else {
            binding.recyclerViewCategory.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerViewCategory.adapter = categoryAdapter
            binding.gridviewIcon.setImageResource(R.drawable.grid_view_icon)
        }
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