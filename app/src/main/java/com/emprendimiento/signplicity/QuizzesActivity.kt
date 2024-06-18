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
        listQuestionModel.add(QuestionModel("Selecciona la opción correcta:", mutableListOf("A","D","B","C"),"D",R.drawable.dc_d))
        listQuestionModel.add(QuestionModel("Selecciona la opción correcta:", mutableListOf("E","F","G","A"),"A",R.drawable.dc_a))
        listQuestionModel.add(QuestionModel("Selecciona la opción correcta:", mutableListOf("B","H","D","I"),"I",R.drawable.dc_i))
        listQuestionModel.add(QuestionModel("Selecciona la opción correcta:", mutableListOf("B","E","A","C"),"E",R.drawable.dc_e))
        listQuestionModel.add(QuestionModel("Selecciona la opción correcta:", mutableListOf("H","D","G","C"),"H",R.drawable.dc_h))

        val listQuestionModel2 = mutableListOf<QuestionModel>()
        listQuestionModel2.add(QuestionModel("Selecciona la opción correcta:", mutableListOf("M","L","J","I"),"M",R.drawable.dc_m))
        listQuestionModel2.add(QuestionModel("Selecciona la opción correcta:", mutableListOf("N","R","O","P"),"P",R.drawable.dc_p))
        listQuestionModel2.add(QuestionModel("Selecciona la opción correcta:", mutableListOf("Q","O","J","C"),"J",R.drawable.dc_j))
        listQuestionModel2.add(QuestionModel("Selecciona la opción correcta:", mutableListOf("G","O","K","L"),"O",R.drawable.dc_o))
        listQuestionModel2.add(QuestionModel("Selecciona la opción correcta:", mutableListOf("N","M","F","C"),"N",R.drawable.dc_n))

        val listQuestionModel3 = mutableListOf<QuestionModel>()
        listQuestionModel3.add(QuestionModel("Selecciona la opción correcta:", mutableListOf("U","T","P","W"),"U",R.drawable.dc_u))
        listQuestionModel3.add(QuestionModel("Selecciona la opción correcta:", mutableListOf("X","V","R","Z"),"R",R.drawable.dc_r))
        listQuestionModel3.add(QuestionModel("Selecciona la opción correcta:", mutableListOf("K","Q","Y","T"),"T",R.drawable.dc_t))
        listQuestionModel3.add(QuestionModel("Selecciona la opción correcta:", mutableListOf("P","Z","W","S"),"Z",R.drawable.dc_z))
        listQuestionModel3.add(QuestionModel("Selecciona la opción correcta:", mutableListOf("M","B","T","Q"),"Q",R.drawable.dc_q))


        quizModelList.add(QuizModel("1","Abecedario","Responde las siguientes preguntas!",listQuestionModel,listQuestionModel2,listQuestionModel3))
        quizModelList.add(QuizModel("2","Números","Responde las siguientes preguntas!",listQuestionModel,listQuestionModel2,listQuestionModel3))
        quizModelList.add(QuizModel("3","Colores","Responde las siguientes preguntas!",listQuestionModel,listQuestionModel2,listQuestionModel3))
        quizModelList.add(QuizModel("4","Saludos","Responde las siguientes preguntas!",listQuestionModel,listQuestionModel2,listQuestionModel3))
        quizModelList.add(QuizModel("1","Colores","Responde las siguientes preguntas!",listQuestionModel,listQuestionModel2,listQuestionModel3))
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = QuizListAdapter(quizModelList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}