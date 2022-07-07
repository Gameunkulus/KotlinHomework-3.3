fun main(){
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
    val message1 = Message( 1, 1, "Hello how dud?",isReaded = true)
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
    val unreadChat:List<Chat> = listOf(ChatService.chats[1], ChatService.chats[0])

    println(ChatService.getMessageList(0,0,2))

}
