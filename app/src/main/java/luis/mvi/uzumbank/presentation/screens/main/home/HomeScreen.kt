package luis.mvi.uzumbank.presentation.screens.main.home

import android.os.Bundle
import android.view.View
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import luis.mvi.uzumbank.R
import luis.mvi.uzumbank.data.local.FastDostupData
import luis.mvi.uzumbank.data.local.OplataNaMestaxData
import luis.mvi.uzumbank.databinding.ScreenHomeBinding
import luis.mvi.uzumbank.domain.models.CardListResponseParam
import luis.mvi.uzumbank.presentation.adapters.CardAdapter
import luis.mvi.uzumbank.presentation.adapters.FastDostupAdapter
import luis.mvi.uzumbank.presentation.adapters.OplataNaMestaxAdapter
import luis.mvi.uzumbank.presentation.screens.main.home.viewmodel.HomeViewModelImpl

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home) {
    val binding by viewBinding(ScreenHomeBinding::bind)
    val viewModel by viewModels<HomeViewModelImpl>()
    private val cardAdapter by lazy { CardAdapter() }
    private val fastDostupAdapter by lazy { FastDostupAdapter() }
    private val oplataNaMestaxAdapter by lazy { OplataNaMestaxAdapter() }
    private var balance: String = ""
    private var hasCard: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().window.statusBarColor = requireContext().getColor(R.color.uzum_theme)
        setStatusBarItemsColor()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cardsRecyclerview.adapter = cardAdapter
        binding.whiteInclude.recyclerBistriyDostup.adapter = fastDostupAdapter
        binding.whiteInclude.recyclerOplataNamestax.adapter = oplataNaMestaxAdapter

        viewModel.fastDostupLiveData.observe(viewLifecycleOwner, fastDoptupLiveDataObserver)
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressLiveDataObserver)
        viewModel.oplataNaMestaxLiveData.observe(viewLifecycleOwner, oplataLiveDataObserver)
        viewModel.cardsListLiveData.observe(viewLifecycleOwner, cardlistObserver)
        viewModel.showBalanceLiveData.observe(viewLifecycleOwner, showBalanceObserver)
        viewModel.getCardList()
        binding.imgProfile.setOnClickListener { viewModel.profile() }
        binding.swipedRefresh.setOnRefreshListener { viewModel.getCardList() }
        binding.floatingActionButton.setOnClickListener { viewModel.getCardList() }
        binding.visibilityOfCard.setOnClickListener { viewModel.showBalance() }
        binding.line1.setOnClickListener { viewModel.goToSearch() }
        binding.textSearch.setOnClickListener { viewModel.goToSearch() }
        viewModel.getFastList()
    }

    private val showBalanceObserver = Observer<Boolean> {
        showBalance = it
        if (it) {
            binding.balance.text = balance
            binding.uzs.visibility = View.VISIBLE
            binding.visibilityOfCard.setImageResource(R.drawable.invisible_icon)
        } else {
            binding.balance.text = "******"
            binding.uzs.visibility = View.GONE
            binding.visibilityOfCard.setImageResource(R.drawable.visibility_icon)
        }
    }

    private val fastDoptupLiveDataObserver = Observer<List<FastDostupData>> {
        fastDostupAdapter.submitList(it)
    }
    private val progressLiveDataObserver = Observer<Boolean> {
        binding.swipedRefresh.isRefreshing = it
    }

    private val cardlistObserver = Observer<CardListResponseParam> { cardsData ->
        if (cardsData.data.isNotEmpty()) {
            hasCard = true
            binding.floatingActionButton.setImageResource(R.drawable.refresh)
            binding.noCardLine.visibility = View.GONE
            binding.balanceLine.visibility = View.VISIBLE
            binding.cardsRecyclerview.visibility = View.VISIBLE

            cardAdapter.submitList(cardsData.data)
            var balance: Long = 0
            cardsData.data.onEach {
                var eachBalance = it.amount.substring(0, it.amount.indexOf('.'))
                balance += eachBalance.toLong()
            }
            val balanceText = String.format("%,3d", balance)
            this.balance = balanceText
            if (showBalance)
                binding.balance.text = balanceText
            else binding.balance.text = "******"
        } else {
            hasCard = false
            binding.floatingActionButton.setImageResource(R.drawable.plus_icon)
            binding.noCardLine.visibility = View.VISIBLE
            binding.balanceLine.visibility = View.GONE
            binding.cardsRecyclerview.visibility = View.GONE
        }
    }


    private val oplataLiveDataObserver = Observer<List<OplataNaMestaxData>> {
        oplataNaMestaxAdapter.submitList(it)
    }

    fun setStatusBarItemsColor() {
        val window = requireActivity().window
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = false
        }
    }
}

var showBalance: Boolean = true