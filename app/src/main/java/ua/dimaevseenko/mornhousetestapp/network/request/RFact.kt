package ua.dimaevseenko.mornhousetestapp.network.request

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RFact{

    @GET("{id}")
    fun getFact(@Path("id") id: String): Call<ResponseBody>

    @GET("random/math")
    fun getRandomFact(): Call<ResponseBody>
}