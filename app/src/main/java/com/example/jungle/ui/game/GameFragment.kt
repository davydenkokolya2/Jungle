package com.example.jungle.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.jungle.databinding.FragmentGameBinding
import com.example.jungle.ui.NavigationViewModel
import com.example.jungle.ui.statistics.StatisticsViewModel
import com.example.jungle.util.Constants
import com.example.jungle.util.Navigation
import kotlinx.coroutines.launch
import java.lang.Integer.max
import kotlin.random.Random

class GameFragment : Fragment() {

    companion object {
        fun newInstance() = GameFragment()
    }

    private val statisticsViewModel: StatisticsViewModel by activityViewModels()
    private lateinit var binding: FragmentGameBinding
    private val gameViewModel: GameViewModel by activityViewModels()
    private val navigationViewModel: NavigationViewModel by activityViewModels()

    //private val numberOfForbiddenSquares = Random.nextInt(4) + 2
    private var countOfForbiddenSquares = 0
    private var clickItem: View? = null
    private var coordinate = -1 to -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        for (i in 0..2)
            for (j in 0..4) {
                generateButtonItem(i, j)
                generateItem(i, j)
            }
        statisticsViewModel.loadState(0)
        binding.btnGameBack.setOnClickListener {
            navigationViewModel.loadState(Navigation.MENU)
        }
        binding.ivItem00.setOnClickListener {
            checkClick(it, 0, 0)
        }
        binding.ivItem01.setOnClickListener {
            checkClick(it, 0, 1)
        }
        binding.ivItem02.setOnClickListener {
            checkClick(it, 0, 2)
        }
        binding.ivItem03.setOnClickListener {
            checkClick(it, 0, 3)
        }
        binding.ivItem04.setOnClickListener {
            checkClick(it, 0, 4)
        }
        binding.ivItem10.setOnClickListener {
            checkClick(it, 1, 0)
        }
        binding.ivItem11.setOnClickListener {
            checkClick(it, 1, 1)
        }
        binding.ivItem12.setOnClickListener {
            checkClick(it, 1, 2)
        }
        binding.ivItem13.setOnClickListener {
            checkClick(it, 1, 3)
        }
        binding.ivItem14.setOnClickListener {
            checkClick(it, 1, 4)
        }
        binding.ivItem20.setOnClickListener {
            checkClick(it, 2, 0)
        }
        binding.ivItem21.setOnClickListener {
            checkClick(it, 2, 1)
        }
        binding.ivItem22.setOnClickListener {
            checkClick(it, 2, 2)
        }
        binding.ivItem23.setOnClickListener {
            checkClick(it, 2, 3)
        }
        binding.ivItem24.setOnClickListener {
            checkClick(it, 2, 4)
        }
        lifecycleScope.launch {
            statisticsViewModel.stateStatistics.collect {
                binding.tvGameScore.text = it.toString()
            }
        }
        lifecycleScope.launch {
            gameViewModel.stateGame.collect {
                if (it > 25)
                    navigationViewModel.loadState(Navigation.WIN)
            }
        }
        return binding.root
    }

    fun generateItem(i: Int, j: Int) {
        when (i) {
            0 -> when (j) {
                0 -> binding.ivItem00.setImageResource(randomItem(i, j))
                1 -> binding.ivItem01.setImageResource(randomItem(i, j))
                2 -> binding.ivItem02.setImageResource(randomItem(i, j))
                3 -> binding.ivItem03.setImageResource(randomItem(i, j))
                4 -> binding.ivItem04.setImageResource(randomItem(i, j))
            }
            1 -> when (j) {
                0 -> binding.ivItem10.setImageResource(randomItem(i, j))
                1 -> binding.ivItem11.setImageResource(randomItem(i, j))
                2 -> binding.ivItem12.setImageResource(randomItem(i, j))
                3 -> binding.ivItem13.setImageResource(randomItem(i, j))
                4 -> binding.ivItem14.setImageResource(randomItem(i, j))
            }
            2 -> when (j) {
                0 -> binding.ivItem20.setImageResource(randomItem(i, j))
                1 -> binding.ivItem21.setImageResource(randomItem(i, j))
                2 -> binding.ivItem22.setImageResource(randomItem(i, j))
                3 -> binding.ivItem23.setImageResource(randomItem(i, j))
                4 -> binding.ivItem24.setImageResource(randomItem(i, j))
            }
        }
    }

    fun generateButtonItem(i: Int, j: Int) {
        when (i) {
            0 -> when (j) {
                0 -> binding.ivSquare00.setImageResource(randomButtonItem(i, j))
                1 -> binding.ivSquare01.setImageResource(randomButtonItem(i, j))
                2 -> binding.ivSquare02.setImageResource(randomButtonItem(i, j))
                3 -> binding.ivSquare03.setImageResource(randomButtonItem(i, j))
                4 -> binding.ivSquare04.setImageResource(randomButtonItem(i, j))
            }
            1 -> when (j) {
                0 -> binding.ivSquare10.setImageResource(randomButtonItem(i, j))
                1 -> binding.ivSquare11.setImageResource(randomButtonItem(i, j))
                2 -> binding.ivSquare12.setImageResource(randomButtonItem(i, j))
                3 -> binding.ivSquare13.setImageResource(randomButtonItem(i, j))
                4 -> binding.ivSquare14.setImageResource(randomButtonItem(i, j))
            }
            2 -> when (j) {
                0 -> binding.ivSquare20.setImageResource(randomButtonItem(i, j))
                1 -> binding.ivSquare21.setImageResource(randomButtonItem(i, j))
                2 -> binding.ivSquare22.setImageResource(randomButtonItem(i, j))
                3 -> binding.ivSquare23.setImageResource(randomButtonItem(i, j))
                4 -> binding.ivSquare24.setImageResource(randomButtonItem(i, j))
            }
        }
    }


    private fun randomButtonItem(i: Int, j: Int): Int {
        if (countOfForbiddenSquares < 5) {
            val random = Random.nextInt(3)
            if (random > 0) {
                countOfForbiddenSquares++
                Constants.listOfImageView[i][j] = 1
            } else
                Constants.listOfImageView[i][j] = 0
            return Constants.mapOfButtons[random]
        } else {
            Constants.listOfImageView[i][j] = 0
            return Constants.mapOfButtons[0]
        }
    }

    private fun randomItem(i: Int, j: Int): Int {
        if (Constants.listOfImageView[i][j] == 1) {
            return Constants.mapOfItems[5]
        } else {
            val random = Random.nextInt(5)
            //Log.d("test", random.toString())
            Constants.listOfImageView[i][j] = random + 2
            return Constants.mapOfItems[random]
        }
    }

    private fun checkClick(view: View, i: Int, j: Int) {
        if (clickItem != null && Constants.listOfImageView[i][j] != 1) {
            swapImage(view as ImageView, clickItem as ImageView)
            swapListOfImageView(coordinate, i to j)
            checkPlayingField()
            checkLose()

            clickItem = null
            coordinate = -1 to -1
        } else {
            if (Constants.listOfImageView[i][j] != 1) {
                clickItem = view
                checkPlayingField()
                checkLose()
                coordinate = i to j
            }

        }
    }

    private fun swapImage(imageView1: ImageView, imageView2: ImageView) {
        val buffer = imageView1.drawable
        imageView1.setImageDrawable(imageView2.drawable)
        imageView2.setImageDrawable(buffer)
    }

    private fun swapListOfImageView(coordinate1: Pair<Int, Int>, coordinate2: Pair<Int, Int>) {
        val bufferCoordinate = Constants.listOfImageView[coordinate1.first][coordinate1.second]
        Constants.listOfImageView[coordinate1.first][coordinate1.second] =
            Constants.listOfImageView[coordinate2.first][coordinate2.second]
        Constants.listOfImageView[coordinate2.first][coordinate2.second] = bufferCoordinate
    }

    private fun checkPlayingField() {
        for (j in 0..4)
            if (Constants.listOfImageView[0][j] == Constants.listOfImageView[1][j] && Constants.listOfImageView[2][j] == Constants.listOfImageView[1][j] && Constants.listOfImageView[1][j] != 1) {
                generateItem(0, j)
                generateItem(1, j)
                generateItem(2, j)
                statisticsViewModel.loadState(statisticsViewModel.stateStatistics.value + 3)
                gameViewModel.loadState(gameViewModel.stateGame.value + 1)
            }
        for (i in 0..2)
            for (j in 1..3)
                if (Constants.listOfImageView[i][j] == Constants.listOfImageView[i][j - 1] && Constants.listOfImageView[i][j] == Constants.listOfImageView[i][j + 1] && Constants.listOfImageView[i][j] != 1) {
                    generateItem(i, j - 1)
                    generateItem(i, j)
                    generateItem(i, j + 1)
                    statisticsViewModel.loadState(statisticsViewModel.stateStatistics.value + 3)
                    gameViewModel.loadState(gameViewModel.stateGame.value + 1)
                }
    }

    private fun checkLose() {
        var list = mutableListOf(0, 0, 0, 0, 0, 0, 0, 0)
        var maxValue = 0

        for (i in 0..2)
            for (j in 0..4) {
                if (Constants.listOfImageView[i][j] != 1) {
                    list[Constants.listOfImageView[i][j]] += 1
                    maxValue = max(maxValue, list[Constants.listOfImageView[i][j]])
                }
            }
        if (maxValue < 3)
            navigationViewModel.loadState(Navigation.LOSE)
    }
}