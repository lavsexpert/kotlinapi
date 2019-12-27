package club.plus1.kotlinapi.api

import club.plus1.kotlinapi.model.Result
import io.reactivex.Observable

class SearchRepository(val apiService: GithubApiService) {
    fun searchUsers(location: String): Observable<Result> {
        return apiService.search(
            query = "location:$location",
            sort = "followers",
            order = "asc"
        )
    }
}