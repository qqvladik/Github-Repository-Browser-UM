package pl.mankevich.githubrepositorybrowserum.presentation.list.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepSimpleDto

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GitRepItemCard(
    gitRepSimpleDto: GitRepSimpleDto,
    modifier: Modifier = Modifier,
    onDetailClick: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        onClick = onDetailClick,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                gitRepSimpleDto.name,
                style = MaterialTheme
                    .typography
                    .body2,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
