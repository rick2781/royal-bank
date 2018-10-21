package com.rick.royalbank.ui.accountlist

import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.rick.royalbank.R
import com.rick.royalbank.data.model.Account
import com.rick.royalbank.databinding.AccountListItemBinding
import com.rick.royalbank.ui.accountdetail.AccountDetailActivity
import com.rick.royalbank.utils.ROI

class AccountListAdapter(private val context: Context) : RecyclerView.Adapter<AccountListAdapter.ViewHolder>() {

    private val differ = AsyncListDiffer<Account>(this, DiffCallback)

    var accountList: List<Account> = emptyList()
        set(value) {
            field = value
            differ.submitList(value)
        }

    class ViewHolder(val binding: AccountListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {

        val binding = DataBindingUtil.inflate<AccountListItemBinding>(
                LayoutInflater.from(p0.context),
                R.layout.account_list_item,
                p0,
                false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        val currentAccount = differ.currentList[p1]

        p0.binding.account = currentAccount

        p0.binding.root.setOnClickListener {
            navigateToAccountDetail(currentAccount.roi)
        }
    }

    override fun getItemCount() = differ.currentList.size

    private fun navigateToAccountDetail(roi: Double) {

        val intent = Intent(context, AccountDetailActivity::class.java)
        intent.putExtra(ROI, roi)

        context.startActivity(intent)
    }
}

object DiffCallback : DiffUtil.ItemCallback<Account>() {
    override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem.amount == newItem.amount
                && oldItem.name == newItem.name
                && oldItem.roi == newItem.roi
    }
}