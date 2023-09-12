package com.example.matchmate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.matchmate.R
import com.example.matchmate.model.CardModelTransformation
import com.squareup.picasso.Picasso

class CardAdapter : RecyclerView.Adapter<CardAdapter.MyViewHolder>() {
    private var items = ArrayList<CardModelTransformation>()


    fun setUpdatedData(items: List<CardModelTransformation>) {
        this.items = items as ArrayList<CardModelTransformation>
        notifyDataSetChanged()

    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagePerson = view.findViewById<ImageView>(R.id.profilePicture)
        val personName = view.findViewById<TextView>(R.id.personName)
        val personAddress = view.findViewById<TextView>(R.id.personAddress)
        val acceptMatch =view.findViewById<ImageView>(R.id.acceptMatch)
        val rejectMatch =view.findViewById<ImageView>(R.id.rejectMatch)
        val acceptordeclinestatus =view.findViewById<TextView>(R.id.AcceptorDeclineStatus)
        val acceptedStatus=itemView.context.getString(R.string.ms_accepted)
        val declinedStatus=itemView.context.getString(R.string.ms_rejected)

        fun bind(data: CardModelTransformation) {
            with(data) {
                personName.text = "$first $last"
                personAddress.text = "$number $country"
                Picasso.get()
                    .load(large)
                    .into(imagePerson)
                acceptMatch.setOnClickListener{
                    acceptMatch.visibility=View.GONE
                    rejectMatch.visibility=View.GONE
                    acceptordeclinestatus.visibility=View.VISIBLE
                    acceptordeclinestatus.text= acceptedStatus
                }
                rejectMatch.setOnClickListener{
                    acceptMatch.visibility=View.GONE
                    rejectMatch.visibility=View.GONE
                    acceptordeclinestatus.visibility=View.VISIBLE
                    acceptordeclinestatus.text= declinedStatus
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_match_card, parent, false)
        return MyViewHolder(view)

    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items.get(position))
    }


    override fun getItemCount(): Int {
        return items.size
    }

}