package com.example.konekdatabase

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fakultas_list.view.*
import com.example.konekdatabase.MainActivity as MainActivity1

class RVAdapterFakultas (private val context: Context, private val arrayList: ArrayList<Fakultas>) :
        RecyclerView.Adapter<RVAdapterFakultas.Holder>(){
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.label_idfakultas.text =
            this.arrayList?.get(position)?.id_fakultas.toString()
        holder.view.label_kodefakultas.text =
            this.arrayList?.get(position)?.kode_falkutas.toString()
        holder.view.label_namafakultas.text = "Nama Fakultas:"+arrayList?.get(position)?.nama_fakultas

        holder.view.cardview_list.setOnClickListener{
            val i = Intent(context,ManageFakultasActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra("editmode","1")
            i.putExtra("txt_idfakultas", arrayList?.get(position)?.id_fakultas)
            i.putExtra("txt_kodefakultas", arrayList?.get(position)?.kode_falkutas)
            i.putExtra("txt_namafakultas", arrayList?.get(position)?.nama_fakultas)
            context.startActivity(i)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder (LayoutInflater.from(parent.context).inflate(R.layout.fakultas_list,parent,false))
    }

    override fun getItemCount(): Int = arrayList!!.size

    class Holder (val view: View) : RecyclerView.ViewHolder(view)
}