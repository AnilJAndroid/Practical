package com.example.mypractical.model


data class DepartmentModel(
    var title: String?="",
    var priority: Deparment?=Deparment.Android,
    var status: Boolean=false
)