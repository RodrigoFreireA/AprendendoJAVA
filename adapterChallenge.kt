// Classe que representa um conversor de moeda antigo, que converte USD para GBP com uma taxa fixa.
class OldCurrencyConverter {
    fun convertUSDtoGBP(amount: Double): Double {
        return amount * 0.80 // Converte USD para GBP com uma taxa de 0.80
    }
}

// Classe que atua como um adaptador para converter USD para EUR usando o conversor antigo.
class CurrencyAdapter(private val oldConverter: OldCurrencyConverter) {
    private val gbpToEurRate = 1.0625 // Taxa de conversão de GBP para EUR

    // Função para converter USD para EUR usando o adaptador
    fun convertUSDtoEUR(amount: Double): Double {
        val amountInGBP = oldConverter.convertUSDtoGBP(amount) // Converte USD para GBP
        val amountInEUR = amountInGBP * gbpToEurRate // Converte GBP para EUR usando a taxa especificada
        return amountInEUR
    }
}

fun main() {
    val input = readLine()?.toDoubleOrNull() ?: return // Lê uma entrada do usuário como um valor Double

    val oldConverter = OldCurrencyConverter() // Cria uma instância do conversor antigo
    val adapter = CurrencyAdapter(oldConverter) // Cria uma instância do adaptador, usando o conversor antigo

    val amountInEUR = adapter.convertUSDtoEUR(input) // Chama a função do adaptador para converter USD para EUR

    // Exibe os resultados
    println("USD: $input")
    println("EUR: $amountInEUR")
}
