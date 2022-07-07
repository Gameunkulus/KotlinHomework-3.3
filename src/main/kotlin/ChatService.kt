import org.jetbrains.annotations.NotNull
import javax.lang.model.type.NullType

object ChatService {
    val chats = mutableListOf<Chat>()

    var checkId: Int = 1

    fun getNextId() = chats.size + 1

    fun addChat(chat: Chat) {
        chats.add(chat.copy(id = getNextId()))
    }

    //  fun getChats(){
    //      val chatlist = chats.
    // }
    fun sendMessage(
        firstUserId: Int,
        secondUserId: Int,
        text: String
    ) {
        val message = Message(
            id = 1,
            ownerId = firstUserId,
            message = text
        )
        val newChat = chats.firstOrNull { chat ->
            chat.users.containsAll(listOf(firstUserId, secondUserId))
        }
            ?.let { chat ->
                chat.copy(messages = chat.messages + message.copy(id = chat.messages.size + 1, isReaded = true))
            } ?: Chat(
            id = getNextId(),
            users = listOf(firstUserId, secondUserId),
            messages = listOf(message)
        )
        chats.forEachIndexed { index, chat ->
            if (newChat.id == chat.id) {
                chats[index] = newChat
                return@forEachIndexed
            }
        }
        chats.removeIf { newChat.id == it.id }
        chats.add(newChat)
    }

    fun getUnreadChatsCount(): String {
        val unreadChats: List<Chat> = chats.filter { chat: Chat ->
            !chat.checkRead(chat)
        }
        return unreadChats.toString()
    }

    fun Chat.checkRead(chat: Chat): Boolean {
        val check: Boolean = chat.messages.all { it.isReaded }
        return check
    }

    fun getChats(): String {
        val warning: String = "No messages"
        val listPart: List<String> = chats
            .map { Chat ->
                Chat.messages.lastOrNull().toString()
            }
        return listPart.toString()
    }

    fun getMessageList(chatId: Int, lastMessageId: Int, messageNum: Int): String {
        val MessageList: List<Message> =
            chats[chatId].messages.subList(lastMessageId, lastMessageId + messageNum)
        return MessageList.toString()
    }

    fun deleteMessage(chatId: Int): Boolean {
        if (chats[chatId].messages.isNotEmpty()) {
            chats[chatId] =
                chats[chatId].copy(messages = chats[chatId].messages.subList(0, chats[chatId].messages.size - 1))
            return true
        } else {
            chats.removeAt(chatId)
            return true
        }
    }

    fun deleteChat(chatId: Int): Boolean {
        if (chats.size > chatId) {
            chats.removeAt(chatId)
            return true
        }
        return false
    }


}