package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    var likes: Long,
    var share: Long,
    var views: Long,
    var likedByMe: Boolean
)

object PostService{
    fun numToString(num: Long): String{
        var number = num.toFloat()
        var numberString = num.toString()
        if (num > 999 && num < 10_000){
            number /= 100
            val truncated = number.toInt() / 10.0
            numberString = truncated.toString() + "К"
        } else if (num > 9_999 && num < 1_000_000){
            number /= 100
            val truncated = number.toInt() / 10.0
            numberString = truncated.toString() + "К"
        } else if (num > 999_999){
            number /= 100_000
            val truncated = number.toInt() / 10.0
            numberString = truncated.toString() + "М"
        }
        return numberString
    }
}
