//package com.example.ricardo.tickit2.view.common
//
//import android.content.Context
//import android.support.v7.widget.RecyclerView
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ArrayAdapter
//import android.widget.TextView
//import com.example.ricardo.tickit2.R
//import com.example.ricardo.tickit2.view.fragment.home.ShowItemAdapter
//
///**
// * Created by Ricardo on 2018/1/4.
// */
//open class HorizontalScrollAdapter(
//        internal var context: Context,
//        internal var layoutId: Int,
//        var items:List<AnyH_ItemAdapter> = listOf()
//): ArrayAdapter<AnyH_ItemAdapter>(context, layoutId){
//
//    private var holder = Holder()
//    var view: View = View(context)
//    var currentPosition = 0
//
//    override fun getCount(): Int {
//        return items.size
//    }
//
//    override fun getItem(position: Int): AnyH_ItemAdapter? {
//        return items[position]
//    }
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val layout: View
//
//        if (convertView == null) {
//
//            layout = View.inflate(context, layoutId, null)
//            //var holder = Holder()
//
//
//            items[position].bindViewHolder(holder)
//            holder.title = layout.findViewById<TextView>(R.id.home_film_title)
//            holder.image = layout.findViewById<com.facebook.drawee.view.SimpleDraweeView>(R.id.home_film_icon)
//            layout.tag = holder
//
//        } else {
//            layout = convertView
//            view = layout
//            var holder = layout.tag as Holder
//        }
//        val newsSource: ShowItemAdapter = getItem(position)!! as ShowItemAdapter
//
//        items[position]
//
//        holder.title!!.text = newsSource.show.name
//        holder.image!!.setImageURI(newsSource.show.avatarPath)
//
//        layout.setOnClickListener { newsSource.clicked(newsSource.show) }
//
//        return layout
//    }
//
//    open class Holder() {
//        var title: TextView? = null
//        var image: com.facebook.drawee.view.SimpleDraweeView? = null
//    }
//
//
//}
//
//typealias AnyH_ItemAdapter=HorizontalItemAdapter
//<out HorizontalScrollAdapter.Holder>