package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			ArtSpaceTheme {
				Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
					ArtSpaceScreen()
				}
			}
		}
	}
}

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {
	val firstArtwork = R.drawable.dunas
	val secondArtwork = R.drawable.catedral
	val thirdArtwork = R.drawable.agaete
	val fourthArtwork = R.drawable.bentayga

	var title by remember {
		mutableStateOf(R.string.dunas)
	}

	var year by remember {
		mutableStateOf(R.string.dunas_year)
	}

	var currentArtwork by remember {
		mutableStateOf(firstArtwork)
	}

	var imageResource by remember {
		mutableStateOf(currentArtwork)
	}

	Column(
		modifier = modifier
			.fillMaxWidth(),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		ArtworkDisplay(currentArtwork = currentArtwork)
		Spacer(modifier = modifier.size(16.dp))
		ArtworkTitle(title = title, year = year)
		Spacer(modifier = modifier.size(25.dp))
		Row(
			modifier = modifier.padding(horizontal = 8.dp),
			horizontalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterHorizontally)
		) {
			// Previous Button
			Button(
				onClick = {
					when (currentArtwork) {
						firstArtwork -> {
							currentArtwork = fourthArtwork
							title = R.string.bentayga
							year = R.string.bentayga_year
						}
						secondArtwork -> {
							currentArtwork = firstArtwork
							title = R.string.dunas
							year = R.string.dunas_year
						}
						thirdArtwork -> {
							currentArtwork = secondArtwork
							title = R.string.catedral
							year = R.string.catedral_year
						}
						else -> {
							currentArtwork = thirdArtwork
							title = R.string.agaete
							year = R.string.agaete_year
						}
					}
				},
				colors = ButtonDefaults.buttonColors(
					backgroundColor = Color(0xFF3B6AA7)
				),
				elevation = ButtonDefaults.elevation(
					defaultElevation = 1.dp,
					pressedElevation = 0.dp,
					focusedElevation = 0.dp
				)
			) {
				Text(
					text = "Previous",
					fontSize = 16.sp,
					fontWeight = FontWeight.Medium,
					color = Color.White
				)
			}

			// Next Button
			Button(
				onClick = {
					when (currentArtwork) {
						firstArtwork -> {
							currentArtwork = secondArtwork
							title = R.string.catedral
							year = R.string.catedral_year
						}
						secondArtwork -> {
							currentArtwork = thirdArtwork
							title = R.string.agaete
							year = R.string.agaete_year
						}
						thirdArtwork -> {
							currentArtwork = fourthArtwork
							title = R.string.bentayga
							year = R.string.bentayga_year
						}
						else -> {
							currentArtwork = firstArtwork
							title = R.string.dunas
							year = R.string.dunas_year
						}
					}
				},
				colors = ButtonDefaults.buttonColors(
					backgroundColor = Color(0xFF3B6AA7)
				),
				elevation = ButtonDefaults.elevation(
					defaultElevation = 1.dp,
					pressedElevation = 0.dp,
					focusedElevation = 0.dp
				)
			) {
				Text(
					text = "Next",
					fontSize = 16.sp,
					fontWeight = FontWeight.Medium,
					color = Color.White
				)
			}
		}
	}
}

@Composable
fun ArtworkDisplay(
	modifier: Modifier = Modifier,
	@DrawableRes currentArtwork: Int
) {
	Image(
		painter = painterResource(currentArtwork),
		contentDescription = "Image",
		modifier = modifier
			.fillMaxWidth()
			.padding(16.dp)
			.size(400.dp)
			.clip(shape = RoundedCornerShape(8.dp)),
		contentScale = ContentScale.Crop
	)
}

@Composable
fun ArtworkTitle(
	@StringRes title: Int,
	@StringRes year: Int,
	modifier: Modifier = Modifier
) {
	val lightGray = Color(0xFFEAEAEA)

	Box(
		modifier = modifier.fillMaxWidth()
			.padding(16.dp)
	) {
		Box(
			modifier = Modifier
				.background(
					color = lightGray,
					shape = RoundedCornerShape(8.dp)
				)
				.padding(16.dp)
		) {
			Column(
				modifier = Modifier.fillMaxWidth(),
				horizontalAlignment = Alignment.Start
			) {
				// Artwork title
				Text(
					text = stringResource(id = title),
					fontWeight = FontWeight.Light,
					color = Color.Black,
					fontFamily = FontFamily.Serif,
					fontSize = 24.sp
				)

				// Artwork year
				Text(
					text = "${stringResource(id = year)}",
					fontSize = 16.sp,
					fontWeight = FontWeight.Bold,
					color = Color.Black,
				)
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	ArtSpaceTheme {
		ArtSpaceScreen()
	}
}