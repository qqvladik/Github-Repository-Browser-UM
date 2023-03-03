package pl.mankevich.githubrepositorybrowserum.presentation.detail.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import pl.mankevich.githubrepositorybrowserum.R
import pl.mankevich.githubrepositorybrowserum.presentation.detail.GitRepDetailViewState

@Composable
fun GitRepDetailContent(
    viewState: GitRepDetailViewState,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        viewState.gitRepDetailDto?.let { gitRep ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                val noData = stringResource(id = R.string.git_rep_detail_no_data)
                val rowModifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)

                val dividerModifier = Modifier.padding(horizontal = 6.dp)

                TextRow(
                    key = stringResource(id = R.string.git_rep_detail_owner_text),
                    value = gitRep.ownerLogin,
                    modifier = rowModifier
                )

                Divider(dividerModifier)

                TextRow(
                    key = stringResource(id = R.string.git_rep_detail_commits_number_text),
                    value = gitRep.commitsNumber?.toString() ?: noData,
                    modifier = rowModifier
                )

                Divider(dividerModifier)

                TextRow(
                    key = stringResource(id = R.string.git_rep_detail_issues_number_text),
                    value = gitRep.issuesNumber.toString(),
                    modifier = rowModifier
                )

                Divider(dividerModifier)

                val descriptionModifier = Modifier.padding(12.dp)

                Text(
                    text = stringResource(id = R.string.git_rep_detail_description_text),
                    modifier = descriptionModifier,
                    style = MaterialTheme.typography.h6
                )

                Text(
                    text = gitRep.description ?: noData,
                    modifier = descriptionModifier,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}

@Composable
private fun TextRow(
    key: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(
            text = key,
            maxLines = 1,
            overflow = TextOverflow.Visible,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Start
        )

        Text(
            text = value,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.End
        )
    }
}