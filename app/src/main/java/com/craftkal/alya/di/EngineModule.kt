package com.craftkal.alya.di

import android.content.Context
import com.craftkal.alya.core.engine.*
import com.craftkal.alya.core.memory.SessionMemory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EngineModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideSessionMemory(): SessionMemory = SessionMemory()

    @Provides
    @Singleton
    fun provideIntentClassifier(@ApplicationContext context: Context, gson: Gson): IntentClassifier {
        val json = context.assets.open("intents.json").bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<IntentDefinition>>() {}.type
        val intents: List<IntentDefinition> = gson.fromJson(json, type)
        return IntentClassifier(intents)
    }

    @Provides
    @Singleton
    fun provideResponseSelector(@ApplicationContext context: Context, gson: Gson): ResponseSelector {
        val json = context.assets.open("responses.json").bufferedReader().use { it.readText() }
        val type = object : TypeToken<Map<String, ResponsePool>>() {}.type
        val pools: Map<String, ResponsePool> = gson.fromJson(json, type)
        return ResponseSelector(pools)
    }

    @Provides
    @Singleton
    fun provideAlyaBrain(
        sessionMemory: SessionMemory,
        intentClassifier: IntentClassifier,
        responseSelector: ResponseSelector
    ): AlyaBrain {
        return AlyaBrain(
            preProcessor = TextPreProcessor(),
            intentClassifier = intentClassifier,
            entityExtractor = EntityExtractor(),
            ruleEngine = RuleEngine(sessionMemory),
            responseSelector = responseSelector,
            sessionMemory = sessionMemory
        )
    }
}
