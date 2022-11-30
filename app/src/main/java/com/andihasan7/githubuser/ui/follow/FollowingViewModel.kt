package com.andihasan7.githubuser.ui.follow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andihasan7.githubuser.api.ApiConfig
import com.andihasan7.githubuser.api.Resource
import com.andihasan7.githubuser.model.DetailResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {

    private val retrofit = ApiConfig.getApiService()
    private val listUser = MutableLiveData<Resource<List<DetailResource>>>()

    fun getUserFollowing(login: String): LiveData<Resource<List<DetailResource>>> {
        listUser.postValue(Resource.Loading())
        retrofit.getUserFollowing(login).enqueue(object : Callback<List<DetailResource>> {
            override fun onResponse(
                call: Call<List<DetailResource>>,
                response: Response<List<DetailResource>>
            ) {
                val list = response.body()
                if (list.isNullOrEmpty())
                    listUser.postValue(Resource.Error(null))
                else
                    listUser.postValue(Resource.Success(list))
            }

            override fun onFailure(call: Call<List<DetailResource>>, t: Throwable) {
                listUser.postValue(Resource.Error(t.message))
            }
        })
        return listUser
    }
}