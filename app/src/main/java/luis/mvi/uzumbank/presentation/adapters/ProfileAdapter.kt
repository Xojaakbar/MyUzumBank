package luis.mvi.uzumbank.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import luis.mvi.uzumbank.data.local.MenuCategoryData
import luis.mvi.uzumbank.databinding.ItemMenuBinding

/*
created by Xo'jaakbar on 26.10.2023 at 2:28
*/

class ProfileAdapter:ListAdapter<MenuCategoryData,ProfileAdapter.ViewHola>(difutil) {

    private object difutil: DiffUtil.ItemCallback<MenuCategoryData>(){
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

    inner class ViewHola(private val binding:ItemMenuBinding): ViewHolder(binding.root){
        fun bind(position: Int){
            binding.img.setImageResource(currentList[position].img)
            binding.title.text = currentList[position].title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter.ViewHola {
        return ViewHola(ItemMenuBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ProfileAdapter.ViewHola, position: Int) {
        holder.bind(position)
    }
}