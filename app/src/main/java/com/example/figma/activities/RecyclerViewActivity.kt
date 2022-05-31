package com.example.figma.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.Figma.Adapters.Adapter
import com.example.figma.DBHelper
import com.example.figma.R
import com.example.figma.models.Model

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var mList: ArrayList<Model>
    private lateinit var refresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        recyclerView = findViewById(R.id.recycle)
        mList = ArrayList()
        refresh = findViewById(R.id.refresh)

        getNames()

        val adapter = Adapter(mList, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        refresh.setOnRefreshListener {
            Toast.makeText(this, "Refreshed", Toast.LENGTH_SHORT).show()
            adapter.notifyDataSetChanged()
            refresh.isRefreshing = false
        }



    }

    private fun getNames() {

        mList.clear()

        val db = DBHelper(this, null)

        val cursor = db.getName()

        while (cursor.moveToNext()) {

            val model = Model(
                cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NAME_COl)),
                cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.EMAIL_COL)),
                cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NUMBER_COL)),
                cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.PASSWORD_COL))
            )

            mList.add(model)
        }
    }
}