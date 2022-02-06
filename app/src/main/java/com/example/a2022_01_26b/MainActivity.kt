package com.example.a2022_01_26b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.a2022_01_26b.controllers.NextQuestion
import com.example.a2022_01_26b.model.AllQuestions
import com.example.a2022_01_26b.model.Score

class MainActivity : AppCompatActivity() {

    var trueButton: Button? = null
    var falseButton: Button? = null
    var nextButton: Button? = null
    var questionText: TextView? = null
    var scoreText: TextView? = null
    var score: Int? = 0

    val nextQuestion: NextQuestion = NextQuestion()

    private val toScoreActivity: TextView
        get() = findViewById(R.id.button_to_score_activity)

    private val buttonToScoreActivity: Button
        get() = findViewById(R.id.button_to_score_activity)

    var total_score: Score = Score()

    var questions: AllQuestions = AllQuestions()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intent?.let {
            val myText = it.getStringExtra("FROM_SCORE")
            toScoreActivity.setText(myText)
        }

        buttonToScoreActivity.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                Intent(baseContext, ScoreActivity::class.java).also {
                        scoreActivity ->
                            scoreActivity.putExtra("FROM_MAIN", score.toString())
                            startActivity(scoreActivity)
                }
                total_score.reset()
            }
        })



        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionText = findViewById(R.id.questionTextView)
        scoreText = findViewById(R.id.score_activity_label)
        scoreText?.setText("Score: $score")


        trueButton?.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                if (questions.allQuestions[nextQuestion.getIndex()].isTrue) {
                    score = total_score.inc()
                    scoreText?.setText("Score: $score")
                    Log.i("BUGGED", nextQuestion.getIndex().toString())
                    Log.i("BUGGEDSC", score.toString())
                    Toast.makeText(baseContext,"Clicked TRUE", Toast.LENGTH_SHORT).show()
                }
                else {
                    score = total_score.dec()
                    scoreText?.setText("Score: $score")
                    Toast.makeText(baseContext, "Incorrect", Toast.LENGTH_SHORT).show()
                }
                val nextQuestionIndex = nextQuestion.linearNextQuestion()
                questionText?.setText(nextQuestionIndex)

            }
        })

        falseButton?.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                if (questions.allQuestions[nextQuestion.getIndex()].isTrue){
                    score = total_score.dec()
                    scoreText?.setText("Score: $score")
                    Log.i("BUGGED", nextQuestion.getIndex().toString())
                    Log.i("BUGGEDSC", score.toString())
                    Toast.makeText(baseContext,"Clicked FALSE", Toast.LENGTH_SHORT).show()
                }
                else {
                    score = total_score.inc()
                    scoreText?.setText("Score: $score")
                    Toast.makeText(baseContext,"Clicked FALSE", Toast.LENGTH_SHORT).show()
                }
                val nextQuestionIndex = nextQuestion.linearNextQuestion()
                questionText?.setText(nextQuestionIndex)
            }
        })

        nextButton?.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                score = total_score.skip_question()
                scoreText?.setText("Score: $score")
                val nextQuestionIndex = nextQuestion.linearNextQuestion()
                questionText?.setText(nextQuestionIndex )
                Toast.makeText(baseContext,"Clicked NEXT", Toast.LENGTH_SHORT).show()
            }
        })
    }
}