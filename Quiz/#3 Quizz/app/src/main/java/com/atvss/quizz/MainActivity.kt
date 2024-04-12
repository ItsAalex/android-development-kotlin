package com.atvss.quizz
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.atvss.quizz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var mScore = 0
    var mIndex = 0
    private val mQuestionBank = arrayOf(
        Question(R.string.question_1, true),
        Question(R.string.question_2, true),
        Question(R.string.question_3, true),
        Question(R.string.question_4, true),
        Question(R.string.question_5, true),
        Question(R.string.question_6, false),
        Question(R.string.question_7, true),
        Question(R.string.question_8, false),
        Question(R.string.question_9, true),
        Question(R.string.question_10, true),
        Question(R.string.question_11, false),
        Question(R.string.question_12, false),
        Question(R.string.question_13, true)
    )

    private val progressBarIncrement = Math.ceil(100.0 / mQuestionBank.size).toInt()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener { checkAnswer(true) }
        binding.falseButton.setOnClickListener { checkAnswer(false) }

        updateQuestion()
    }

    private fun updateQuestion() {
        if (mIndex < mQuestionBank.size) {
            binding.questionTextView.setText(mQuestionBank[mIndex].questionID)
            binding.progressBar.progress = mIndex * progressBarIncrement
            binding.score.text = "Score $mScore/${mQuestionBank.size}"
        } else {
            showFinalScore()
        }
    }

    private fun checkAnswer(userSelectedTrue: Boolean) {
        if (mQuestionBank[mIndex].answer == userSelectedTrue) {
            mScore++
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show()
        }

        mIndex++
        updateQuestion()
    }

    private fun showFinalScore() {
        AlertDialog.Builder(this)
            .setTitle("Game Over")
            .setMessage("You scored $mScore points")
            .setPositiveButton("Close Application") { dialog, which -> finish() }
            .setCancelable(false)
            .show()
    }
}