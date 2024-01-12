package com.example.pingbdkt

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pingbdkt.databinding.ActivityCourseListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CourseList : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter

    //    var BASE_URL = "https://api.github.com/"
    var BASE_URL = "https://ping-bd-server.onrender.com/"



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        var dataList = ArrayList<DataClass>();
        recyclerView = findViewById(R.id.recycleView);

        val layoutManager = GridLayoutManager(this, 2)

        // at last set adapter to recycler view.

        // at last set adapter to recycler view.
        recyclerView.layoutManager = layoutManager

//        recyclerView.layoutManager= LinearLayoutManager(this);
//        for (i in 1..20) {
//            dataList.add(DataClass(R.drawable.ic_launcher_background, "Item " + i))
//        }
//        val adapter = MyAdaptar(this,dataList);
//        recyclerView.adapter = adapter;
        getAllData();

    }

    private fun getAllData() {

        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        var retroData = retrofit.getCourse()

        retroData.enqueue(object : Callback<List<CoursesItem>> {
            override fun onResponse(
                call: Call<List<CoursesItem>>,
                response: Response<List<CoursesItem>>
            ) {
                var data = response.body()!!
                myAdapter  = MyAdapter(baseContext,data)
                recyclerView.adapter = myAdapter
                showToast(data.toString())
                Log.d("data",data.toString())
            }

            override fun onFailure(call: Call<List<CoursesItem>>, t: Throwable) {

            }
        })

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}