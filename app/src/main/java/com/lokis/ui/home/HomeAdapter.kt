package com.lokis.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPhotoRequest
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.lokis.R
import com.lokis.model.DataDetail
import com.lokis.model.DataTravel
import java.util.ArrayList
import java.util.Arrays

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    private val limit = 20
    private val list = ArrayList<DataTravel>()
    var placeClient: PlacesClient? = null

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setList(dataTravel: ArrayList<DataTravel>){
        list.clear()
        list.addAll(dataTravel)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(home: DataTravel) {
            val name = view.findViewById<TextView>(R.id.tvNameWisata)
            val city = view.findViewById<TextView>(R.id.tvCity)
            val imgUser = view.findViewById<ImageView>(R.id.img_user)


            name.text = home.name
            city.text = home.city

            Glide.with(itemView)
                .load(home.url)
                .transform(RoundedCorners(20))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loader)
                        .error(R.drawable.ic_error)
                )
                .into(imgUser)
        }

        fun getPhoto(placeId: String) {
            val place_id = view.findViewById<ImageView>(R.id.img_user)
            val placePict = FetchPlaceRequest.builder(
                placeId,
                Arrays.asList(
                    Place.Field.PHOTO_METADATAS
                )
            ).build()

            placeClient?.fetchPlace(placePict)
                ?.addOnSuccessListener {
                    val place = it.place
                    val photoMetadata = place.photoMetadatas!![0]
                    val photoRequest = FetchPhotoRequest.builder(photoMetadata).build()
                    placeClient?.fetchPhoto(photoRequest)
                        ?.addOnSuccessListener { fetch ->
                            val bitmap = fetch.bitmap
                            place_id.setImageBitmap(bitmap)
                        }
                }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return if(list.size > limit){
            limit
        } else {
            list.size
        }
    }

    interface OnItemClickCallback{
        fun onItemClicked(home: DataTravel)
    }
}