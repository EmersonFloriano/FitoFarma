package br.com.emerson.fitofarma.data.database

import com.google.firebase.firestore.FirebaseFirestore

class FirestoreHelper {
    companion object {
        fun getInstance(): FirebaseFirestore {
            return FirebaseFirestore.getInstance()
        }
    }
}