import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {


    @Test
    fun sendMessage() {

        val usersList1 = mutableListOf<Int>()
        usersList1 += 1
        usersList1 += 2
        usersList1 += 3
        usersList1 += 4
        val usersList2 = mutableListOf<Int>()
        usersList2 += 3
        usersList2 += 4
        var messagesList1 = listOf<Message>()
        var messagesList2 = listOf<Message>()
        val message1 = Message( 1, 1, "Hello how dud?", isReaded = true)
        val message2 = Message(1, 1, "Somthing new?", )
        val message3 = Message(1, 2, "I'm fine", isReaded = true)
        val message4 = Message(1, 2, "Just hanging myself", )
        messagesList1 += message1
        messagesList1 += message3
        messagesList2 += message2
        messagesList2 += message4
        val chat1 = Chat(1,usersList1, messagesList1, true)
        val chat2 = Chat(2,usersList2, messagesList2, true)
        ChatService.addChat(chat1)
        ChatService.addChat(chat2)
        ChatService.sendMessage(usersList1[0], usersList1[1], "Hello!!!")
        assertEquals("Hello!!!", ChatService.chats[1].messages.last().message)
        val unreadChat:List<Chat> = listOf(ChatService.chats[1], ChatService.chats[0])
    }

    @Test
    fun getUnreadChatsCount() {

        val usersList2 = mutableListOf<Int>()
        usersList2 += 3
        usersList2 += 4
        var messagesList2 = listOf<Message>()
        val message2 = Message(1, 1, "Somthing new?", )
        val message4 = Message(1, 2, "Just hanging myself", )
        messagesList2 += message2
        messagesList2 += message4
        val chat2 = Chat(2,usersList2, messagesList2, true)

        val unreadChat:List<Chat> = listOf(chat2)

        val expect: String = unreadChat.toString()
        assertEquals(expect, ChatService.getUnreadChatsCount())
    }

    @Test
    fun getChats() {
        val message1 = Message( 1, 2, "Just hanging myself" , isReaded = false)
        val unreadChat:List<Message> = listOf(message1)
        assertEquals(unreadChat.toString(), ChatService.getChats())
    }

    @Test
    fun getMessageList() {
        val message1 = Message( 1, 1, "Somthing new?")
        val message2 = Message(1, 2, "Just hanging myself")
        val unreadChat:List<Message> = listOf(message1,message2)
        val chatList: String = ChatService.getMessageList(0,0,2)
        assertEquals(unreadChat.toString(), chatList)
    }

    @Test
    fun deleteMessage() {
        assertTrue(ChatService.deleteMessage(1))
    }

    @Test
    fun deleteChat() {
        assertTrue(ChatService.deleteChat(1))
    }
}