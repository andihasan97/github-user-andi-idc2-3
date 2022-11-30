package com.andihasan7.githubuser.ui.follow

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.andihasan7.githubuser.api.Resource
import com.andihasan7.githubuser.api.ViewStateCallBack
import com.andihasan7.githubuser.databinding.FragmentFollowerBinding
import com.andihasan7.githubuser.model.DetailResource
import com.andihasan7.githubuser.ui.adapter.ListUserAdapter


@Suppress("UNCHECKED_CAST", "DEPRECATION")
class FollowerFragment : Fragment(), ViewStateCallBack<List<DetailResource>> {

    companion object {
        fun getInstance(login : String): Fragment {
            return FollowerFragment().apply {
                arguments = Bundle().apply {
                    putString("USERNAME", login)
                }
            }
        }
    }

    private var _followerBinding: FragmentFollowerBinding? = null
    private lateinit var viewModel: FollowerViewModel
    private lateinit var userAdapter: ListUserAdapter
    private var login: String? = null
    private val followerBinding get() = _followerBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            login = it.getString("USERNAME")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _followerBinding = FragmentFollowerBinding.inflate(inflater, container, false)
        return followerBinding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[FollowerViewModel::class.java]
        userAdapter = ListUserAdapter()
        followerBinding.rvViewFollowers.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        viewModel.getUserFollowers(login.toString()).observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> onFailed(it.message)
                is Resource.Loading -> onLoading()
                is Resource.Success -> it.data?.let { it1 -> onSuccess(it1)}
            }
        }
    }


    override fun onSuccess(data: List<DetailResource>) {
        userAdapter.setAllData(data)
        followerBinding.apply {
            tvMessage.visibility = invisible
            progressbarFollowers.visibility = invisible
            rvViewFollowers.visibility = visible
        }
    }

    override fun onLoading() {
        followerBinding.apply {
            tvMessage.visibility = invisible
            progressbarFollowers.visibility = visible
            rvViewFollowers.visibility = invisible
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onFailed(message: String?) {
        followerBinding.apply {
            if (message == null) {
                tvMessage.text = "Tidak ada yang Mengikuti ${login}"
                tvMessage.visibility = visible
            } else {
                tvMessage.text = message
                tvMessage.visibility = visible
            }
            progressbarFollowers.visibility = invisible
            rvViewFollowers.visibility = invisible
        }
    }
}