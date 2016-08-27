package os.outtonight.placefinder.api.mapper;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

public class JSONMapper implements Mapper<String> {

    private static Logger LOG = Logger.getLogger(JSONMapper.class);

    private ObjectMapper objectMapper;
    public JSONMapper(){
        objectMapper = new ObjectMapper();
    }

    @Override
    public String map(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOG.error("Failed to write object to JSON String", e);
            return "{\"error\": \"failed to write object to json string in JSONMapper\"}";
        }
    }
}
