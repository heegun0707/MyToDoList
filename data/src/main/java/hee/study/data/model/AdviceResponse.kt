package hee.study.data.model

data class AdviceResponse(
    val slip: Slip
) {

    data class Slip(
        val id: Int,
        val advice: String
    )
}
