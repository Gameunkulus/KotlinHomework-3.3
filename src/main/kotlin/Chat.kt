data class Chat(
    val id: Int,
    val users: List<Int>,
    val messages: List<Message>,
    val isRead: Boolean = false
)

data class Message(
    val id: Int,
    val ownerId: Int,
    val message: String,
    val isReaded: Boolean = false
)