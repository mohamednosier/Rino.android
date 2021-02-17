package com.rinho.payment.ui.DisolayAccounts

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.rinho.payment.R
import com.rinho.payment.databinding.FragmentDisplayAccountsBinding
import com.rinho.payment.models.Status
import com.rinho.payment.ui.base.BaseFragment
import com.rinho.payment.ui.common.DialogType
import com.rinho.payment.ui.common.GeneralDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
 class DisplayAccountsFragment :
    BaseFragment<DisplayAccountsViewModel, FragmentDisplayAccountsBinding>(DisplayAccountsViewModel::class.java){

var account :Int=100
    override fun getLayoutRes() = R.layout.fragment_display_accounts

    override fun init() {

        initaccept()
        initaccounts()

    }
    private fun initaccept() {
        binding.accept.setOnClickListener {
            if (account == 100) {
//                Toast.makeText(context, getString(R.string.choose_account), Toast.LENGTH_SHORT).show()
                GeneralDialog(DialogType.ERROR, getString(R.string.choose_account)).show(childFragmentManager, tag)
            } else {
                viewModel.acceptAccount().observe(viewLifecycleOwner) {
                    handleApiStatus(it)
                    if (it.status == Status.SUCCESS) {
                        findNavController().navigateUp()
                    }
                }
            }

        }

        binding.cancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }
    @SuppressLint("ResourceType")
    private fun initaccounts() {
//        viewModel.displayaccounts()
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
        viewModel.displayaccounts().observe(viewLifecycleOwner){
            handleApiStatus(it)
            if(it.status==Status.SUCCESS){
                val linearLayout1 = binding.rootContainer
                val radioGroup1 = binding.radioGroup
//                Log.d(TAG, "initaccounts: "+it.data?.data?.get(0))
                 val size: Int? =it.data?.data?.size
                val size1= size?.minus(1)
         for(i in 0 ..size1!!) {
             val radioButton3 = RadioButton(context)
             radioButton3.layoutParams = LinearLayout.LayoutParams(
                 ViewGroup.LayoutParams.WRAP_CONTENT,
                 ViewGroup.LayoutParams.WRAP_CONTENT
             )
             radioButton3.setText(it.data.data.get(i).department)
             radioButton3.id = i

             if (radioGroup1 != null) {

                 radioGroup1.addView(radioButton3)

                 radioGroup1.setOnCheckedChangeListener { group, checkedId ->
                     var text = "you selected"
//                     text += " " + getString(if (checkedId == 0) R.string.male else R.string.female)
                     text += " " + checkedId
                     account=checkedId
                     Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                 }
             }
         }
//        viewModel.updateDetailsRequest(args.reqNo)


//        viewModel.displayaccounts.observe(viewLifecycleOwner, ::handleApiStatus)
//        viewModel.displayaccounts.observe(viewLifecycleOwner) {
//            if (it.data != null) {
//                binding.hotelDetails = it.data.data
//            imagesAdapter.submitList(it.data.images)
//            policyAdapter.submitList(it.data.policies)
//            facilityAdapter.submitList(it.data.amenities)

//                Log.d(TAG, "initaccounts: "+it.data.data.get(0))




//                val linearLayout = binding.rootContainer
//
//                // Create RadioButton Dynamically
//                val radioButton1 = RadioButton(context)
//                radioButton1.layoutParams = LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//                )
//                radioButton1.setText(R.string.male)
//                radioButton1.id = 0
//
//                val radioButton2 = RadioButton(context)
//                radioButton2.layoutParams = LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//                )
//                radioButton2.setText(R.string.female)
//                radioButton2.id = 1
//
//                val radioGroup = binding.radioGroup
//                if (radioGroup != null) {
//                    radioGroup.addView(radioButton1)
//                    radioGroup.addView(radioButton2)
//
//                    radioGroup.setOnCheckedChangeListener { group, checkedId ->
//                        var text = "you selected"
//                        text += " " + getString(if (checkedId == 0) R.string.male else R.string.female)
//                        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
//                    }
//                }


            }
        }
    }
}







