package com.rinho.payment.ui.Accounts.New_Account

import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.rinho.payment.R
import com.rinho.payment.databinding.FragmentHotelsList1Binding
import com.rinho.payment.models.Status
import com.rinho.payment.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_hotels_list1.view.*

/**
 * Display hotel search results in list and in map.
 */
@AndroidEntryPoint
class NewAccountFragment :
    BaseFragment<NewAccountViewModel, FragmentHotelsList1Binding>(NewAccountViewModel::class.java) {


    /**
     * The arguments of the fragment.
     */
    private val args by navArgs<NewAccountFragmentArgs>()

    override fun getLayoutRes() = R.layout.fragment_hotels_list1

    override fun init() {




        Log.d("initCreate", "initCreate: "+args.reqNo)
        initCreate()
        initDetails()
//        initType()
    }




private fun initDetails(){

    viewModel.updateDetailsRequest(args.reqNo)

    viewModel.details.observe(viewLifecycleOwner, ::handleApiStatus)

    viewModel.details.observe(viewLifecycleOwner) {
        if (it.data != null) {
            binding.hotelDetails = it.data.data
//            imagesAdapter.submitList(it.data.images)
//            policyAdapter.submitList(it.data.policies)
//            facilityAdapter.submitList(it.data.amenities)
            val text=it.data.data.action.name
            Log.d(TAG, "initDetails: "+it.data.data.action.name)
            if(text!=null){
                binding.linear1.visibility= View.VISIBLE
                binding.cretenew.setText(text)
//
            }
            Log.d(TAG, "initDetails: "+it.data.data.action)
        }
    }
}
    /**
     * Add Anew Contact.
     */
    private fun initCreate() {

    binding.root.cretenew.setOnClickListener {
//        if(binding.root.validate()){
//            viewModel.insertAccount(viewModel.createInsertAccount())
//                .observe(viewLifecycleOwner) {
//                    handleApiStatus(it)
//                    if (it.status == Status.SUCCESS){
//
//                        Log.d("initCreate", "initCreate: "+"true")
//                        findNavController().navigateUp()
//                }else{
//                Log.d("initCreate", "initCreate: " + "false")
//            }
//            }
//
//    }
//        DisplayAccounts.newInstance("x")
//            .apply { isCancelable = false }
//            .show(childFragmentManager, DisplayAccounts.TAG)
viewModel.accept_statues()
    .observe(viewLifecycleOwner) {
                    handleApiStatus(it)
                    if (it.status == Status.SUCCESS){

                        Log.d("initCreate", "initCreate: "+"true")
                        binding.linear1.visibility=View.INVISIBLE
                        Toast.makeText(context,"تم الاعتماد",Toast.LENGTH_LONG).show()
                }else{
                Log.d("initCreate", "initCreate: " + "false")
            }
            }



    }




        binding.root.cretenew1.setOnClickListener {

       viewModel.deny_statues().observe(viewLifecycleOwner) {
                handleApiStatus(it)
                if (it.status == Status.SUCCESS){
                    binding.linear1.visibility=View.INVISIBLE
                    Log.d("initCreate", "initCreate: "+"true")
Toast.makeText(context,"تم الغاء الطلب",Toast.LENGTH_LONG).show()
                }else{
                    Log.d("initCreate", "initCreate: " + "false")
                }
            }



        }


    }

//    // Init type od account
//    private fun initType() {
//        val typeAdapter = TypeAdapter(requireContext(), listOf(Type.Checking, Type.Savings,Type.creditCard))
//        binding.etTypeContainer.spinner.setAdapter(typeAdapter)
//
//        binding.etTypeContainer.spinner.setOnItemClickListener { _, _, _, id ->
//            viewModel.setSelectedType(id.toInt())
//        }
//
//        viewModel.type_list.observe(viewLifecycleOwner) {
//            binding.etTypeContainer.spinner.setText(it.txtRes)
//            typeAdapter.filter.filter(null)
//
//        }
//    }
}