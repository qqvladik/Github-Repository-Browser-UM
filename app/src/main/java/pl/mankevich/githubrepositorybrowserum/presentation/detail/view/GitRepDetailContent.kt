package pl.mankevich.githubrepositorybrowserum.presentation.detail.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
    paddingValues: PaddingValues,
    viewState: GitRepDetailViewState
) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        viewState.gitRepDetailDto?.let { gitRep ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                TextRow(
                    key = stringResource(id = R.string.git_rep_detail_owner_text),
                    value = gitRep.ownerLogin
                )

                Divider(modifier = Modifier.padding(horizontal = 6.dp))

                TextRow(
                    key = stringResource(id = R.string.git_rep_detail_commits_number_text),
                    value = gitRep.commitsNumber.toString()
                )

                Divider(modifier = Modifier.padding(horizontal = 6.dp))

                TextRow(
                    key = stringResource(id = R.string.git_rep_detail_issues_number_text),
                    value = gitRep.issuesNumber.toString()
                )

                Divider(modifier = Modifier.padding(horizontal = 6.dp))

                Text(
                    text = stringResource(id = R.string.git_rep_detail_description_text),
                    modifier = Modifier
                        .padding(12.dp),
                    style = MaterialTheme.typography.h6
                )

                Text(
                    text = gitRep.description ?: "-",
                    modifier = Modifier
                        .padding(12.dp),
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}

@Composable
private fun TextRow(key: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 12.dp),
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