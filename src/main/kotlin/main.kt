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
    var messagesList3 = listOf<Message>()
    val message1 : Message = Message( 1, 1, "Hello how dud?")
    val message2 : Message = Message(1, 1, "Somthing new?", )
    val message3 : Message = Message(1, 2, "I'm fine", isReaded = true)
    val message4 : Message = Message(1, 2, "Just hanging myself", )
    messagesList1 += message1
    messagesList1 += message3
    messagesList2 += message2
    messagesList2 += message4
    val chat1 = Chat(1,usersList1, messagesList1, true)
    val chat2 = Chat(2,usersList1, messagesList1, true)
    val chat3 = Chat(3,usersList1, messagesList1, true)
    ChatService.addChat(chat1)
    ChatService.addChat(chat2)
    ChatService.addChat(chat3)
    ChatService.getChats()
    ChatService.deleteChat(1)
    ChatService.getChats()
}
