package luis.mvi.uzumbank.presentation.screens.main.transfer.cardtransfer.checktransfer

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import luis.mvi.uzumbank.R
import luis.mvi.uzumbank.databinding.ScreenCardTransfer2Binding
import luis.mvi.uzumbank.domain.models.CurrentTransferData
import luis.mvi.uzumbank.domain.models.TransferRequestParam

@AndroidEntryPoint
class CardTransferScreen2 : Fragment(R.layout.screen_card_transfer_2) {
    private val binding by viewBinding(ScreenCardTransfer2Binding::bind)
    private val viewModel:CardTransfer2ViewModel by viewModels<CardTransfer2ViewModelImpl>()
    private val args: CardTransferScreen2Args by navArgs()
    private lateinit var transferRequestParam: TransferRequestParam

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.toastLiveData.observe(viewLifecycleOwner,toastObserver)
        viewModel.currentLiveData.observe(viewLifecycleOwner,currentDataObserver)
        viewModel.currentTransferInfo(args.currentTransferData)
        binding.btnContinue.setOnClickListener {
            viewModel.transfer(transferRequestParam)
            viewModel.setSumma(binding.summaNumber.text.toString()+" UZS")
        }
        binding.back.setOnClickListener { viewModel.back() }
    }

    private val currentDataObserver = Observer<CurrentTransferData>{
        Log.d("currentTransferData", "\ncurrentTransferData: $it")
        binding.cardName.text = it.otherCardsName
        binding.cardNameInfo.text = it.myCardsName
        binding.date.text = "${it.expire_month}/${it.expire_year}"
        val amount = it.transferSumma.toString()
        val balanceText = Editable.Factory.getInstance().newEditable(amount).append(" UZS")
        val myBalance = it.myCardsAmount.substring(0, it.myCardsAmount.indexOf('.')).toLong()
        val myBalanceText = String.format("%,3d", myBalance)
        binding.summaOfCard.setText(myBalanceText)
        binding.summaNumber.text = balanceText

        val myCardNumber = it.myCardsPan.replaceRange(6, 12, "******").chunked(4)
        val myCardNumberText = myCardNumber.joinToString(" ")

        val yourCardNumber = it.toCard
            .replaceRange(6, 12, "******").chunked(4)
        val yourCardNumberText = yourCardNumber.joinToString(" ")
        binding.cardNumberInfo.text = myCardNumberText
        binding.cardNumber.text = yourCardNumberText
        val kommissiya = it.transferSumma*0.006
        binding.komissiya.text = "Комиссия: $kommissiya UZS"
        transferRequestParam = TransferRequestParam(amount = it.transferSumma.toInt(), from_card_id = it.fromCardId, card_number = it.toCard)
    }

    private val incorrectLiveDataObserver = Observer<Unit> {
        val vibrator = requireActivity().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        // Проверка на доступность вибрации
        if (vibrator.hasVibrator()) {
            // Вибрация на 500 миллисекунд
            vibrator.vibrate(100)
        }

        FancyToast.makeText(
            requireContext(),
            "Неверный КОД",
            FancyToast.LENGTH_SHORT,
            FancyToast.ERROR,
            true
        ).show()
    }

    private val toastObserver = Observer<String>{
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
}