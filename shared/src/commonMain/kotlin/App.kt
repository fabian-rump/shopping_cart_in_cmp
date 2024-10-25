import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import myapplication.shared.generated.resources.*
import myapplication.shared.generated.resources.Res
import myapplication.shared.generated.resources.person
import myapplication.shared.generated.resources.plant
import org.jetbrains.compose.resources.painterResource

val shoppingCartEntries = listOf(
    ShoppingCartEntry(
        id = 1,
        productIcon = Res.drawable.plant,
        productName = "Felsenkatus, 12cm",
        amount = 1,
        price = 13.99
    ),
    ShoppingCartEntry(
        id = 2,
        productIcon = Res.drawable.tomatoes,
        productName = "Tomate \"Corazon\", 150cm",
        amount = 1,
        price = 8.99
    ),
    ShoppingCartEntry(
        id = 3,
        productIcon = Res.drawable.flower,
        productName = "Gerbera Mini, Rosa",
        amount = 2,
        price = 4.95
    ),
    ShoppingCartEntry(
        id = 4,
        productIcon = Res.drawable.pot,
        productName = "HAVSON Topf, Ton, 29cm",
        amount = 1,
        price = 18.89
    ),
)

val greenColor = Color(0xFFB0D553)

@Composable
fun App() {
    MaterialTheme(
        colors = MaterialTheme.colors.copy(
            primary = Color.White
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Headline()
            ShoppingCartEntries()
            Divider()
            Sum()
            Divider()
            Checkout()
            Divider()
            BottomNavigation()
        }
    }
}

@Composable
private fun BottomNavigation() {
    BottomNavigation {
        BottomNavigationItem(
            selected = false,
            onClick = {},
            label = {
                Text(
                    text = "Start",
                    color = Color.Black
                )
            },
            icon = {
                Image(
                    modifier = Modifier.size(size = 24.dp),
                    painter = painterResource(resource = Res.drawable.home),
                    contentDescription = null,
                )
            })
        BottomNavigationItem(
            selected = true,
            onClick = {},
            label = {
                Text(
                    text = "Einkaufswagen",
                    color = greenColor,
                    fontWeight = FontWeight.Bold
                )
            },
            icon = {
                Image(
                    modifier = Modifier.size(size = 24.dp),
                    painter = painterResource(resource = Res.drawable.shopping_bag),
                    contentDescription = null,
                )
            })
        BottomNavigationItem(
            selected = false,
            onClick = {},
            label = {
                Text(
                    text = "Empfehlungen",
                    color = Color.Black
                )
            },
            icon = {
                Image(
                    modifier = Modifier.size(size = 24.dp),
                    painter = painterResource(Res.drawable.star),
                    contentDescription = null,
                )
            })
    }
}

@Composable
private fun Checkout() {
    TextButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 24.dp)
            .clip(shape = CircleShape)
            .height(height = 64.dp)
            .background(color = greenColor),
        onClick = {},
    ) {
        Text(
            text = "Zur Kasse gehen",
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun Sum() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Gesamtpreis:",
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${shoppingCartEntries.sumOf { it.price }}€",
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
private fun ColumnScope.ShoppingCartEntries() {
    Column(
        modifier = Modifier
            .verticalScroll(state = rememberScrollState())
            .weight(weight = 1f, fill = true),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        shoppingCartEntries.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 12.dp)
                    .clip(shape = CircleShape)
                    .background(Color(color = 0xFFF1F0F7)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(size = 64.dp)
                        .padding(start = 24.dp),
                    painter = painterResource(it.productIcon),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.weight(weight = 1f))

                Column(
                    modifier = Modifier.padding(end = 24.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = it.productName,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "${it.amount}x ${it.price}",
                        fontSize = 14.sp
                    )
                }

                Image(
                    modifier = Modifier
                        .padding(end = 24.dp)
                        .size(size = 18.dp),
                    painter = painterResource(resource = Res.drawable.trash),
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
private fun Headline() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Text(
                text = "Dein",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Einkaufswagen",
                color = greenColor,
                fontWeight = FontWeight.Bold
            )
        }
        Image(
            painter = painterResource(Res.drawable.person),
            contentDescription = null,
            modifier = Modifier.size(36.dp),
        )
    }
}

expect fun getPlatformName(): String