package android.finances.terrier.com.budget.services.rest;

import android.finances.terrier.com.budget.models.DepenseCategorie;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;


/**
 * Module REST
 * Created by vzwingma on 31/12/2014.
 */
public class RESTDataModule extends SimpleModule {


    public RESTDataModule() {
        super("REST DataModule Budget", new Version(1, 0, 0, ""));


        // then serializers:
        final JsonSerializer<Object> stringSer = ToStringSerializer.instance;
        addSerializer(DepenseCategorie.class, stringSer);
        // then key deserializers
        addKeyDeserializer(DepenseCategorie.class, new DepenseCategorieDeserializer());

    }


    // yes, will try to avoid duplicate registations (if MapperFeature enabled)
    @Override
    public String getModuleName() {
        return getClass().getSimpleName();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }
}