package com.yosep.msa.yoscouponapi.stock.repository

import com.yosep.msa.yoscouponapi.stock.domain.Stock
import org.springframework.data.jpa.repository.JpaRepository

interface StockRepository: JpaRepository<Stock, String> {
}