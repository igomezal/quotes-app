package quotes.app.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.*
import io.reactivex.Single
import quotes.app.dao.QuoteDAO
import quotes.app.dto.Quote

@Controller("/quotes")
class QuoteResource(private val quoteDAO: QuoteDAO) {
    @Get(value = "{id}", produces = [MediaType.APPLICATION_JSON])
    fun getOne(@PathVariable("id") id: String): Single<MutableHttpResponse<Quote>> {
        return quoteDAO.findOne(id).map { HttpResponse.ok(it) }.toSingle(HttpResponse.notFound())
    }

    @Post
    fun insertOne(@Body quote: Quote): Single<MutableHttpResponse<Quote>> {
        return quoteDAO.insert(quote).map { HttpResponse.created(it) }.onErrorReturn { HttpResponse.serverError() }
    }
}