package com.example.productfinderfromml.ui.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.paging.ExperimentalPagingApi
import androidx.transition.TransitionInflater
import coil.load
import com.example.productfinderfromml.R
import com.example.productfinderfromml.core.Resource
import com.example.productfinderfromml.data.model.Resultado
import com.example.productfinderfromml.data.model.detail.ProductDetail
import com.example.productfinderfromml.databinding.FragmentDetailsBinding
import com.example.productfinderfromml.databinding.ItemDetailBinding
import com.example.productfinderfromml.presentation.DetailViewModel
import com.example.productfinderfromml.presentation.DetailViewModel.Status
import com.example.productfinderfromml.ui.details.viewpager.CarouselTransformer
import com.example.productfinderfromml.ui.details.viewpager.ViewPagerAdapter
import com.example.productfinderfromml.utils.slideDown
import com.example.productfinderfromml.utils.slideUp
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by Lorenzo Suarez on 3/5/2021.
 */

@ExperimentalPagingApi
@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemReceived: Resultado = args.item

        binding = FragmentDetailsBinding.bind(view)
        binding.image.transitionName = itemReceived.id
        binding.image.load(itemReceived.thumbnail)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.viewPager.adapter = ViewPagerAdapter()

        viewModel.getProductDetail(arrayOf(itemReceived.id))

        viewModel.status.observe(viewLifecycleOwner, {
            if(it == Status.STATUS1) binding.linear.slideDown(viewGroup = binding.parent)
            if(it == Status.STATUS2) binding.linear.slideUp(viewGroup = binding.parent)
        })

        binding.button.setOnClickListener {
            when (viewModel.status.value) {
                Status.STATUS1 -> viewModel.setStatus(Status.STATUS2)
                Status.STATUS2 -> viewModel.setStatus(Status.STATUS1)
            }
        }

        viewModel.productDetail.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    val a = 2
                }
                is Resource.Success -> {
                    /*if (result.data. != 200) {
                                //binding.rvList.hide()
                                return@Observer
                            }*/
                    viewModel.updatePictures(result.data.first().body.pictures)
                    binding.productDetail = result.data.first()


                    result.data.first().body.attributes.forEach { attribute ->
                        val viewInflater: View = layoutInflater.inflate(
                            R.layout.item_detail,
                            null
                        )
                        val itemDetailBinding = ItemDetailBinding.bind(viewInflater)
                        itemDetailBinding.name.text = attribute.name.trim()
                        itemDetailBinding.value.text = attribute.valueName.trim()

                        binding.linear.addView(viewInflater)
                    }

                }

                is Resource.Failure -> {
                    val a = 2
                }
            }
        })

        binding.viewPager.offscreenPageLimit = 1
        binding.viewPager.setPageTransformer(CarouselTransformer(requireContext()))

    }

    private val productDetailObserver = Observer<Resource<ProductDetail>> { result ->
        when (result) {
            is Resource.Loading -> {

            }
            is Resource.Success -> {
                if (result.data.code != 200) {
                    //binding.rvList.hide()
                    return@Observer
                }
                //viewModel.updatePictures(result.data.body.pictures)
            }
            is Resource.Failure -> {

            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.myGalacticLeague).isVisible = false

        super.onPrepareOptionsMenu(menu)
    }
}

