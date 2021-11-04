package com.example.msapps.utils

//Enum class that holds the app state -> providing a single source of truth regarding the current state.
enum class States {
    Idle,
    Loading,
    AddedToFavorites,
    DeletedFromFavorites
}