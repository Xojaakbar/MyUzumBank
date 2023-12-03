package luis.mvi.uzumbank.presentation.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import coil.transform.CircleCropTransformation
import com.bumptech.glide.Glide
import luis.mvi.uzumbank.R
import luis.mvi.uzumbank.data.local.FastDostupData
import luis.mvi.uzumbank.databinding.ItemFastDostupBinding

/*
created by Xo'jaakbar on 14.09.2023 at 3:53
*/

class FastDostupAdapter: ListAdapter<FastDostupData, FastDostupAdapter.ViewHola>(difutil) {

    private object difutil: DiffUtil.ItemCallback<FastDostupData>(){
        override fun areItemsTheSame(oldItem: FastDostupData, newItem: FastDostupData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: FastDostupData, newItem: FastDostupData): Boolean {
            return oldItem.name == newItem.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHola {
            return ViewHola(ItemFastDostupBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHola, position: Int) {
        holder.bind()
    }

    inner class ViewHola(private val binding:ItemFastDostupBinding) : ViewHolder(binding.root){
            fun bind(){
//                binding.image.load(currentList[adapterPosition].imageUrl){
//                    crossfade(true)
//                    placeholder(R.drawable.uzum_market)
//                    transformations(CircleCropTransformation())
//                }
                Log.d("imgurl", "img url fastdostup: ${currentList[adapterPosition].imageUrl}")
                    Glide
                        .with(binding.root)
                        .load(currentList[adapterPosition].imageUrl)
                        .into(binding.image)
                binding.text.text = currentList[adapterPosition].name
            }
    }
}