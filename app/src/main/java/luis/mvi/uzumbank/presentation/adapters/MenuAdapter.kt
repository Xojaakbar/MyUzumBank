package luis.mvi.uzumbank.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import luis.mvi.uzumbank.data.local.CategoryData
import luis.mvi.uzumbank.data.local.MenuCategoryData
import luis.mvi.uzumbank.databinding.ItemMenuBinding

/*
created by Xo'jaakbar on 19.10.2023 at 19:59
*/

class MenuAdapter:ListAdapter<MenuCategoryData,MenuAdapter.ViewHola>(menudifutil) {

    private object menudifutil : DiffUtil.ItemCallback<MenuCategoryData>(){
        override fun areItemsTheSame(
            oldItem: MenuCategoryData,
            newItem: MenuCategoryData,
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: MenuCategoryData,
            newItem: MenuCategoryData,
        ): Boolean {
            return oldItem.title == newItem.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHola {
            return ViewHola(ItemMenuBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHola, position: Int) {
        holder.bind(position)
    }

    inner class ViewHola(private val binding:ItemMenuBinding):ViewHolder(binding.root){
        fun bind(position: Int){
            binding.img.setImageResource(currentList[position].img)
            binding.title.text = currentList[position].title
        }
    }

}