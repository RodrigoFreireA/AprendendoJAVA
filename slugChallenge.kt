fun main() {
    val titulo = readLine() ?: ""
    val autor = readLine() ?: ""

    val slugTitulo = titulo.generateSlug()
    val slugAutor = autor.generateSlug()

    println("Slug gerado para o livro:")
    println("${slugTitulo}_${slugAutor}")
}

fun String.generateSlug(): String {
    // Remove espaços em branco no início e no final da string
    val trimmed = this.trim()

    // Substitui espaços por traços
    val slug = trimmed.replace(Regex("\\s+"), "-")

    // Remove caracteres especiais e deixa apenas letras, números, traços e sublinhados
    val cleanSlug = slug.replace(Regex("[^a-zA-Z0-9-_]"), "")

    // Converte o slug para letras minúsculas
    val lowercaseSlug = cleanSlug.lowercase()

    return lowercaseSlug
}
