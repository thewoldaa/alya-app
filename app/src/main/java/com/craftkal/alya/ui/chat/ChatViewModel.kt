package com.craftkal.alya.ui.chat

import androidx.lifecycle.*
import com.craftkal.alya.core.engine.AlyaBrain
import com.craftkal.alya.core.memory.Message
import com.craftkal.alya.core.memory.SessionMemory
import com.craftkal.alya.data.db.dao.MessageDao
import com.craftkal.alya.data.db.entities.MessageEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val brain: AlyaBrain,
    private val messageDao: MessageDao,
    private val sessionMemory: SessionMemory
) : ViewModel() {

    val messages: LiveData<List<Message>> = messageDao.getAllMessages().map { entities ->
        entities.map { Message(it.text, it.isUser, it.timestamp) }
    }.asLiveData()

    private val _isThinking = MutableLiveData<Boolean>(false)
    val isThinking: LiveData<Boolean> = _isThinking

    fun sendMessage(text: String) {
        viewModelScope.launch {
            // Save User Message
            val userMsg = MessageEntity(text = text, isUser = true, timestamp = System.currentTimeMillis())
            messageDao.insertMessage(userMsg)
            sessionMemory.addMessage(Message(text, true))

            // Think
            _isThinking.value = true
            val response = brain.processInput(text)
            _isThinking.value = false

            // Save Alya Message
            val alyaMsg = MessageEntity(text = response.text, isUser = false, timestamp = System.currentTimeMillis())
            messageDao.insertMessage(alyaMsg)
            sessionMemory.addMessage(Message(response.text, false))
        }
    }
}
