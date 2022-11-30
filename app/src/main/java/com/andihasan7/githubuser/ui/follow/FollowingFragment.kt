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
import com.andihasan7.githubuser.databinding.FragmentFollowingBinding
import com.andihasan7.githubuser.model.DetailResource
import com.andihasan7.githubuser.ui.adapter.ListUserAdapter

@Suppress("UNCHECKED_CAST", "DEPRECATION")
class FollowingFragment : Fragment(), ViewStateCallBack<List<DetailResource>> {
    // TODO: Rename and change types of parameters
    private var _followingBinding: FragmentFollowingBinding? = null
    private lateinit var userAdapter: ListUserAdapter
    private var login: String? = null
    private lateinit var viewModel: FollowingViewModel
    private val followingBinding get() = _followingBinding!!

    companion object {
        fun getInstance(login: String): Fragment {
            return FollowingFragment().apply {
                arguments = Bundle().apply {
                    putString("USERNAME", login)
                }
            }
        }
    }

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
        _followingBinding = FragmentFollowingBinding.inflate(inflater, container, false)
        return followingBinding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[FollowingViewModel::class.java]
        userAdapter = ListUserAdapter()
        followingBinding.rvViewFollowing.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        viewModel.getUserFollowing(login.toString()).observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> onFailed(it.message)
                is Resource.Loading -> onLoading()
                is Resource.Success -> it.data?.let { it1 -> onSuccess(it1) }
            }
        }
    }

    override fun onSuccess(data: List<DetailResource>) {
        userAdapter.setAllData(data)

        followingBinding.apply {
            tvMessage.visibility = invisible
            progressbarFollowing.visibility = invisible
            rvViewFollowing.visibility = visible
        }
    }

    override fun onLoading() {
        followingBinding.apply {
            tvMessage.visibility = invisible
            progressbarFollowing.visibility = visible
            rvViewFollowing.visibility = invisible
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onFailed(message: String?) {
        followingBinding.apply {
            if (message == null) {
                tvMessage.text = "Tidak ada yang di ikuti ${login}"
                tvMessage.visibility = visible
            } else {
                tvMessage.text = message
                tvMessage.visibility = visible
            }
            progressbarFollowing.visibility = invisible
            rvViewFollowing.visibility = invisible
        }
    }
}