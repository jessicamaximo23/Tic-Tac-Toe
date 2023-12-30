package com.example.tictactoe

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    enum class Turn{
        NOUGHT,
        CROSS
    }

    private var firstTurn = Turn.CROSS
    private var currentTurn = Turn.CROSS

    //variable for show the score
    private var crossesScore = 0
    private var noughtsScore = 0

    private var boardList = mutableListOf<Button>()

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Create Function
        initBoard()
    }
    private fun initBoard() {
        //put all buttons inside this function
        boardList.add(binding.btna1)
        boardList.add(binding.btna2)
        boardList.add(binding.btna3)
        boardList.add(binding.btnb1)
        boardList.add(binding.btnb2)
        boardList.add(binding.btnb3)
        boardList.add(binding.btnc1)
        boardList.add(binding.btnc2)
        boardList.add(binding.btnc3)

    }

    fun boardTapped(view: View){
        //inside this function put the view is not a button
        if(view !is Button)
            return
       addToBoard(view)

        //Put all the message for the winner
        //Counter for winners
        if(checkForVictory(NOUGHT)){
            noughtsScore++
            result("You Win X !!!")
        }

        if(checkForVictory(CROSS)){
            crossesScore++
            result("You Win O !!!")
        }

    //Create a Function
    if(fullBoard()){
        result("Draw")
    }
   }
    //Put all scenarios for the game
    private fun checkForVictory(s: String): Boolean {
        //Horizontal Victory
        if(match(binding.btna1,s) && match(binding.btna2,s) && match(binding.btna3,s))
            return true
        if(match(binding.btnb1,s) && match(binding.btnb2,s) && match(binding.btnb3,s))
            return true
        if(match(binding.btnc1,s) && match(binding.btnc2,s) && match(binding.btnc3,s))
            return true

        //Vertical Victory
        if(match(binding.btna1,s) && match(binding.btnb1,s) && match(binding.btnc1,s))
            return true
        if(match(binding.btna2,s) && match(binding.btnb2,s) && match(binding.btnc2,s))
            return true
        if(match(binding.btna3,s) && match(binding.btnb3,s) && match(binding.btnc3,s))
            return true

        //Diagonal Victory
        if(match(binding.btna1,s) && match(binding.btnb2,s) && match(binding.btnc3,s))
            return true
        if(match(binding.btna3,s) && match(binding.btnb2,s) && match(binding.btnc1,s))
            return true


        return false
    }

    private fun match (button: Button, symbol : String): Boolean = button.text == symbol


    //Create Function for reset the board and update
    private fun result(title: String) {
        val message = "\nNoughts $noughtsScore \n\nCrosses $crossesScore"

        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Reset")
            { _,_ ->
                resetBoard()
            }
            .setCancelable(false)
            .show()
    }

    private fun resetBoard() {
        for (button in boardList)
        {
            button.text = ""
        }

        if (firstTurn == Turn.NOUGHT)
            firstTurn = Turn.CROSS
        else if  (firstTurn == Turn.CROSS)
            firstTurn = Turn.NOUGHT

        currentTurn = firstTurn
        setTurnLabel()

    }

    private fun fullBoard(): Boolean {

        for(button in boardList){
            if(button.text == "")
                return false

        }
        return true
    }

    private fun addToBoard(button: Button){
        if(button.text != "")
            return

        if(currentTurn == Turn.NOUGHT){
            button.text = NOUGHT
            currentTurn = Turn.CROSS
        }
        else if (currentTurn == Turn.CROSS){
            button.text = CROSS
            currentTurn = Turn.NOUGHT
        }

        //Create function
        setTurnLabel()
    }

    private fun setTurnLabel() {
        var turnText = ""

        if(currentTurn == Turn.CROSS)
            turnText = "Turn $CROSS"

        else if (currentTurn == Turn.NOUGHT)
                turnText = "Turn $NOUGHT"

    }

    companion object{
        const val  NOUGHT = "O"
        const val  CROSS = "X"
    }
}