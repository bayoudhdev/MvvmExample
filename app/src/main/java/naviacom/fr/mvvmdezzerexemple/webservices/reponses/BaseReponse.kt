package naviacom.fr.mvvmdezzerexemple.webservices.reponses

class BaseResponse<out T>(
        val data: List<T>,
        val total: Int
)