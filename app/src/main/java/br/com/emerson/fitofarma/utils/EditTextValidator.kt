package br.com.emerson.fitofarma.utils

import android.text.InputType
import android.util.Patterns
import android.widget.EditText

class EditTextValidator {
    companion object {
        fun isValid(editText: EditText): Boolean {
            if (isURLType(editText.inputType)) {
                return isURLValid(editText)
            }
            return !isEmpty(editText)
        }

        private fun isEmpty(editText: EditText): Boolean {
            if (editText.text.isEmpty()) {
                editText.error = "Campo obrigatório!"
                return true
            }
            return false
        }

        private fun isURLValid(editText: EditText): Boolean {
            if (isEmpty(editText)) return false

            val url = editText.text.toString()
            if (!Patterns.WEB_URL.matcher(url).matches()) {
                editText.error = "URL inválida!"
                return false
            }

            return true
        }

        private fun isURLType(inputType: Int): Boolean {
            return (inputType and InputType.TYPE_MASK_CLASS == InputType.TYPE_CLASS_TEXT &&
                    inputType and InputType.TYPE_MASK_VARIATION == InputType.TYPE_TEXT_VARIATION_URI)
        }
    }
}