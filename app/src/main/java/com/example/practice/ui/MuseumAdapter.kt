package com.example.practice.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.databinding.DistrictBinding
import com.example.practice.model.MuseumData

class MuseumAdapter:RecyclerView.Adapter<MuseumAdapter.MuseumViewHolder>() {
    private val museumlist= arrayListOf<MuseumData>()
    inner class MuseumViewHolder(val districtBinding: DistrictBinding):RecyclerView.ViewHolder(districtBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MuseumViewHolder {
        val item=DistrictBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MuseumViewHolder(item)
    }

    override fun getItemCount(): Int {
       return museumlist.size
    }

    override fun onBindViewHolder(holder: MuseumViewHolder, position: Int) {
       val museum=museumlist[position]
        holder.districtBinding.textView.text=museum.name
    }
    fun updateList(newlist:List<MuseumData>){
        museumlist.clear()
        museumlist.addAll(newlist)
        notifyDataSetChanged()
    }

}