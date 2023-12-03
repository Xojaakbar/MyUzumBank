package luis.mvi.uzumbank.presentation.screens.main.transfer

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import luis.mvi.uzumbank.R
import luis.mvi.uzumbank.databinding.ScreenTransferBinding
import luis.mvi.uzumbank.presentation.screens.main.transfer.cardtransfer.CardTransferScreen

@AndroidEntryPoint
class TransferScreen : Fragment(R.layout.screen_transfer) {

    val binding by viewBinding(ScreenTransferBinding::bind)
    private val viewModel:TransferViewModel by viewModels<TransferViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().window.statusBarColor = Color.WHITE
        setStatusBarItemsColor()

        binding.transferCard.setOnClickListener {
        viewModel.cardScreen()
        }
        binding.transferWallet.setOnClickListener {
            viewModel.walletScreen()
        }
        binding.transferRecvisite.setOnClickListener {
            viewModel.rekvizitScreen()
        }
        binding.transferBil.setOnClickListener {
            viewModel.schetScreen()
        }
    }
    val CardScreenObserver = Observer<Unit>{
        findNavController().navigate(R.id.action_transferScreen_to_cardTransferScreen)
    }
    val RekvizitScreenObserver = Observer<Unit>{
        findNavController().navigate(R.id.action_transferScreen_to_rekvizitTransfer)
    }
    val SchetScreenObserver = Observer<Unit>{
        findNavController().navigate(R.id.action_transferScreen_to_transferNaSchetScreen)
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