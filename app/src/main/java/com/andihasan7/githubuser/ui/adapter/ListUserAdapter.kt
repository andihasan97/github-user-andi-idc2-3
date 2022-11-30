package com.andihasan7.githubuser.ui.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.EXTRA_USER
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andihasan7.githubuser.databinding.ItemRowUserBinding
import com.andihasan7.githubuser.model.DetailResource
import com.andihasan7.githubuser.ui.detailuser.DetailUser
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListUserAdapter : RecyclerView.Adapter<ListUserAdapter.UserViewHolder>() {

    private val listUser = ArrayList<DetailResource>()

    @SuppressLint("NotifyDataSetChanged")
    fun setAllData(data: List<DetailResource>) {
        listUser.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemRowUserBinding
                .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size

    inner class UserViewHolder(private val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DetailResource) {
            itemView.apply {
                with(binding) {
                    Glide.with(itemView.context)
                        .load(user.avatarUrl)
                        .apply(RequestOptions().override(80, 80))
                        .into(imgItemPhoto)
                    tvItemCompany.text = user.type
                    tvItemUsername.text = user.login

                    itemView.setOnClickListener {
                        val intent = Intent(itemView.context, DetailUser::class.java)
                        intent.putExtra(EXTRA_USER, user.login)
                        itemView.context.startActivity(intent)
                    }
                }
            }
        }
    }

}