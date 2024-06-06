package com.emprendimiento.signplicity

data class QuizModel(

    val id : String,
    val title : String,
    val subtitle : String,
    val questionList : List<QuestionModel>,
    val questionList2 : List<QuestionModel>,
    val questionList3 : List<QuestionModel>

) {
    constructor() : this("","","", emptyList(),emptyList(),emptyList())
}

data class QuestionModel(
    val question: String,
    val option : List<String>,
    val correct : String,
    val imageId : Int
) {
    constructor() : this("",emptyList(),"",0)
}
