package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.databinding.ItemAdLayoutBinding
import com.example.myapplication.databinding.ItemLayoutBinding


class NoteRecyclerAdsAdapter(
    val viewModel: MainViewModel,
    val arrayList: ArrayList<Blog>
) : RecyclerView.Adapter<ViewHolder>() {


    private val CONTENT_TYPE: Int = 2
    private val AD_TYPE: Int = 1
    private val ADS_LIST_DALTA: Int = 4

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int,
    ): ViewHolder {
        return if (viewType == AD_TYPE) {
            val binding =
                ItemAdLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            AdsHolder(binding)
        } else {
            val binding =
                ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            NotesViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pm = arrayList[getRealPosition(position)]

        if (holder is AdsHolder) {
//            holder.oNativeExpressAdview.setText("This is AdView")
        } else if (holder is NotesViewHolder) {
            holder.bind(pm)
        }
    }

    private fun getRealPosition(position: Int): Int {
        return if (ADS_LIST_DALTA == 0) {
            position
        } else {
            position - position / ADS_LIST_DALTA
        }
    }

    override fun getItemCount(): Int {
        var additionalContent = 0
        if (arrayList.size > 0 && ADS_LIST_DALTA > 0 && arrayList.size > ADS_LIST_DALTA) {
            additionalContent = arrayList.size / ADS_LIST_DALTA
        }
        return arrayList.size + additionalContent
    }


    /*

    override fun getItemViewType(position: Int): Int {
        return when {
            position % 8 == 3 -> ADS
            else -> DATA
        }
    }

    //

     override fun getItemViewType(position: Int): Int {
         return if (position > 1 && (position + 1) % 4 == 0) {
             AD_TYPE
         } else CONTENT_TYPE
         }
     }*/

    override fun getItemViewType(position: Int): Int {
        return if (position > 1 && (position + 1) % ADS_LIST_DALTA == 0) {
            AD_TYPE
        } else CONTENT_TYPE
    }

    inner class NotesViewHolder(private val binding: ItemLayoutBinding) :
        ViewHolder(binding.root) {
        fun bind(blog: Blog) {
            binding.title.text = blog.title
            binding.delete.setOnClickListener {
                viewModel.remove(blog)
                notifyItemRemoved(arrayList.indexOf(blog))
            }
        }
    }

    inner class AdsHolder(private val binding: ItemAdLayoutBinding) :
        ViewHolder(binding.root)
}
