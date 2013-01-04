package websocket.echo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {
	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(new PVValue(1000, 23));
		System.out.println(s);
	}
}
