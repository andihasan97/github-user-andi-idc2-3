package com.andihasan7.githubuser.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
    @SerializedName("items") @Expose val items: List<DetailResource>
)
