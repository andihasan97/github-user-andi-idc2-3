package com.andihasan7.githubuser.ui.detailuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andihasan7.githubuser.api.ApiConfig
import com.andihasan7.githubuser.model.DetailResource
import com.andihasan7.githubuser.api.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {

    private val retrofit = ApiConfig.getApiService()
    private val user = MutableLiveData<Resource<DetailResource>>()

    fun getDetailUser(login: String?): LiveData<Resource<DetailResource>> {
        if (login != null) {
            retrofit.getDetailUser(login).enqueue(object :  Callback<DetailResource> {
                override fun onResponse(
                    call: Call<DetailResource>,
                    response: Response<DetailResource>
                ) {
                    val result = response.body()
                    user.postValue(Resource.Success(result))
                }

                override fun onFailure(call: Call<DetailResource>, t: Throwable) {

                }

            })
        }
        return user
    }
}