package naviacom.fr.mvvmdezzerexemple.activities.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import naviacom.fr.mvvmdezzerexemple.R
import naviacom.fr.mvvmdezzerexemple.models.Playlist
import kotlin.properties.Delegates

class MainViewAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var playLists: List<Playlist> = emptyList()
         set(value) {
            field = value
            notifyDataSetChanged()

        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainViewHolder {
        val rootView = LayoutInflater.from(parent!!.context).inflate(R.layout.item_playlist, parent, false)
        return MainViewHolder(rootView)
    }

    override fun getItemCount() = if (playLists.isNotEmpty()) playLists.size else 0


    override fun onBindViewHolder(holder: MainViewHolder?, position: Int) {
        holder!!.bindItemView(playLists[position])
    }

}