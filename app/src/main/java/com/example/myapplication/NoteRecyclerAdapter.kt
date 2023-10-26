package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemLayoutBinding

class NoteRecyclerAdapter(
    val viewModel: MainViewModel,
    val arrayList: ArrayList<Blog>,
    private val context: Context
) : RecyclerView.Adapter<NoteRecyclerAdapter.NotesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): NoteRecyclerAdapter.NotesViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteRecyclerAdapter.NotesViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        if (arrayList.size == 0) {
            Toast.makeText(context, "List is empty", Toast.LENGTH_LONG).show()
        }
        return arrayList.size
    }

    /*
    override fun getItemViewType(position: Int): Int {
        return when {
            position % 8 == 3 -> ADS position %
            8 == 7 -> POLLS
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

    inner class NotesViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(blog: Blog) {
            binding.title.text = blog.title
            binding.delete.setOnClickListener {
                viewModel.remove(blog)
                notifyItemRemoved(arrayList.indexOf(blog))
            }
        }
    }
}
