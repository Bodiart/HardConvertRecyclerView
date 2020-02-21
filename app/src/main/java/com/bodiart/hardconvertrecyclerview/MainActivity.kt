package com.bodiart.hardconvertrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val urls = arrayListOf<String>()

        for (i in 0..1000)
            urls.add(IMAGE_URL)

        val images = urls.map { Image(it) }

        val adapter = ImageListAdapter()

        recycler_view.adapter = adapter
        recycler_view.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)

        adapter.setNewData(images)
    }


    companion object {
        private const val IMAGE_URL = "https://www.gstatic.com/webp/gallery3/1.png"
    }

}
