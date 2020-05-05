package com.example.dndapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import kotlin.random.Random

class dice_roller : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dice_roller, container, false)

        // the valid types of dice
        val typesOfDice = arrayOf("4", "6", "8", "10", "12", "20", "100")
        var diceIndex = 0

        var rollType = arrayOf("1", "4")

        // 1d4, 2d6, etc.
        val rollTypeTextView: TextView? = view?.findViewById(R.id.roll)

        // result of the roll
        val rollResultTextView: TextView? = view?.findViewById(R.id.rollResult)

        // the roll button
        val rollButton: Button? = view?.findViewById(R.id.rollButtton)

        // seek bar for number of dice
        val numDiceBar: SeekBar? = view?.findViewById(R.id.seekBar)
        numDiceBar?.max = 10

        // plus and minus buttons
        val plusButton: Button? = view?.findViewById(R.id.plus)
        val minusButton: Button? = view?.findViewById(R.id.minus)

        if (diceIndex == 0){
            minusButton?.isEnabled = false
        }
        if (diceIndex == 6){
            plusButton?.isEnabled = false
        }

        plusButton?.setOnClickListener(){
            if (diceIndex != 0){
                minusButton?.isEnabled = true
            }
            if (diceIndex != 6){
                plusButton?.isEnabled = true
            }

            diceIndex++
            rollType[1] = typesOfDice[diceIndex]
            rollTypeTextView?.text = makeString(rollType)

            if (diceIndex == 0){
                minusButton?.isEnabled = false
            }
            if (diceIndex == 6){
                plusButton?.isEnabled = false
            }
        }

        minusButton?.setOnClickListener(){
            if (diceIndex != 0){
                minusButton?.isEnabled = true
            }
            if (diceIndex != 6){
                plusButton?.isEnabled = true
            }

            diceIndex--
            rollType[1] = typesOfDice[diceIndex]
            rollTypeTextView?.text = makeString(rollType)

            if (diceIndex == 0){
                minusButton?.isEnabled = false
            }
            if (diceIndex == 6){
                plusButton?.isEnabled = false
            }
        }

        rollButton?.setOnClickListener(){
            var roll = rollDice(rollType[0].toInt(), rollType[1].toInt());
            rollResultTextView?.text = roll.toString()
        }

        numDiceBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                rollType[0] = progress.toString()
                rollTypeTextView?.text = makeString(rollType)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // intentionally blank
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // intentionally blank
            }
        })

        return view
    }

    private fun makeString(roll: Array<String>): String {
        return roll[0] + " d " + roll[1]
    }

    private fun rollDice(numDice: Int, dieType: Int): Int {
        var result = 0

        for (i in 1..numDice){
            result += Random.nextInt(1, dieType + 1)
        }

        return result;
    }
}
