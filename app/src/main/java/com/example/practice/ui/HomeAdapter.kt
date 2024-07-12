package com.example.practice.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.databinding.CityBinding
import com.example.practice.model.Data

class HomeAdapter:RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    val cities= arrayListOf<Data>()
    lateinit var onclick:(String)->Unit
    inner class HomeViewHolder(val cityBinding: CityBinding):RecyclerView.ViewHolder(cityBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val item=CityBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeViewHolder(item)
    }
    override fun getItemCount(): Int{
        return cities.size
    }
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val city=cities[position]
        holder.cityBinding.textView2.text=city.cities
        holder.cityBinding.city.setOnClickListener {
            onclick(city.slug.toString())
        }
    }
    fun updateList(newList:List<Data>){
        cities.clear()
        cities.addAll(newList)
        notifyDataSetChanged()
    }
}