package luis.mvi.uzumbank.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import luis.mvi.uzumbank.data.local.CategoryData
import luis.mvi.uzumbank.databinding.ItemCategoryBinding

/*
created by Xo'jaakbar on 19.09.2023 at 2:44
*/

class CategoryAdapter:ListAdapter<CategoryData,CategoryAdapter.ViewHola>(CategoryDiffUtil) {

    private object CategoryDiffUtil: DiffUtil.ItemCallback<CategoryData>() {
        override fun areItemsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean {
            return oldItem.title ==newItem.title
        }

        override fun areContentsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean {
            return oldItem.title ==newItem.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHola {
        return ViewHola(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHola, position: Int) {
        holder.bind(position)
    }

    inner class ViewHola(val binding:ItemCategoryBinding):ViewHolder(binding.root){
        fun bind(position:Int){
            binding.apply {
                img.setImageResource(currentList[position].img)
                title.text = currentList[position].title
                count.text = currentList[position].count.toString()
            }
        }
    }
}