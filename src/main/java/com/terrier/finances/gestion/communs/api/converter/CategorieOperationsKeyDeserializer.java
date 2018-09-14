/**
 * 
 */
package com.terrier.finances.gestion.communs.api.converter;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.terrier.finances.gestion.communs.budget.model.BudgetMensuel;
import com.terrier.finances.gestion.communs.parametrages.model.CategorieOperation;

/**
 * Serializer pour CategorieOperation dans le cas où elle est utilisée comme clé d'une map (dans {@link BudgetMensuel})
 * /!\ : la liste des sous catégories est vidée !! 
 * @author vzwingma
 *
 */
public class CategorieOperationsKeyDeserializer extends KeyDeserializer {
	
	private ObjectMapper mapper = new ObjectMapper();
	
	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.KeyDeserializer#deserializeKey(java.lang.String, com.fasterxml.jackson.databind.DeserializationContext)
	 */
	@Override
	public CategorieOperation deserializeKey(String keyInJSON, DeserializationContext arg1) throws IOException {
		return mapper.readValue(keyInJSON, CategorieOperation.class);
	}
}
