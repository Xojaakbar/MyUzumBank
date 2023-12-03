package luis.mvi.uzumbank.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import luis.mvi.uzumbank.data.local.CategoryData
import luis.mvi.uzumbank.data.local.FavouritePaysData
import luis.mvi.uzumbank.databinding.ItemCategoryBinding
import luis.mvi.uzumbank.databinding.ItemFavouriteBinding

/*
created by Xo'jaakbar on 19.09.2023 at 3:30
*/

class FavouritePaysAdapter: ListAdapter<FavouritePaysData, FavouritePaysAdapter.ViewHola>(catergoryDifutil) {

    private object catergoryDifutil: DiffUtil.ItemCallback<FavouritePaysData>() {
        override fun areItemsTheSame(
            oldItem: FavouritePaysData,
            newItem: FavouritePaysData,
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: FavouritePaysData,
            newItem: FavouritePaysData,
        ): Boolean {
            return oldItem.title == newItem.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHola {
        return ViewHola(ItemFavouriteBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHola, position: Int) {
        holder.bind(position)
    }

    inner class ViewHola(val binding: ItemFavouriteBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position:Int){
           binding.img.setImageResource(currentList[position].img)
            binding.title.text = currentList[position].title
        }
    }
}