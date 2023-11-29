package com.ajidres.movies.features.people


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ajidres.movies.databinding.ViewItemPopularPeopleBinding
import com.ajidres.movies.domain.model.ResultUI
import com.bumptech.glide.Glide

class PeopleListAdapter : ListAdapter<ResultUI, PeopleListViewHolder>(ResultDiffUtil()) {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleListViewHolder {
        context = parent.context

        val mView: ViewItemPopularPeopleBinding = ViewItemPopularPeopleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PeopleListViewHolder(mView)
    }

    override fun onBindViewHolder(holder: PeopleListViewHolder, position: Int) {
        holder.bind(context, getItem(position))
    }

}

class PeopleListViewHolder(itemView: ViewItemPopularPeopleBinding) : RecyclerView.ViewHolder(itemView.root) {

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


class ResultDiffUtil:DiffUtil.ItemCallback<ResultUI>(){
    override fun areItemsTheSame(oldItem: ResultUI, newItem: ResultUI): Boolean = oldItem==newItem

    override fun areContentsTheSame(oldItem: ResultUI, newItem: ResultUI): Boolean = oldItem==newItem
}