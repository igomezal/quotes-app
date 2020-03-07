package quotes.app.dto

import org.bson.codecs.pojo.annotations.BsonId

data class Quote(@BsonId var id: String, var text: String)
