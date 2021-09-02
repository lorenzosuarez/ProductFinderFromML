package com.example.productfinderfromml.ui.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.paging.ExperimentalPagingApi
import androidx.transition.TransitionInflater
import coil.load
import com.example.productfinderfromml.R
import com.example.productfinderfromml.core.Resource
import com.example.productfinderfromml.data.model.Resultado
import com.example.productfinderfromml.data.model.detail.Picture
import com.example.productfinderfromml.data.model.detail.ProductDetail
import com.example.productfinderfromml.databinding.FragmentDetailsBinding
import com.example.productfinderfromml.databinding.FragmentMainBinding
import com.example.productfinderfromml.presentation.DetailViewModel
import com.example.productfinderfromml.presentation.MainViewModel
import com.example.productfinderfromml.ui.ResultadoAdapter
import com.example.productfinderfromml.ui.details.viewpager.CarouselTransformer
import com.example.productfinderfromml.ui.details.viewpager.ViewPagerAdapter
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
        // Inflate the layout for this fragment
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


        viewModel.productDetail.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    val a = 2
                }
                is Resource.Success -> {
                    /*if (result.data. != 200) {
                        //binding.rvList.hide()
                        return@Observer
                    }*/
                    val a = 2
                    viewModel.updatePictures(result.data.first().body.pictures)
                }
                is Resource.Failure -> {
                    val a = 2
                }
            }
        }

        binding.viewPager.offscreenPageLimit = 1
        binding.viewPager.setPageTransformer(CarouselTransformer(requireContext()))

        //binding.carousel.setData(list)
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

