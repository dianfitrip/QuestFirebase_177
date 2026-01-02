package com.example.myapplication.view.controllNavigasi


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.view.EntrySiswaScreen
import com.example.myapplication.view.HomeScreen
import com.example.myapplication.view.route.DestinasiDetail
import com.example.myapplication.view.route.DestinasiEntry
import com.example.myapplication.view.route.DestinasiHome

@Composable
fun DataSiswaApp(navController: NavHostController = rememberNavController(), modifier:
Modifier){
    HostNavigasi(navController = navController)
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
){

}