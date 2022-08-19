package ua.dimaevseenko.mornhousetestapp.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ua.dimaevseenko.mornhousetestapp.model.Fact
import ua.dimaevseenko.mornhousetestapp.network.request.RFact
import javax.inject.Inject

class Server @Inject constructor() {

    @Inject
    lateinit var rFact: RFact

    fun getFact(factId: String, completion: (Fact)-> Unit){
        CoroutineScope(Dispatchers.Default).launch {
            rFact.getFact(factId).enqueue(object : Callback<ResponseBody>{
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    response.body()?.let { completion(Fact(factId, it.string())) }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }

    fun getRandomFact(completion: (Fact) -> Unit){
        CoroutineScope(Dispatchers.Default).launch {
            rFact.getRandomFact().enqueue(object : Callback<ResponseBody>{
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    response.body()?.let { completion(Fact("Random", it.string())) }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }
}