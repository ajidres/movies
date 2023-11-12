package com.ajidres.movies.features.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ajidres.movies.R
import com.ajidres.movies.databinding.ViewItemMoviesBinding
import com.ajidres.movies.domain.model.ResultMoviesUI
import com.ajidres.movies.extentions.gone
import com.bumptech.glide.Glide


class MoviesAdapter() : RecyclerView.Adapter<MoviesViewHolder>() {


    private var items = mutableListOf<ResultMoviesUI>()
    private var movieType = ""
    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        context = parent.context

        val mView: ViewItemMoviesBinding = ViewItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MoviesViewHolder(mView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(context, items[position], movieType)
    }

    fun update(data: List<ResultMoviesUI>, movie: String) {
        this.items.apply {
            clear()
            addAll(data)
            movieType = movie
            notifyDataSetChanged()
        }
    }

}

class MoviesViewHolder(itemView: ViewItemMoviesBinding) : RecyclerView.ViewHolder(itemView.root) {
    private var mView: ViewItemMoviesBinding = itemView

    fun bind(context: Context, items: ResultMoviesUI, movieType: String) {
        with(mView) {
            Glide.with(context).load(items.posterPath).into(imgMovie)
            tvMovieName.text = items.originalTitle
            tvMovieRelease.text = items.releaseDate

            when (movieType) {
                POPULAR_MOVIES -> {
                    tvMovieAverage.background = AppCompatResources.getDrawable(context, R.drawable.bg_circle)
                    tvMovieAverage.text = items.showPopularity()
                }

                RATED_MOVIES -> {
                    tvMovieAverage.background = AppCompatResources.getDrawable(context, R.drawable.bg_rectangle)
                    tvMovieAverage.text = items.rated.toString()
                }
                else -> tvMovieAverage.gone()
            }
        }
    }
}
