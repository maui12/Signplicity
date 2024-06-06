package com.emprendimiento.signplicity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.emprendimiento.signplicity.databinding.ActivityQuizzesBinding
import com.google.firebase.database.FirebaseDatabase

class QuizzesActivity : AppCompatActivity() {

    lateinit var binding:ActivityQuizzesBinding
    lateinit var quizModelList: MutableList<QuizModel>
    lateinit var adapter: QuizListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizzesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        quizModelList = mutableListOf()
        getDataFromFirebase()
    }

    private fun getDataFromFirebase() {


        val listQuestionModel = mutableListOf<QuestionModel>()
        listQuestionModel.add(QuestionModel("Hola como estas", mutableListOf("bien","bien","bien","bien"),"bien",R.drawable.signplicity_logo))
        listQuestionModel.add(QuestionModel("como se llama", mutableListOf("gato","libro","auto","palo"),"libro",R.drawable.dictionary_icon))

        val listQuestionModel2 = mutableListOf<QuestionModel>()
        listQuestionModel2.add(QuestionModel("como se llama", mutableListOf("gato","libro","auto","control"),"control",R.drawable.games_icon))

        val listQuestionModel3 = mutableListOf<QuestionModel>()
        listQuestionModel3.add(QuestionModel("como se llama la app", mutableListOf("signplicity","no","mal","bien"),"signplicity",R.drawable.signplicity_logo))

        quizModelList.add(QuizModel("1","letras","muchas letras",listQuestionModel,listQuestionModel2,listQuestionModel3))
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = QuizListAdapter(quizModelList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}