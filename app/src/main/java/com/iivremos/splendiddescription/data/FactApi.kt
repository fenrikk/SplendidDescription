package com.test.factsapp.data

import com.iivremos.splendiddescription.data.model.FactResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FactApi {
    @Headers("X-Api-Key: Atgztu3YlbIUiQb3TlbdBw==Cy3cLBuEuCydpV1Q")
    @GET("/v1/facts")
    fun getFacts(
        @Query("limit") limit: Int
    ): Single<FactResponse>
}