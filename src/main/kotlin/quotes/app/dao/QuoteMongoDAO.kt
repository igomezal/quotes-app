package quotes.app.dao

import com.mongodb.client.model.Filters.eq
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoCollection
import io.micronaut.http.MutableHttpRequest
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import quotes.app.dao.codecregistry.KMongoFactory
import quotes.app.dto.Quote
import java.util.*
import javax.inject.Singleton

@Singleton
class QuoteMongoDAO(private val mongoClient: MongoClient): QuoteDAO {
    private val kmongoCodecRegistry = KMongoFactory().kCodecRegistry()

    override fun findOne(id: String): Maybe<Quote> {
        return Flowable.fromPublisher(getQuoteCollection().find(eq("_id", id)).limit(1)).firstElement()
    }

    override fun insert(quote: Quote): Single<Quote> {
        quote.id = UUID.randomUUID().toString()
        return Single.fromPublisher(getQuoteCollection().insertOne(quote)).map { quote }
    }

    private fun getQuoteCollection(): MongoCollection<Quote> {
        return mongoClient.getDatabase("model-quotes").withCodecRegistry(kmongoCodecRegistry).getCollection("quotes", Quote::class.java)
    }
}