package app.test.takehomeproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import app.test.takehomeproject.databinding.FragmentAmazonDetailBinding

class DetailsFragment : Fragment() {
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAmazonDetailBinding.inflate(inflater, container, false)
        binding.item = args.detailItem
        return binding.root
    }
}