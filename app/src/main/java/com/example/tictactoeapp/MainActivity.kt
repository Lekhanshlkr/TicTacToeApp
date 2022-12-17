package com.example.tictactoeapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buClick(view: View) {

        val buSelected:Button = view as Button
        var cellId:Int =0
        when(buSelected.id){
            R.id.bu1 -> cellId=1
            R.id.bu2 -> cellId=2
            R.id.bu3 -> cellId=3
            R.id.bu4 -> cellId=4
            R.id.bu5 -> cellId=5
            R.id.bu6 -> cellId=6
            R.id.bu7 -> cellId=7
            R.id.bu8 -> cellId=8
            R.id.bu9 -> cellId=9
        }
        //Log.d("buClick:", buSelected.id.toString())
        //Log.d("buClick: cellId: ",cellId.toString())

        playGame(cellId,buSelected)
    }

    var activePlayer = 1
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    var p1winscount = 0
    var p2winscount = 0

    fun playGame(cellId:Int, buSelected:Button){

        if(activePlayer==1){
            buSelected.text = "X"
            buSelected.setBackgroundColor(Color.BLUE)
            player1.add(cellId)
            activePlayer = 2
            autoPlay()
        }
        else if(activePlayer==2){
            buSelected.text = "O"
            buSelected.setBackgroundColor(Color.GREEN)
            player2.add(cellId)
            activePlayer = 1

        }
        buSelected.isEnabled = false
        checkWinner()
    }

    fun checkWinner(){
        var winner = -1

        //row 1
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner = 1
        }
        else if(player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner = 2
        }

        //row 2
        else if(player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner = 1
        }
        else if(player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner = 2
        }

        //row 3
        else if(player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner = 1
        }
        else if(player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner = 2
        }

        //col 1
        else if(player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner = 1
        }
        else if(player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner = 2
        }

        //col 2
        else if(player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner = 1
        }
        else if(player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner = 2
        }

        //col 3
        else if(player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner = 1
        }
        else if(player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner = 2
        }

        //left diag
        else if(player1.contains(1) && player1.contains(5) && player1.contains(9)){
            winner = 1
        }
        else if(player2.contains(1) && player2.contains(5) && player2.contains(9)){
            winner = 2
        }

        //right diag
        else if(player1.contains(3) && player1.contains(5) && player1.contains(7)){
            winner = 1
        }
        else if(player2.contains(3) && player2.contains(5) && player2.contains(7)){
            winner = 2
        }

        if(winner == 1){
            p1winscount += 1
            Toast.makeText(this,"Player 1 wins the Game!",Toast.LENGTH_LONG).show()
            //restart the app after 2 seconds
            restartGame()
        }
        else if(winner == 2){
            p2winscount += 1
            Toast.makeText(this,"Player 2 wins the Game!",Toast.LENGTH_LONG).show()
            //restart the app after 2 seconds
            restartGame()
        }
    }

    fun autoPlay(){
        // function to randomly select a cell by computer
        var emptyCells = ArrayList<Int>()

        for(cellId in 1..9){
            if(!(player1.contains(cellId) || player2.contains(cellId))){
                emptyCells.add(cellId)
            }
        }
        if(emptyCells.size==0){
            restartGame()
        }

        val r = Random(1)
        val randIndex:Int = r.nextInt(emptyCells.size)
        val cellId:Int = emptyCells[randIndex]

        var buSelected:Button?
        buSelected = when(cellId) {
            1 -> bu1
            2 -> bu2
            3 -> bu3
            4 -> bu4
            5 -> bu5
            6 -> bu6
            7 -> bu7
            8 -> bu8
            9 -> bu9
            else ->{bu1}
        }
        playGame(cellId,buSelected)
    }

    fun restartGame() {
        activePlayer = 1
        player1.clear()
        player2.clear()

        Handler(Looper.getMainLooper()).postDelayed({

        for (cellId in 1..9) {
            var buSelected: Button = when (cellId) {
                1 -> bu1
                2 -> bu2
                3 -> bu3
                4 -> bu4
                5 -> bu5
                6 -> bu6
                7 -> bu7
                8 -> bu8
                9 -> bu9
                else -> {
                    bu1
                }
            }
            buSelected!!.text = ""
            buSelected.isEnabled = true
            buSelected!!.setBackgroundColor(Color.WHITE)
        }
        },2000)

        Toast.makeText(this, "Player1 : $p1winscount , Player2: $p2winscount", Toast.LENGTH_LONG)
            .show()
    }
}