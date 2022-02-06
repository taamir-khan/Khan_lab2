package com.example.a2022_01_26b.controllers

import com.example.a2022_01_26b.model.AllQuestions


class NextQuestion {

    private val allQuestions: AllQuestions = AllQuestions()

    private var question: Int = 0
    private val total_qs: Int = allQuestions.allQuestions.size


    public fun linearNextQuestion(): Int {
        question = (question+1) % total_qs
        return allQuestions.allQuestions[question].index
    }

    public fun pseudoRandomNextQuestion(): Int {
        question = (question+1) % total_qs
        return allQuestions.allQuestions[question].index
    }

    public fun getIndex(): Int {
        return (question) % total_qs
    }

}