package luis.mvi.uzumbank.presentation.screens.main.transfer.cardtransfer.checktransfer.verifytransfer.successtransfer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import luis.mvi.uzumbank.R
import luis.mvi.uzumbank.databinding.ScreenSuccessTransferBinding

/*
created by Xo'jaakbar on 23.11.2023 at 21:40
*/
@AndroidEntryPoint
class SuccessTransferScreen:Fragment(R.layout.screen_success_transfer) {
    private val binding by viewBinding(ScreenSuccessTransferBinding::bind)
    private val viewModel: SuccessTransferViewModel by viewModels<SuccessViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnHome.setOnClickListener {
            viewModel.goHome()
        }
        viewModel.summaLiveData.observe(viewLifecycleOwner,summaObserver)
    }

    private val summaObserver = Observer<String>{
        binding.summa.text = it
    }
}