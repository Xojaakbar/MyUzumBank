package luis.mvi.uzumbank.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import luis.mvi.uzumbank.R
import luis.mvi.uzumbank.data.network.response.DataXX
import luis.mvi.uzumbank.databinding.ItemCardBottomsheetBinding

/*
created by Xo'jaakbar on 11.11.2023 at 4:21
*/

class CardsAdapterBottomSheet: ListAdapter<DataXX,CardsAdapterBottomSheet.ViewHola>(DiffUtils) {

    private var onItemClick: ((DataXX)->Unit)? = null

    fun setOnItemClickListener(listener: (DataXX) -> Unit) {
        onItemClick = listener
    }

    private object DiffUtils: DiffUtil.ItemCallback<DataXX>(){
        override fun areItemsTheSame(oldItem: DataXX, newItem: DataXX): Boolean {
            return oldItem.pan == newItem.pan
        }

        override fun areContentsTheSame(oldItem: DataXX, newItem: DataXX): Boolean {
            return oldItem.pan == newItem.pan
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CardsAdapterBottomSheet.ViewHola {
        return ViewHola(ItemCardBottomsheetBinding
            .inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CardsAdapterBottomSheet.ViewHola, position: Int) {
        holder.bind()
    }

    inner class ViewHola(private val binding: ItemCardBottomsheetBinding): ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    onItemClick?.let {
                            it1 -> it1(item) }
                }
            }
        }

        fun bind(){
            val data = currentList[adapterPosition]
            val amount = data.amount
            val balance = amount.substring(0, amount.indexOf('.')).toLong()
            val balanceText = String.format("%,3d", balance)

            binding.summaOfCard.text = balanceText+" UZS"

            val cardNumber = data.pan
                .replaceRange(6,12,"******").chunked(4)
            val cardNumberText = cardNumber.joinToString(" ")
                binding.txtCardNumber.text =
                    if(data.pan.startsWith("8600")) "UZCARD - $cardNumberText" else "HUMO - $cardNumberText"

            binding.imgOfCard.setImageResource(
                if (data.pan.startsWith("8600")) R.drawable.u
                else R.drawable.humos
            )
            binding.txtTypeOfCard.text = if (data.pan.startsWith("8600")) "UZCARD" else "HUMO"
        }
    }
}