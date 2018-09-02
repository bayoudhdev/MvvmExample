package naviacom.fr.mvvmdezzerexemple.models


data class Track(
        val id: Long,
        val title: String,
        val title_short: String,
        var duration: Int,
        val artist: Artist,
        val album: Album
)