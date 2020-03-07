package quotes.app.dao

import io.reactivex.Maybe
import io.reactivex.Single
import quotes.app.dto.Quote

interface QuoteDAO {
    fun findOne(id: String): Maybe<Quote>
    fun insert(quote: Quote): Single<Quote>
}