package com.terrier.finances.gestion.communs.api.converter;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.terrier.finances.gestion.communs.budget.model.BudgetMensuel;
import com.terrier.finances.gestion.communs.parametrages.model.CategorieOperation;

/**
 * Serializer pour CategorieOperation dans le cas où elle est utilisée comme clé d'une map (dans {@link BudgetMensuel})
 * /!\ : la liste des sous catégories est vidée !! 
 * @author vzwingma
 *
 */
public class CategorieOperationsKeySerializer extends JsonSerializer<CategorieOperation> {

    private ObjectMapper mapper = new ObjectMapper();
 

	@Override
	public void serialize(CategorieOperation value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException {
		
		// Dans l'utilisation de clé d'une map : Suppression de la liste des sous catégories
		value.getListeSSCategories().clear();
		// On map le reste correctement
        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, value);
        gen.writeFieldName(writer.toString());
	}
}