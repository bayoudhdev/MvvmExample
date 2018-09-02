package naviacom.fr.mvvmdezzerexemple.models


data class Playlist(
        val id: Long,
        val title: String,
        val creator: Author,
        val picture_small: String,
        val picture_medium: String,
        val picture_big: String,
        val nb_tracks: Int,
        val duration: Int,
        val rating:Int,
        val fans:Int
)