package com.example.konekdatabase

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.renderscript.RenderScript
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_manage_fakultas.*
import org.json.JSONObject

class ManageFakultasActivity : AppCompatActivity() {
    lateinit var i: Intent
    lateinit var add:Button
    lateinit var delete:Button
    lateinit var edit:Button

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_fakultas)

        add = findViewById(R.id.btnCreate)
        edit = findViewById(R.id.btnUpdate)
        delete = findViewById(R.id.btnDelete)

        i = intent

        if(i.hasExtra("editmode")){
            if(i.getStringExtra("editmode").equals("1")){
                onEditMode()
            }
        }
        add.setOnClickListener{
            onCreate()
        }
        add.setOnclickListener{
            onUpdate()
        }
        add.setOnClickListener{
            onDelete()
        }

    }

    private fun onCreate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun onEditMode(){
        TODO("not implemented")

        txt_kodefakultas.setText(i.getStringExtra("txt_kodefakultas"))
        txt_namafakultas.setText(i.getStringExtra("txt_namafakultas"))

        btnCreate.visibility = View.GONE
        btnUpdate.visibility = View.VISIBLE
        btnDelete.visibility = View.VISIBLE
    }
    private fun OnCreate(){
        val loading = ProgressDialog(this)
        loading.setMessage("Menambahkan data...")
        loading.show()

        AndroidNetworking.post(ApiEndPoint.CREATE)
            .addBodyParameter("kode fakultas",txt_kodefakultas.text.toString())
            .addBodyParameter("nama fakultas",txt_namafakultas.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {
                    loading.dismiss()

                    Toast.makeText(applicationContext,response?.getString("message"),Toast.LENGTH_SHORT).show()
                    if(response?.getString("message")?.contains("successfully")!!){
                        this@ManageFakultasActivity.finish()
                    }
                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure",Toast.LENGTH_SHORT).show()
                }

            })
    }
    private fun onDelete() {
        val loading = ProgressDialog(this)
        loading.setMessage("Mengubah data...")
        loading.show()

        AndroidNetworking.post(ApiEndPoint.DELETE)
            .addBodyParameter("idfakultas", txt_idfakultas.text.toString())
            .addBodyParameter("kode_fakultas", txt_kodefakultas.text.toString())
            .addBodyParameter("nama_fakultas", txt_namafakultas.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener{
                override fun onResponse(response: JSONObject?) {
                    loading.dismiss()
                    Toast.makeText(applicationContext,response?.getString("massage"),Toast.LENGTH_SHORT).show()

                    if (response?.getString("message")?.contains("successfully")!!){
                        this@ManageFakultasActivity.finish()
                    }
                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failed", Toast.LENGTH_SHORT).show()
                }
            })

    }
}