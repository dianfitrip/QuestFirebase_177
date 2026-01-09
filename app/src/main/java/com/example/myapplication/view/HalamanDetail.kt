package com.example.myapplication.view




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailSiswaScreen(
    navigateToEditItem: (Int) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    Scaffold(
        topBar = {
            SiswaTopAppBar(
                title = stringResource(DestinasiDetail.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            val uiState = viewModel.statusUIDetail
            FloatingActionButton(
                onClick = {
                    when(uiState) {
                        is StatusUIDetail.Success ->
                            navigateToEditItem(uiState.satusiswa!!.id.toInt()) else -> {}
                    }
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.update_siswa)
                )
            }
        }, modifier = modifier
    ){ innerPadding ->
        val coroutineScope = rememberCoroutineScope()
        BodyDetailDataSiswa(
            statusUIDetail = viewModel.statusUIDetail,
            onDelete = {
                coroutineScope.launch {
                    viewModel.hapusSatuSiswa()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
private fun BodyDetailDataSiswa(
    statusUIDetail: StatusUIDetail,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }

        when(statusUIDetail) {
            is StatusUIDetail.Success -> {
                DetailDataSiswa(
                    siswa = statusUIDetail.satusiswa,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedButton(
                    onClick = { deleteConfirmationRequired = true },
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.delete))
                }

                if (deleteConfirmationRequired) {
                    DeleteConfirmationDialog(
                        onDeleteConfirm = {
                            deleteConfirmationRequired = false
                            onDelete()
                        },
                        onDeleteCancel = { deleteConfirmationRequired = false },
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
                    )
                }
            }
            is StatusUIDetail.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is StatusUIDetail.Error -> {
                Text(text = "Terjadi kesalahan saat memuat data.")
            }
        }
    }
}

@Composable
fun DetailDataSiswa(
    siswa: Siswa?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {
            BarisDetailData(
                labelResID = R.string.nama,
                itemDetail = siswa?.nama ?: "",
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
            )
            BarisDetailData(
                labelResID = R.string.alamat,
                itemDetail = siswa?.alamat ?: "",
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
            )
            BarisDetailData(
                labelResID = R.string.telpon,
                itemDetail = siswa?.telpon ?: "",
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
            )
        }
    }
}