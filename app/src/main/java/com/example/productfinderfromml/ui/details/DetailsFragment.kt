package com.example.productfinderfromml.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.paging.ExperimentalPagingApi
import androidx.transition.TransitionInflater
import coil.load
import com.example.productfinderfromml.R
import com.example.productfinderfromml.core.Resource
import com.example.productfinderfromml.data.model.item.Results
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
        val itemReceived: Results = args.item

        binding = FragmentDetailsBinding.bind(view)
        binding.image.load(itemReceived.thumbnail)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.item = itemReceived

        binding.viewPager.adapter = ViewPagerAdapter()

        viewModel.getProductDetail(arrayOf(itemReceived.id))

        viewModel.status.observe(viewLifecycleOwner, fun(status: Status) {
            with(binding) {
                when (status) {
                    Status.STATUS1 -> linear.slideDown(viewGroup = containerAttributes)
                    Status.STATUS2 -> linear.slideUp(viewGroup = containerAttributes)
                }
            }
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
                }
                is Resource.Success -> {
                    binding.shimmer.hideShimmer()
                    if (result.data.isNotEmpty()) {
                        viewModel.updatePictures(result.data.first().body.pictures)
                        binding.productDetail = result.data.first()

                        result.data.map { d ->
                            d
                                .body
                                .attributes
                                .filter { atr -> atr.name.isNullOrEmpty().not() and atr.valueName.isNullOrEmpty().not() }
                                .forEach { attribute ->
                                val viewInflater: View = layoutInflater.inflate(
                                    R.layout.item_detail,
                                    null
                                )

                                val itemDetailBinding = ItemDetailBinding.bind(viewInflater)
                                itemDetailBinding.name.text = attribute.name.trim()
                                itemDetailBinding.value.text = attribute.valueName.trim()

                                binding.linear.addView(viewInflater)
                            }
                        }.firstOrNull()
                    }
                }

                is Resource.Failure -> {
                    val l = 1
                }
            }
        })

        binding.viewPager.offscreenPageLimit = 1
        binding.viewPager.setPageTransformer(CarouselTransformer(requireContext()))
        binding.goToML.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(itemReceived.permalink)))
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.myGalacticLeague).isVisible = false

        super.onPrepareOptionsMenu(menu)
    }
}

