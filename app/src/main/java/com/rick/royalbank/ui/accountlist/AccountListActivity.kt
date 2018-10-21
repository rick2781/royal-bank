package com.rick.royalbank.ui.accountlist

import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rick.royalbank.R
import com.rick.royalbank.databinding.ActivityAccountListBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountListActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityAccountListBinding

    private val accountListAdapter: AccountListAdapter by inject()
    private val viewModel: AccountListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupDataBinding()
        setupLayout()
    }

    private fun setupLayout() {

        with(dataBinding.recyclerView){

            val linearLayoutManager = LinearLayoutManager(this@AccountListActivity)

            adapter = accountListAdapter
            layoutManager = linearLayoutManager

            addItemDecoration(DividerItemDecoration(
                    this@AccountListActivity,
                    linearLayoutManager.orientation))

            updateRecyclerView()
        }
    }

    private fun updateRecyclerView() {

        val adapter = dataBinding.recyclerView.adapter as AccountListAdapter

        viewModel.accountList.observe(this, Observer {
            adapter.accountList = it
        })
    }

    private fun setupDataBinding(){

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_account_list)
        dataBinding.accountListViewModel = viewModel
    }
}
