package com.rinho.payment.ui.Accounts.Account_List

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rinho.payment.R
import com.rinho.payment.databinding.FragmentHotelsSearchBinding
import com.rinho.payment.ui.Accounts.New_Account.AccountAdapter
import com.rinho.payment.ui.base.BaseFragment
import com.rinho.payment.util.EndlessRecyclerViewScrollListener
import com.rinho.payment.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint

/**
 * Display UI of hotels search form.
 */
@AndroidEntryPoint
class AccountListFragment :
    BaseFragment<AccountListViewModel, FragmentHotelsSearchBinding>(AccountListViewModel::class.java) {





var x:Int=1
    private var hotelsAdapter by autoCleared<AccountAdapter>()

    /**
     * A recyclerView scrollListener to handle Scroll.
     */
    private var scrollListener by autoCleared<EndlessRecyclerViewScrollListener>()

    override fun getLayoutRes() = R.layout.fragment_hotels_search

    override fun init() {

        viewModel.allcontacts.observe(viewLifecycleOwner, ::handleApiStatus)

//init account list
        initAccountList()
        //init Add account
        initAddAccount()
    }



    private fun initAccountList() {


        viewModel.navigatewithresult.observe(viewLifecycleOwner) {
           binding.totalResult.setText(it)
        }


                hotelsAdapter = AccountAdapter(dataBindingComponent, appExecutors) {
                    findNavController().navigate(
                        R.id.hotelsListFragment,
                        bundleOf(
                            "req_no" to it.id
//                            "hotelSearchRequest" to viewModel.accountsList.value!!
                        )
                    )
                }

                binding.recyclerHotels.adapter = hotelsAdapter

                scrollListener = object :
                    EndlessRecyclerViewScrollListener(binding.recyclerHotels.layoutManager as LinearLayoutManager) {
                    override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
//                        x=x+1
//                        viewModel.fetchNextPage(""+x)
//                        Log.d(TAG, "onLoadMore: ")
                    }
                }

                binding.recyclerHotels.removeOnScrollListener(scrollListener)
                binding.recyclerHotels.addOnScrollListener(scrollListener)

                viewModel.accountsList.observe(viewLifecycleOwner) {
                                    Log.d("fetchContacts", "fetchContacts1: "+it.toString())
//it.sortByDescending { it.balance }

                    hotelsAdapter.submitList(it)
                    hotelsAdapter.notifyDataSetChanged()
//                    if (it.toString().length<40) {
//                        GeneralDialog(DialogType.ERROR, getString(R.string.again)).show(childFragmentManager, tag)
//                    }
                }
//                Log.d("accounts", "accounts: "+it.data.toString())
//            }
//        }


    }
    /**
     * init search button click to navigate to Add Account screen.
     */
    @SuppressLint("NewApi")
    private fun initAddAccount() {
        binding.btnSearch.setOnClickListener {
x=x+1
          viewModel.fetchNextPage(""+x)
            viewModel.accountsList.observe(viewLifecycleOwner) {
                Log.d("fetchContacts", "fetchContacts1: " + it.toString())
//it.sortByDescending { it.balance }
                hotelsAdapter.submitList(it)
                hotelsAdapter.notifyDataSetChanged()


//                val arabicLocale: Locale = Locale.forLanguageTag("ar")
//                val arabicDateFormatter: DateTimeFormatter =
//                    DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
//                        .withLocale(arabicLocale)
//                        .withDecimalStyle(DecimalStyle.of(arabicLocale))
//                val today: LocalDate = LocalDate.now(ZoneId.of("Asia/Muscat"))
//                System.out.println(today.format(arabicDateFormatter))
//            Log.d(TAG, "initAddAccount: "+today.format(arabicDateFormatter))

//            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//
//            val date = "2011-08-04"
//            val arabicLocale: Locale = Locale.forLanguageTag("ar-SA")
//            val arabicDateFormatter: DateTimeFormatter =
//                DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(arabicLocale)
//                    .withDecimalStyle(DecimalStyle.of(arabicLocale))
//
//            var today: LocalDate = LocalDate.now(ZoneId.of("Asia/Muscat"))
//            today = LocalDate.parse(date, formatter)
//
//            val dat: String = today.format(arabicDateFormatter)
//            println(dat)
            }
        }
        binding.searchresult.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
//                if (list.contains(query)) {
//
//                    hotelsAdapter.filter.filter(query)
//                } else {
//                    Toast.makeText(this@MainActivity, "No Match found", Toast.LENGTH_LONG).show()
//                }
                viewModel.search(""+query)
//                viewModel.accountsList.observe(viewLifecycleOwner) {
//                    Log.d("fetchContacts", "fetchContacts1: " + it.toString())
////it.sortByDescending { it.balance }
//                    hotelsAdapter.submitList(it)
//                    hotelsAdapter.notifyDataSetChanged()
//                }
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
//                adapter.filter.filter(newText)
                return false
            }
        })
//binding.radioGroup.setOnCheckedChangeListener(
//    RadioGroup.OnCheckedChangeListener { group, checkedId ->
//        val radio: RadioButton = binding.ch
//
//    })
        binding.available.setOnClickListener {

            Log.d(TAG, "initAddAccount: ")
viewModel.filter(""+x)
        }
        binding.total.setOnClickListener {
            binding.fromto.setText("1-20")
            Log.d(TAG, "initAddAccount: ")
            viewModel.total()
        }


        binding.next.setOnClickListener {
            x=x+1
val result=x*20
            val resultto=(x*20)+20
            val resultfrom=(x*20)-20
            binding.fromto.setText(""+resultfrom+"-"+result+" of ")
            Log.d(TAG, "initAddAccount: ")
            viewModel.fetchNextPage(""+x)
        }
        binding.previous.setOnClickListener {

            Log.d(TAG, "initAddAccount: ")
            if(x==1 || x==0){
                binding.previous.visibility=View.INVISIBLE
            }else {
                x=x-1
                val result=x*20
                val resultto=(x*20)+20
                val resultfrom=(x*20)-20
                if(x==1){
                    binding.fromto.setText("1-20")
                    viewModel.filter("" + x)
                }else {
                    binding.fromto.setText("" + resultfrom + "-" + result+" of ")
                    viewModel.fetchNextPage("" + x)
                }
            }
        }
    }
}