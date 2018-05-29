package br.com.cristiano.marvelheroes.adapters

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import br.com.cristiano.marvelheroes.ui.CharacterDetailActivity
import br.com.cristiano.marvelheroes.R
import br.com.cristiano.marvelheroes.http.apimodel.Character
import br.com.cristiano.marvelheroes.http.apimodel.Thumbnail
import com.squareup.picasso.Picasso
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import com.squareup.picasso.Callback
import java.lang.Exception


class CharactersAdapter(var context: Context, var data: ArrayList<Character>) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    private var isLoadingAdded = false
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        this.context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_character,parent,false)

        return ViewHolder(cell)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = data[position]
        holder.row_progress_bar.visibility = View.VISIBLE
        Picasso.get()
                .load(item.thumbnail.getUrl(Thumbnail.Variants.standard_medium)).into(holder.thumbnail_image_view
                ,object :Callback{
                    override fun onSuccess() {
                        holder.row_progress_bar.visibility = View.INVISIBLE
                    }

                    override fun onError(e: Exception?) {
                        holder.row_progress_bar.visibility = View.INVISIBLE
                    }

                })


        holder.nema_text_view.text = item.name
        holder.row_container.setOnClickListener {
            val i = Intent(this.context, CharacterDetailActivity::class.java)

            val type = object : TypeToken<Character>() {}.type
            val json = Gson().toJson(item, type)
            i.putExtra("characterJson",json)
            this.context.startActivity(i)
        }



    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {


        lateinit var thumbnail_image_view: ImageView
        lateinit var nema_text_view: TextView
        lateinit var row_container: ConstraintLayout
        lateinit var row_progress_bar: ProgressBar


        init {
            if (itemView != null){

                this.thumbnail_image_view = itemView.findViewById(R.id.row_thumbnail_image_view)
                this.nema_text_view = itemView.findViewById(R.id.row_name_text_view)
                this.row_container = itemView.findViewById(R.id.row_container)
                this.row_progress_bar = itemView.findViewById(R.id.row_progress_bar)


            }
        }





    }


}