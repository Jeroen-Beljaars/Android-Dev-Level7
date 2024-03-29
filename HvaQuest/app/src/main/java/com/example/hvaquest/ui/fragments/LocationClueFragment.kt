package com.example.hvaquest.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hvaquest.R
import kotlinx.android.synthetic.main.fragment_location_clue.*

/**
 * A simple [Fragment] subclass.
 */
class LocationClueFragment : Fragment() {
    private val args: LocationClueFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_clue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivClue.setImageResource(args.clueImage)

        btnNext.setOnClickListener {
            if (args.questFinished) {
                findNavController().navigate(R.id.action_locationClueFragment_to_questCompletedFragment)
            } else {
                findNavController().navigate(R.id.action_locationClueFragment_to_questionFragment)
            }
        }
    }


}
