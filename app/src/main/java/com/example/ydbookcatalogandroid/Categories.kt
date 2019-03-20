package com.example.ydbookcatalogandroid

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_categories.*
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class Categories : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val view = findViewById(R.id.categories_view) as TextView

        view.text = getCategories()

    }

    private fun getCategories():String{
        val url = URL("ydbookcatalog.herokuapp.com/categoryList/")
        val urlConnection = url.openConnection() as HttpsURLConnection
        urlConnection.doInput = true
        urlConnection.connect()

        try{
            val sb = StringBuilder()
            val input = BufferedReader(InputStreamReader(urlConnection.inputStream))
            input.forEachLine {
                sb.append(it)
            }
            input.close()
            return sb.toString()
        } finally {
            urlConnection.disconnect()
        }
    }

//    private fun readStream(input: InputStream):String {
//        try {
//            val bo = ByteArrayOutputStream()
//            var i = input.read()
//            while (i != -1) {
//                bo.write(i)
//                i = input.read()
//            }
//            return bo.toString()
//        } catch (e: IOException) {
//            return ""
//        }
//    }

}
