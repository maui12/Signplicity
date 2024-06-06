package com.emprendimiento.signplicity

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.emprendimiento.signplicity.databinding.QuizItemRecyclerViewBinding

class QuizListAdapter(private val quizModelList: List<QuizModel>) : RecyclerView.Adapter<QuizListAdapter.MyViewHolder>(){

    class MyViewHolder(private val binding: QuizItemRecyclerViewBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(model : QuizModel) {
            binding.apply {
                quizTitleText.text = model.title
                quizSubtitleText.text = model.subtitle

                root.findViewById<Button>(R.id.button1).setOnClickListener() {
                    val intent = Intent(root.context,QuizActivity::class.java)
                    QuizActivity.questionModelList = model.questionList
                    root.context.startActivity(intent)
                }

                root.findViewById<Button>(R.id.button2).setOnClickListener() {
                    val intent = Intent(root.context,QuizActivity::class.java)
                    QuizActivity.questionModelList = model.questionList2
                    root.context.startActivity(intent)
                }

                root.findViewById<Button>(R.id.button3).setOnClickListener() {
                    val intent = Intent(root.context,QuizActivity::class.java)
                    QuizActivity.questionModelList = model.questionList3
                    root.context.startActivity(intent)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = QuizItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return quizModelList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(quizModelList[position])
    }
}