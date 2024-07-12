package com.example.practice.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.databinding.DistrictBinding
import com.example.practice.model.RegionData

class DistrictAdapter:RecyclerView.Adapter<DistrictAdapter.DistrictViewHolder>() {
    val districts= arrayListOf<RegionData>()
    lateinit var onclick:(String)->Unit
    inner class DistrictViewHolder(val districtBinding: DistrictBinding): RecyclerView.ViewHolder(districtBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistrictViewHolder {
        val item=DistrictBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DistrictViewHolder(item)
    }
    override fun getItemCount(): Int{
        return districts.size
    }
    override fun onBindViewHolder(holder: DistrictViewHolder, position: Int) {
        val district=districts[position]
        holder.districtBinding.textView.text=district.slug
        holder.districtBinding.district.setOnClickListener {
            onclick(district.slug.toString())
        }
    }
    fun updateList(newList:List<RegionData>){
        districts.clear()
        districts.addAll(newList)
        notifyDataSetChanged()
    }
}