import org.jetbrains.annotations.NotNull
import javax.lang.model.type.NullType

object ChatService {
    val chats = mutableListOf<Chat>()

    var checkId: Int = 1

    fun getNextId() = checkId++

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
                chat.copy(messages = chat.messages + message.copy(id = chat.messages.size + 1))
            } ?: Chat(
            id = 1,
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

    fun getUnreadChatsCount() {
        val unreadChats: List<Chat> = chats.filter { chat: Chat ->
            !chat.checkRead(chat)
        }
        println(unreadChats.toString())
    }

    fun Chat.checkRead(chat: Chat): Boolean {
        chat.messages.filter { !it.isReaded }
        val newChat = chat.copy(isRead = false)
        chats[chat.id] = newChat
        return false
    }

    fun getChats() {
        val warning: String = "No messages"
        val listPart: List<String> = chats
            .map { Chat ->
                Chat.messages.lastOrNull().toString()
            }
        println(listPart.toString())
    }

    fun getMessageList(chatId: Int, lastMessageId: Int, messageNum: Int): String {
        val MessageList: List<Message> =
            chats[chatId].messages.subList(lastMessageId, lastMessageId + messageNum)
        return MessageList.toString()
    }

    fun deleteMessage(chatId: Int) {
        if (chats[chatId].messages.isNotEmpty()) {
            chats[chatId] = chats[chatId].copy(messages = chats[chatId].messages.subList(0, chats[chatId].messages.size - 1))
        } else{
            chats.removeAt(chatId)
        }
    }

    fun deleteChat(chatId: Int){
        if(chats.size > chatId){
            chats.removeAt(chatId)
        }
    }



}