//package com.example.ricardo.tickit2.view.fragment.show
//
//import android.widget.TextView
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import android.support.v7.widget.RecyclerView
//import com.example.ricardo.tickit2.R
//
//
///**
// * Created by yuhanyin on 12/22/17.
// */
//class RecycleTicketsAdapter  : RecyclerView.Adapter<RecyclerViewTicketsAdapter.MyViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        return MyViewHolder(LayoutInflater.from(
//                this@HomeActivity).inflate(R.layout.item_home, parent,
//                false))
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.tv.setText(mDatas.get(position))
//    }
//
//    override fun getItemCount(): Int {
//        return mDatas.size()
//    }
//
//    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//
//        var tv: TextView
//
//        init {
//            tv = view.findViewById(R.id.id_num)
//        }
//    }
//}