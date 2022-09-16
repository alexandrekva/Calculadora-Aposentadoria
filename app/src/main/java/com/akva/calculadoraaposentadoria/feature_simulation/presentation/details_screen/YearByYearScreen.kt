package com.akva.calculadoraaposentadoria.feature_simulation.presentation.details_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.akva.calculadoraaposentadoria.R
import com.akva.calculadoraaposentadoria.core.components.loading_screen.LoadingScreen
import com.akva.calculadoraaposentadoria.feature_simulation.presentation.components.YearInfoCard
import com.akva.calculadoraaposentadoria.feature_simulation.presentation.details_screen.YearByYearScreenViewState.Loaded
import com.akva.calculadoraaposentadoria.feature_simulation.presentation.details_screen.YearByYearScreenViewState.Loading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YearByYearScreen(
    detailsScreenViewModel: DetailsScreenViewModel,
    popBackStack: () -> Unit,
) {
    val yearByYearScreenViewState = detailsScreenViewModel.yearByYearScreenViewState
    val appBarState = rememberTopAppBarState()
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior(appBarState) }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SmallTopAppBar(
                title = { Text(text = stringResource(id = R.string.ano_a_ano)) },
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    IconButton(onClick = popBackStack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(
                                R.string.voltar
                            )
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when (val viewState = yearByYearScreenViewState.value) {
                is Loading -> {
                    LoadingScreen()
                    detailsScreenViewModel.getYearByYearEvolution()
                }
                is Loaded -> {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(viewState.yearList) { item ->
                            YearInfoCard(yearEvolution = item)
                        }
                    }
                }
            }
        }
    }
}