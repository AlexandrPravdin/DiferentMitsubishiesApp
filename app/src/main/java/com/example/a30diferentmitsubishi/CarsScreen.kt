package com.example.a30diferentmitsubishi

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30diferentmitsubishi.Data.Car
import com.example.a30diferentmitsubishi.Model.cars
import com.example.compose.DifferentMitsubishiTheme

@Composable
fun CarScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    cars: List<Car>
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier.background(color = MaterialTheme.colorScheme.background)
    )

    {
        items(cars) {
            CarCard(car = it, modifier = modifier)
        }
    }
}


@Composable
fun CarCard(
    modifier: Modifier = Modifier, car: Car
) {
    var expanded: Boolean by remember {
        mutableStateOf(false)
    }
    val color by animateColorAsState(
        if (!expanded) {
            MaterialTheme.colorScheme.onPrimaryContainer
        } else {
            MaterialTheme.colorScheme.error
        }, label = ""
    )
    Card(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.medium))
            .fillMaxWidth()
            .clickable {
                expanded = !expanded
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),

        ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.medium))
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                ),
            //verticalAlignment = Alignment.CenterVertically
        ) {
            CarInformation(
                carName = car.name,
                carDescription = car.description,
                carNumber = car.number,
                expanded = expanded,
                color = color
            )
            if (expanded) {
                CarImage(
                    modifier = modifier.fillMaxWidth(),
                    carImage = car.image,
                    carName = car.name,
                )
            }
        }
    }
}

@Composable
fun CarInformation(
    modifier: Modifier = Modifier,
    @StringRes carName: Int,
    @StringRes carDescription: Int,
    carNumber: Int,
    expanded: Boolean,
    color: Color
) {
    Column(modifier = modifier) {
        Text(
            text = "Car $carNumber",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        Text(
            text = stringResource(id = carName),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.small)),
            color = color
        )
        if (expanded) {
            Text(
                text = stringResource(id = carDescription),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.medium)),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

    }
}


@Composable
fun CarImage(
    modifier: Modifier = Modifier, @DrawableRes carImage: Int, @StringRes carName: Int
) {
    Image(
        painter = painterResource(id = carImage),
        contentDescription = stringResource(id = carName),
        modifier = modifier.clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun CardPreview() {
    DifferentMitsubishiTheme {
        CarCard(
            car = Car(
                1,R.string.car_1_name,
                R.string.car_1_description,
                R.drawable.car_1_image
            )
        )
    }

}

@Preview
@Composable
fun ScreenPreview() {
    DifferentMitsubishiTheme {
        CarScreen(cars = cars)
    }
}