package com.example.productfinderfromml.ui.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.paging.ExperimentalPagingApi
import androidx.transition.TransitionInflater
import coil.load
import com.example.productfinderfromml.R
import com.example.productfinderfromml.data.model.Resultado
import com.example.productfinderfromml.data.model.detail.Picture
import com.example.productfinderfromml.databinding.FragmentDetailsBinding
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

        val listPictures = mutableListOf<Picture>().apply {
            add(Picture(url = "http://http2.mlstatic.com/D_819940-MLA31003080242_062019-O.jpg"))
            add(Picture(url = "http://http2.mlstatic.com/D_780684-MLA31003091231_062019-O.jpg"))
        }
        binding.viewPager.adapter = ViewPagerAdapter(listPictures)
        binding.viewPager.offscreenPageLimit = 1
        binding.viewPager.setPageTransformer(CarouselTransformer(requireContext()))

        //binding.carousel.setData(list)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.myGalacticLeague).isVisible = false

        super.onPrepareOptionsMenu(menu)
    }
}

