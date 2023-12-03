package luis.mvi.uzumbank.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import luis.mvi.uzumbank.R
import luis.mvi.uzumbank.data.local.CardData
import luis.mvi.uzumbank.data.local.CardEnum
import luis.mvi.uzumbank.data.network.response.DataXX
import luis.mvi.uzumbank.databinding.ItemCardBinding
import luis.mvi.uzumbank.domain.models.CardListResponseParam
import luis.mvi.uzumbank.presentation.screens.main.home.showBalance

/*
created by Xo'jaakbar on 14.09.2023 at 3:38
*/

class CardAdapter : ListAdapter<DataXX, CardAdapter.ViewHola>(diffutil) {

    private object diffutil : DiffUtil.ItemCallback<DataXX>() {
        override fun areItemsTheSame(oldItem: DataXX, newItem: DataXX): Boolean {
            return oldItem.pan == newItem.pan
        }

        override fun areContentsTheSame(oldItem: DataXX, newItem: DataXX): Boolean {
            return oldItem.pan == newItem.pan
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHola {
        return ViewHola(ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHola, position: Int) {
        holder.bind()
    }

    inner class ViewHola(private val binding: ItemCardBinding) : ViewHolder(binding.root) {

        fun bind() {
            val amount = currentList[adapterPosition].amount
            var balance = amount.substring(0, amount.indexOf('.')).toLong()
                val balanceText = String.format("%,3d", balance)
                if (showBalance){
                    binding.balance.text = balanceText
                    binding.uzs.visibility = View.VISIBLE
                }else{
                    binding.balance.text = "******"
                    binding.uzs.visibility = View.GONE
                }
                if (adapterPosition % 2 == 0) {
                    binding.imgTypeOfCard.setImageResource(R.drawable.u)
                } else {
                    binding.imgTypeOfCard.setImageResource(R.drawable.humos)
                }
                binding.txtFourNumbersOfCard.text = currentList[adapterPosition].pan.substring(0, 4)
            }
        }
    }
