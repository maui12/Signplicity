package com.emprendimiento.signplicity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.emprendimiento.signplicity.databinding.ActivityQuizBinding
import com.emprendimiento.signplicity.databinding.ScoreDialogBinding

class QuizActivity : AppCompatActivity(),View.OnClickListener {

    companion object {
        var questionModelList : List<QuestionModel> = listOf()
    }

    lateinit var binding: ActivityQuizBinding

    var selectedAnswer = ""
    var score = 0

    var currentQuestionIndex = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            answer1.setOnClickListener(this@QuizActivity)
            answer2.setOnClickListener(this@QuizActivity)
            answer3.setOnClickListener(this@QuizActivity)
            answer4.setOnClickListener(this@QuizActivity)
        }
        loadQuestions()


    }

    private fun loadQuestions() {
        selectedAnswer = ""
        if(currentQuestionIndex == questionModelList.size) {
            finishQuiz()
            return
        }
        binding.apply {
            questionIndicatorTextview.text = "Pregunta ${currentQuestionIndex+1}/${questionModelList.size}"
            questionProgressIndicator.progress = (currentQuestionIndex.toFloat() / questionModelList.size.toFloat() * 100).toInt()
            questionTextView.text = questionModelList[currentQuestionIndex].question
            image.setImageResource(questionModelList[currentQuestionIndex].imageId)
            answer1.text = questionModelList[currentQuestionIndex].option[0]
            answer2.text = questionModelList[currentQuestionIndex].option[1]
            answer3.text = questionModelList[currentQuestionIndex].option[2]
            answer4.text = questionModelList[currentQuestionIndex].option[3]
        }
    }

    override fun onClick(view: View?) {

        val clickedBtn = view as Button
        selectedAnswer = clickedBtn.text.toString()

        if(selectedAnswer == questionModelList[currentQuestionIndex].correct) {
            score++
            currentQuestionIndex++
            loadQuestions()
        } else {
            currentQuestionIndex++
            loadQuestions()
        }

    }

    private fun finishQuiz() {
        val totalQuestions = questionModelList.size
        val percentage = ((score.toFloat() / totalQuestions.toFloat()) * 100).toInt()

        val dialogBinding = ScoreDialogBinding.inflate(layoutInflater)
        dialogBinding.apply {
            scoreProgressIndicator.progress = percentage
            scoreProgressText.text = "$percentage %"
            if(percentage>50) {
                scoreTitle.text = "¡Felicitaciones!"
                scoreTitle.setTextColor(Color.GREEN)

            }
            else {
                scoreTitle.text = "Oops!"
                scoreTitle.setTextColor(Color.RED)
            }

            scoreSubtitle.text = "$score de $totalQuestions respuestas correctas"
            finishBtn.setOnClickListener() {
                finish()
            }

        }

        AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setCancelable(false)
            .show()

    }
}