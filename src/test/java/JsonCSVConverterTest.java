
import org.junit.Test;
import org.json.JSONException;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class JsonCSVConverterTest {

    @Test
    public void shouldParseJsonIntoCsvRow(){
        String json = "{\"name\":\"Alice\",\"age\":30}";
        String expected = "Alice,30";
        assertEquals(expected, JsonCSVConverter.convert(json));
    }
}
