// Definição da classe Product que representa um produto
class Product(val name: String, val price: Double, val quantity: Int)

// Definição da classe CustomOrder que representa um pedido personalizado
class CustomOrder private constructor(
    val customerName: String,
    val products: List<Product>,
    val total: Double,
    val deliveryAddress: String
) {
    // Classe interna Builder para construir instâncias de CustomOrder de maneira conveniente
    class Builder {
        private var customerName: String = ""
        private var products: MutableList<Product> = mutableListOf()
        private var deliveryAddress: String = ""

        // Método para definir o nome do cliente e encadear a chamada de método (Builder Pattern)
        fun setCustomerName(name: String) = apply { customerName = name }

        // Método para adicionar um produto ao pedido e encadear a chamada de método (Builder Pattern)
        fun addProduct(product: Product) = apply { products.add(product) }

        // Método para definir o endereço de entrega e encadear a chamada de método (Builder Pattern)
        fun setDeliveryAddress(address: String) = apply { deliveryAddress = address }

        // Método para construir uma instância de CustomOrder com base nos dados do Builder
        fun build(): CustomOrder {
            // Calcula o total do pedido somando o preço de cada produto multiplicado pela quantidade
            val total = products.sumByDouble { it.price * it.quantity }
            // Instancia corretamente um CustomOrder, consolidando o Builder
            return CustomOrder(customerName, products, total, deliveryAddress)
        }
    }

    // Método para imprimir um recibo do pedido
    fun printReceipt() {
        println("${this.customerName}")
        this.products.forEachIndexed { index, product ->
            println("${index + 1}. ${product.name} | ${product.price} | ${product.quantity}")
        }
        println("Total: ${this.total}")
        println("End: ${this.deliveryAddress}")
    }
}

// Função principal
fun main() {
    // Solicita ao usuário a entrada do nome do cliente
    val customerName = readLine() ?: ""

    // Cria um Builder para construir um pedido personalizado
    val orderBuilder = CustomOrder.Builder().setCustomerName(customerName)

    // Solicita a quantidade de produtos a serem registrados
    val numProducts = readLine()?.toIntOrNull() ?: 0
    for (i in 1..numProducts) {
        // Solicita informações de cada produto: nome, preço e quantidade
        val productName = readLine() ?: ""
        val productPrice = readLine()?.toDoubleOrNull() ?: 0.0
        val productQuantity = readLine()?.toIntOrNull() ?: 0

        // Adiciona o produto ao pedido
        orderBuilder.addProduct(Product(productName, productPrice, productQuantity))
    }

    // Solicita ao usuário o endereço de entrega
    val deliveryAddress = readLine() ?: ""

    // Constrói o pedido personalizado com base nos dados fornecidos
    val customOrder = orderBuilder.setDeliveryAddress(deliveryAddress).build()

    // Imprime o recibo do pedido
    customOrder.printReceipt()
}
