package com.coursework.lifehacktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.coursework.lifehacktest.model.Company
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_.layoutManager = LinearLayoutManager(this)

        fetchJson()
    }

    fun fetchJson(){
        val url = "http://megakohz.bget.ru/test_task/test.php"
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()
                val companies = gson.fromJson(body, Array<Company>::class.java).toList()

                runOnUiThread{
                    recyclerView_.adapter = MainAdapter(companies)
                    recyclerView_.addItemDecoration(DividerItemDecoration(this@MainActivity,
                        DividerItemDecoration.VERTICAL))
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                Log.d("error request", e.message)
            }
        })
    }
}


