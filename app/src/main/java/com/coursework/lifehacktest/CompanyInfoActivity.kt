package com.coursework.lifehacktest

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.coursework.lifehacktest.model.CompanyDescription
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import okhttp3.*
import java.io.IOException

class CompanyInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.company_info)

        val id = intent.getIntExtra("id", -1)

        if(id!=-1)
        {
            val url = "http://megakohz.bget.ru/test_task/test.php?id="+id
            val request = Request.Builder().url(url).build()

            val client = OkHttpClient()
            client.newCall(request).enqueue(object: Callback {
                override fun onResponse(call: Call, response: Response) {
                    val body = response?.body()?.string()

                    val gson = GsonBuilder().create()
                    try {
                        val company_info = gson.fromJson(body, Array<CompanyDescription>::class.java).toList()
                        runOnUiThread(){
                            supportActionBar?.setTitle(company_info[0].name)
                            findViewById<TextView>(R.id.tv_descr).text = company_info[0].description
                            findViewById<TextView>(R.id.tv_nameInfo).text = company_info[0].name
                            findViewById<TextView>(R.id.tv_site).text = company_info[0].www
                            findViewById<TextView>(R.id.tv_lat).text = company_info[0].lat
                            findViewById<TextView>(R.id.tv_lon).text = company_info[0].lon
                            findViewById<TextView>(R.id.tv_phone).text = company_info[0].phone

                            Glide.with(this@CompanyInfoActivity)
                                .asBitmap()
                                .load("http://megakohz.bget.ru/test_task/" + company_info[0].img)
                                .into(findViewById(R.id.img_info))
                        }
                    }
                    catch (e : JsonSyntaxException)
                    {
                        runOnUiThread(){
                            Toast.makeText(applicationContext, "Error in json", Toast.LENGTH_SHORT).show()
                        }
                        finish()
                    }
                }
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("error request", e.message)
                }
            })
        }
    }
}