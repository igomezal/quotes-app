package quotes.app.dao.codecregistry

import com.mongodb.MongoClientSettings
import io.micronaut.context.annotation.Factory
import org.bson.codecs.configuration.CodecRegistry
import org.litote.kmongo.service.ClassMappingType
import javax.inject.Singleton

@Factory
class KMongoFactory {
    @Singleton
    fun kCodecRegistry(): CodecRegistry {
        return ClassMappingType.codecRegistry(MongoClientSettings.getDefaultCodecRegistry());
    }
}