package pl.madsoft.myweather.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.madsoft.myweather.common.CityNameValidator

@Composable
fun SearchBar(
    searchQuery: TextFieldValue,
    onSearchQueryChange: (TextFieldValue) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(24.dp)
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var isError by remember { mutableStateOf(false) }

    fun validateInput(input: String): Boolean {
        return CityNameValidator.isValidCityName(input)
    }

    TextField(
        value = searchQuery,

        onValueChange = { query ->
            onSearchQueryChange(query)
            isError = !validateInput(query.text) && query.text.isNotEmpty()
        },

        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(horizontal = 16.dp, vertical = 16.dp),

        shape = shape,

        label = {
            if (isError) {
                Text(
                    text = "Invalid city name.",
                    color = MaterialTheme.colorScheme.error,
                )
            } else {
                Text("Search City")
            }
        },

        leadingIcon = {
            Icon(
                Icons.Filled.Search,
                contentDescription = "Search Icon"
            )
        },

        trailingIcon = {
            if (searchQuery.text.isNotEmpty()) {
                IconButton(onClick = { onSearchQueryChange(TextFieldValue("")) }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "Clear Text",
                    )
                }
            }
        },

        singleLine = true,

        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
        ),

        isError = isError,

        textStyle = TextStyle(
            fontSize = 16f.sp,
            fontWeight = FontWeight.Normal,
            textDecoration = TextDecoration.None,
            background = Color.Transparent
        ),

        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search,
            capitalization = KeyboardCapitalization.Words
        ),

        keyboardActions = KeyboardActions(
            onSearch = {
                if (validateInput(searchQuery.text)) {
                    onSearch()
                    keyboardController?.hide()
                } else {
                    isError = true
                }
            }
        )
    )
}