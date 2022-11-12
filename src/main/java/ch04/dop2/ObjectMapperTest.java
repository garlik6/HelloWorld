package ch04.dop2;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Map;

class ObjectMapperTest {

    @Test
    void getMapFromJson() {
        String json = """
                 {
                "x": 2,
                "b": aaaaaa,
                "c": [ aaaaa, bbbbbb]
                 }
                """;
        Map<String,Object> a = ObjectMapper.jsonToMap(new JSONObject(json));
    }

}