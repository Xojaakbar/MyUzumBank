package luis.mvi.uzumbank.presentation.screens.main.transfer.cardtransfer

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import luis.mvi.uzumbank.R
import luis.mvi.uzumbank.data.network.response.DataXX
import luis.mvi.uzumbank.databinding.ScreenCardTransferBinding
import luis.mvi.uzumbank.domain.models.CardListResponseParam
import luis.mvi.uzumbank.domain.models.CurrentTransferData
import luis.mvi.uzumbank.presentation.adapters.CardsAdapterBottomSheet

/*
created by Xo'jaakbar on 15.09.2023 at 17:08
*/

@AndroidEntryPoint
class CardTransferScreen : Fragment(R.layout.screen_card_transfer) {
    val binding by viewBinding(ScreenCardTransferBinding::bind)
    private val viewModel: CardTransferViewModel by viewModels<CardTransferViewModelImpl>()
    private var dialog: Dialog? = null
    private val cardsAdapter by lazy { CardsAdapterBottomSheet() }
    private lateinit var currentTransferData: CurrentTransferData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllCards()
        viewModel.dialogLiveData.observe(this, dialogObserver)
        viewModel.toastLiveData.observe(this, toastObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCardsList()
        if (::currentTransferData.isInitialized) {  }
        binding.back.setOnClickListener { viewModel.backToHome() }
        binding.line2.setOnClickListener { viewModel.chooseCard() }
        viewModel.cardsListLiveData.observe(viewLifecycleOwner, cardsObserver)
        viewModel.cardLiveData.observe(viewLifecycleOwner, chosenCardObserver)
        viewModel.otherCardsNameLiveData.observe(viewLifecycleOwner,otherCardsInfoObserver)
        binding.edtCardNumber.doAfterTextChanged {

            if (binding.edtCardNumber.text.toString().length == 16) {
                viewModel.searchCard(binding.edtCardNumber.text.toString())
            } else {
                if (::currentTransferData.isInitialized){
                    binding.imgCardType.setImageResource(R.drawable.cards_icon2)
                    binding.imgCardType.setBackgroundColor(Color.WHITE)
                    binding.otherOwner.text = ""
                    val myCardsAmount = currentTransferData.myCardsAmount.substring(
                        0,
                        currentTransferData.myCardsAmount.indexOf('.')
                    ).toLong()
                    val it = binding.edtSumma.getNumericValue().toString()
                    val summaText =
                        if (it.isEmpty()) "" else binding.edtSumma.getNumericValue().toString()
                    val summa = summaText.substring(0, summaText.indexOf(".")).toLong()
                    binding.btnContinue.isEnabled =
                        (binding.edtCardNumber.text?.length == 16
                                && (binding.edtSumma.text.toString().length) > 3
                                && (summa + summa * 0.006) < myCardsAmount
                                && summa < 20000000)
                }
            }
        }

        binding.edtSumma.doAfterTextChanged {
            if (::currentTransferData.isInitialized) {
                val myCardsAmount = currentTransferData.myCardsAmount.substring(
                    0,
                    currentTransferData.myCardsAmount.indexOf('.')
                ).toLong()

                val it = binding.edtSumma.getNumericValue().toString()
                val summaText =
                    if (it.isEmpty()) "" else binding.edtSumma.getNumericValue().toString()

                Log.d("summatransfer", "$summaText")
                if (it.isEmpty() || summaText == "") {
                    binding.btnContinue.isEnabled = false
                    binding.apply {
                        komissiya.visibility = View.GONE
                        kzachisleniyu.visibility = View.GONE
                        errorMessage.visibility = View.VISIBLE
                        errorMessage.text =
                            "Минимальная сумма перевода без учета комиссии\n1 000,00"
                    }
                } else {
                    var summa =
                        summaText.toDouble().toLong()
                    if (summaText == "1.0E7") summa = 10000000

                    Log.d("summa", "summaText: $summaText \nsumma: $summa")
                    if (summaText.length > 5 && summa < 20000000) {
                        binding.apply {
                            var balance = edtSumma.text.toString()
                            Log.d("summa", "edtSumma.text: $balance")
//                      val balanceText = String.format("%,3d", summa.toString())
                            val komissiyaText = summa * 0.006
                            komissiya.text = "Комиссия: " + komissiyaText + " UZS"
                            kzachisleniyu.text = "К зачислению: $balance" + " UZS"
                            komissiya.visibility = View.VISIBLE
                            kzachisleniyu.visibility = View.VISIBLE
                            errorMessage.visibility = View.GONE
                        }
                    } else {
                        Log.d(
                            "summa",
                            "currentTransferData.myCardsAmount.toLong(): ${myCardsAmount}"
                        )
                        binding.apply {
                            komissiya.visibility = View.GONE
                            kzachisleniyu.visibility = View.GONE
                            errorMessage.visibility = View.VISIBLE
                            when {

                                summa < 1000 -> {
                                    errorMessage.text =
                                        "Минимальная сумма перевода без учета комиссии\n1 000,00"
                                }

                                summa > 20000000 -> {
                                    errorMessage.text = "Вы превысили сумму разового лимита"
                                    errorMessage.visibility = View.VISIBLE
                                    komissiya.visibility = View.GONE
                                    kzachisleniyu.visibility = View.GONE
                                }

                                (summa + summa * 0.006) > myCardsAmount -> {
                                    errorMessage.text = "Недостаточно средств"
                                    komissiya.visibility = View.VISIBLE
                                    kzachisleniyu.visibility = View.VISIBLE
                                    errorMessage.visibility = View.VISIBLE
                                }

                                else -> {
                                    errorMessage.visibility = View.GONE
                                    komissiya.visibility = View.VISIBLE
                                    kzachisleniyu.visibility = View.VISIBLE
                                }
                            }
                        }
                    }

                    binding.btnContinue.isEnabled =
                        (binding.edtCardNumber.text?.length == 16
                                && (binding.edtSumma.text.toString().length) > 3
                                && (summa + summa * 0.006) < myCardsAmount
                                && summa < 20000000)
                }
            }
        }

        binding.btnContinue.setOnClickListener {

            when {
                binding.edtCardNumber.text?.length != 16 -> {
                    Toast.makeText(requireContext(), "Введите номер карты", Toast.LENGTH_SHORT)
                        .show()
                }

                else -> {
                    currentTransferData.transferSumma = binding.edtSumma.getNumericValue().toLong()
                    currentTransferData.toCard = binding.edtCardNumber.text.toString()
                    viewModel.goToTransfer(currentTransferData)
                }
            }
        }

        cardsAdapter.setOnItemClickListener {
            viewModel.chosenCard(it)
            dialog?.hide()
        }
    }

    private val toastObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private val otherCardsInfoObserver = Observer<String> {
        if (it!=""){
            currentTransferData.otherCardsName = it
            binding.otherOwner.text = it
            val color = Color.rgb(63,103,165)
            binding.imgCardType.setBackgroundColor(color)
            if (binding.edtCardNumber.text.startsWith("9860")){
                binding.imgCardType.setImageResource(R.drawable.humos)
            }else{
                binding.imgCardType.setImageResource(R.drawable.u)
            }
        }else{
            binding.otherOwner.text = "Недействующий номер карты"
            binding.imgCardType.setBackgroundColor(Color.parseColor("#fff"))
            binding.imgCardType.setImageResource(R.drawable.cards_icon2)
        }
    }

    private val cardsObserver = Observer<CardListResponseParam> {
        if (it.data.isNotEmpty()) {
            binding.txtMyCardNumbr.visibility = View.VISIBLE
            binding.txtSumma.gravity = Gravity.LEFT
            binding.imgMyCardType.setImageResource(R.drawable.change_icon)
            viewModel.chosenCard(it.data[0])
        } else {
            binding.imgMyCardType.setImageResource(R.drawable.change_icon)
            binding.txtMyCardNumbr.visibility = View.GONE
            binding.txtSumma.text = "Выберите карту"
            binding.txtSumma.gravity = Gravity.CENTER_VERTICAL
        }
        initBottomSheetDialog(it)
    }

    private val chosenCardObserver = Observer<DataXX> {

        val amount = it.amount
        val balance = amount.substring(0, amount.indexOf('.')).toLong()
        val balanceText = String.format("%,3d", balance)

        binding.txtSumma.text = balanceText + " UZS"
        binding.imgMyCardType.setImageResource(
            if (it.pan.startsWith("8600")) R.drawable.u
            else R.drawable.humos
        )
        val cardNumber = it.pan
            .replaceRange(6, 12, "******").chunked(4)
        val cardNumberText = cardNumber.joinToString(" ")

        binding.txtMyCardNumbr.text = cardNumberText
        currentTransferData = it.toCurrentCardData()

    }

    private val dialogObserver = Observer<Unit> { dialog?.show() }

    private fun initBottomSheetDialog(cards: CardListResponseParam) {
        dialog = Dialog(requireContext())
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setContentView(R.layout.bottomsheet_cards)
        dialog!!.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.window!!.setGravity(Gravity.BOTTOM)
        dialog!!.window!!.attributes.windowAnimations = R.style.DialogAnimation
        dialog!!.findViewById<RecyclerView>(R.id.recycler_card).adapter = cardsAdapter
        cardsAdapter.submitList(cards.data)
    }

    override fun onStop() {
        super.onStop()
        dialog = null
    }
}