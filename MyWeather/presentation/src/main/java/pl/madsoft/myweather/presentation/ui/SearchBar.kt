package pl.madsoft.myweather.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(
    searchQuery: TextFieldValue,
    onSearchQueryChange: (TextFieldValue) -> Unit,
    onSearch: () -> Unit
) {

    Surface(
        tonalElevation = 4.dp,
        shadowElevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(horizontal = 16.dp, vertical = 16.dp),

            shape = RoundedCornerShape(24.dp),

            label = { Text("Search City") },

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

            textStyle = TextStyle(
                fontSize = 16f.sp,
                fontWeight = FontWeight.Normal,
                textDecoration = TextDecoration.None,
                background = Color.Transparent
            ),
        )
    }
}