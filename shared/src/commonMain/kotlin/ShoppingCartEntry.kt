import org.jetbrains.compose.resources.DrawableResource

data class ShoppingCartEntry(
    val id: Int,
    val productIcon: DrawableResource,
    val productName: String,
    val amount: Int,
    val price: Double,
)