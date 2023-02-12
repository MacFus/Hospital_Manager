package pl.fus.doctor_manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.springframework.stereotype.Component;

@Component
abstract public class PatchApplier {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T applyPatch(T entity, String patch) throws JsonProcessingException, JsonPatchException {
        JsonNode node = objectMapper.convertValue(entity, JsonNode.class);
        JsonNode patchNode = objectMapper.readTree(patch);
        JsonMergePatch mergePatch = JsonMergePatch.fromJson(patchNode);
        node = mergePatch.apply(node);
        return (T) objectMapper.treeToValue(node, entity.getClass());
    }
}
