# Alya - Offline Rule-Based Chatbot

Alya is a native Android chatbot developed using Kotlin, MVVM, Room, and Hilt. It features a custom deterministic engine that processes input without any internet connection.

## Features
- **Offline Intelligence**: Rule-based intent classification and entity extraction.
- **Personality Engine**: Consistent personality (manja, snack-loving, slightly stubborn).
- **Persistent Memory**: Saves conversation history and user preferences locally.
- **Thinking Visualization**: Obsidian-style graph visualization during processing.
- **Large Dataset**: Over 20MB of pre-defined response pools.

## Tech Stack
- **Language**: Kotlin
- **Architecture**: MVVM
- **Database**: Room (SQLite)
- **Dependency Injection**: Hilt
- **JSON Engine**: GSON
- **UI**: XML ViewBinding

## Project Structure
- `core/engine`: The brain, classifier, and rules.
- `core/memory`: Session and context tracking.
- `data/db`: Persistence layer.
- `ui/chat`: Main conversation interface.
- `assets`: 22MB+ JSON dataset.

Developed by CraftKal.
