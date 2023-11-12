package com.ajidres.movies.features.people

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajidres.movies.databinding.ViewItemPopularPeopleBinding
import com.ajidres.movies.domain.model.ResultUI
import com.bumptech.glide.Glide


class PeopleAdapter() : RecyclerView.Adapter<PeopleViewHolder>() {


    private var items = mutableListOf<ResultUI>()
    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        context = parent.context

        val mView: ViewItemPopularPeopleBinding = ViewItemPopularPeopleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PeopleViewHolder(mView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.bind(context, items[position])
    }

    fun update(data: List<ResultUI>) {
        this.items.apply {
            clear()
            addAll(data)
            notifyDataSetChanged()
        }
    }

}

class PeopleViewHolder(itemView: ViewItemPopularPeopleBinding) : RecyclerView.ViewHolder(itemView.root) {
    var mView: ViewItemPopularPeopleBinding = itemView

    fun bind(context: Context, items: ResultUI) {
        with(mView) {
            Glide.with(context).load(items.profilePath).into(imgPeople)
            tvPeopleName.text=items.name
            tvPeopleMovies.text=items.toMoviesList()
            tvPeopleAverage.text=items.rate.toString()
        }
    }
}
