package naviacom.fr.mvvmdezzerexemple.activities.main

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_playlist.view.*
import naviacom.fr.mvvmdezzerexemple.models.Playlist

class MainViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    private val mPlayListCover = itemView!!.playlist_cover
    private val mPlayListTitle = itemView!!.playlist_title

    fun bindItemView(playList: Playlist) {
        mPlayListTitle.text = playList.title
        mPlayListCover.setImageURI(playList.picture_medium)
    }
}