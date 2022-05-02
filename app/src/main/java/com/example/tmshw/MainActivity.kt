package com.example.tmshw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmshw.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val recyclerViewFragment = RecyclerViewFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_view_tag, recyclerViewFragment)
            commit()
        }
    }
}