import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coctails.R
import com.example.coctails.entity.Recipe
import com.example.coctails.presentation.Back

@Composable
fun DetailScreen(
    recipe: Recipe,
    onEditClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.oldfashioned),
            contentDescription = null,
            modifier = Modifier
                .height(500.dp),
            contentScale = ContentScale.Crop
        )
        Back(onEditClick)
        Surface(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 300.dp),
            color = Color.White,
            shape = RoundedCornerShape(40.dp)
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
            ) {
                Text(
                    text = recipe.title,
                    fontSize = 32.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                if (recipe.recipe?.isNotEmpty() == true) {
                    Text(
                        text = recipe.description!!,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(6.dp),
                    )
                }
                if (recipe.ingredients!!.isNotEmpty()) {
                    Text(
                        text = recipe.ingredients.joinToString("\n") {
                            it
                        },
                        fontSize = 16.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(6.dp),
                    )
                }

                if (recipe.recipe!!.isNotEmpty()) {
                    Text(
                        text = "Recipe:",
                        fontSize = 16.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = recipe.recipe,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(6.dp),
                    )
                }

                Button(
                    onClick = onEditClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                ) {
                    Text(text = "Edit")
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DetailPreview() {
//    CoctailsTheme {
//        val onEditClick: () -> Unit = {}
//
//        DetailScreen(onEditClick)
//    }
//}