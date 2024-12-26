package io.github.jr1811.ashbornrp.datagen.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.jr1811.ashbornrp.util.Predicate;
import net.minecraft.data.client.SimpleModelSupplier;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.List;

public class ModelPredicateProviderSupplier extends SimpleModelSupplier {
    private final Identifier parent;
    private final LinkedHashMap<Identifier, List<Predicate>> models;

    public ModelPredicateProviderSupplier(Identifier parent, LinkedHashMap<Identifier, List<Predicate>> models) {
        super(parent);
        this.parent = parent;
        this.models = models;
    }

    @Override
    public JsonElement get() {
        JsonObject jsonObject = new JsonObject();
        addParent(jsonObject);
        addOverrides(jsonObject);
        return jsonObject;
    }

    private void addParent(JsonObject base) {
        base.addProperty("parent", this.parent.toString());
    }

    private void addOverrides(JsonObject base) {
        JsonArray overridesArray = new JsonArray();
        for (var entry : this.models.entrySet()) {
            Identifier modelIdentifier = entry.getKey();
            List<Predicate> predicates = entry.getValue();

            JsonObject modelJson = new JsonObject();
            JsonObject predicatesJson = new JsonObject();

            for (Predicate predicate : predicates) {
                predicate.addToJson(predicatesJson);
            }
            modelJson.add("predicate", predicatesJson);

            modelJson.addProperty("model", modelIdentifier.toString());

            overridesArray.add(modelJson);
        }
        base.add("overrides", overridesArray);
    }
}
