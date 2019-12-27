package club.plus1.kotlinapi.model

data class Result (
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<User>
)