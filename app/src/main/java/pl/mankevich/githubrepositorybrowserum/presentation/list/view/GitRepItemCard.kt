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
import pl.mankevich.githubrepositorybrowserum.data.model.remote.dto.GitRepSimpleDto

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GitRepItemCard(
    gitRepSimpleDto: GitRepSimpleDto,
    onDetailClick: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        onClick = onDetailClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                gitRepSimpleDto.name ?: "Name unavailable",//TODO name должен быть не null, в маппере еще исключение ес что кидать
                style = MaterialTheme.typography.h6, maxLines = 1, overflow = TextOverflow.Ellipsis
            )
        }
    }
}
