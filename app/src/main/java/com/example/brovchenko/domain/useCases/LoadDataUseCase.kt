package com.example.brovchenko.domain.useCases

import com.example.brovchenko.domain.Repository


class LoadDataUseCase (private val repository: Repository) {
    suspend operator fun invoke() = repository.loadData()
}