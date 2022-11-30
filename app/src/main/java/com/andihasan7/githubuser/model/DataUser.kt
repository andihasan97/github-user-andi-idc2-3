package com.andihasan7.githubuser.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataUser(
    var avatar: Int,
    var name: String?,
    var username: String?,
    var company: String?,
    var location: String?,
    var repository: String?,
    var followers: String?,
    var following: String?
) : Parcelable
