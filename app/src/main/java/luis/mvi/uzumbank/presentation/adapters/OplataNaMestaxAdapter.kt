package luis.mvi.uzumbank.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import luis.mvi.uzumbank.R
import luis.mvi.uzumbank.data.local.CardData
import luis.mvi.uzumbank.data.local.CardEnum
import luis.mvi.uzumbank.data.local.OplataNaMestaxData
import luis.mvi.uzumbank.databinding.ItemCardBinding
import luis.mvi.uzumbank.databinding.ItemOplataNaMestaxBinding

/*
created by Xo'jaakbar on 14.09.2023 at 3:38
*/

class OplataNaMestaxAdapter:ListAdapter<OplataNaMestaxData,OplataNaMestaxAdapter.ViewHola>(diffutil) {

    private object diffutil: DiffUtil.ItemCallback<OplataNaMestaxData>() {
        override fun areItemsTheSame(
            oldItem: OplataNaMestaxData,
            newItem: OplataNaMestaxData,
        ): Boolean {
            return oldItem.imageUrl == newItem.imageUrl
        }

        override fun areContentsTheSame(
            oldItem: OplataNaMestaxData,
            newItem: OplataNaMestaxData,
        ): Boolean {
            return oldItem.imageUrl == newItem.imageUrl
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHola {
        return ViewHola(ItemOplataNaMestaxBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHola, position: Int) {
            holder.bind()
    }


    inner class ViewHola(private val binding:ItemOplataNaMestaxBinding) : ViewHolder(binding.root){

        fun bind(){
           binding.percentage.text = currentList[adapterPosition].skidka.toString()+"%"
           Glide
                .with(binding.root)
                .load(currentList[adapterPosition].imageUrl)
                .into(binding.image)
            binding.title.text = currentList[adapterPosition].title
        }
    }

}