package com.mirkamol.mydemo4recyclerview.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mirkamol.mydemo4recyclerview.R
import com.mirkamol.mydemo4recyclerview.adapter.CustomAdapter
import com.mirkamol.mydemo4recyclerview.listener.onBottomReachedListener
import com.mirkamol.mydemo4recyclerview.model.Member

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerview)

        initViews()


        val members = prepareMemberList()
        refreshAdapter(members)
    }

    private fun initViews() {
        recyclerView.layoutManager = GridLayoutManager(this, 1)
    }

    private fun refreshAdapter(members: List<Member>) {
        val adapter = CustomAdapter(members, object : onBottomReachedListener {
            override fun onBottomReached(position: Int) {
                Log.d("@@@", "@@@onBottomReached" + position)
            }
        })
        recyclerView.adapter = adapter

    }

    private fun prepareMemberList(): List<Member> {
        val members = ArrayList<Member>()
        for (i in 0..29) {
            members.add(Member("Mirkamol Egamberdiyev", "+998974697907", true))
            members.add(Member("Mirkamol Egamberdiyev", "+998974697907", true))
            members.add(Member("Mirkamol Egamberdiyev", "+998974697907", false))
            members.add(Member("Mirkamol Egamberdiyev", "+998974697907", true))
        }


        return members
    }
}