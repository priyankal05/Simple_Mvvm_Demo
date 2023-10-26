package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
//        val viewModel: MainViewModel by viewModels()

        binding.recycler.layoutManager = LinearLayoutManager(this)

        viewModel.lst.observe(this) {
            binding.recycler.adapter = NoteRecyclerAdapter(viewModel, it, this)
        }

        binding.button.setOnClickListener {
            addData()
        }

        /*
        // for add fragment
            if (savedInstanceState == null) {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    add<MainFragment>(R.id.fragment_container_view)
                }
            }*/
    }

    private fun addData() {
        val title = binding.titletxt.text.toString()
        if (title.isEmpty()) {
            Toast.makeText(this, "Enter value!", Toast.LENGTH_LONG).show()
        } else {
            val blog = Blog(title)
            viewModel.add(blog)
            binding.titletxt.text.clear()
//            binding.recycler.adapter?.notifyDataSetChanged()
        }
    }
}