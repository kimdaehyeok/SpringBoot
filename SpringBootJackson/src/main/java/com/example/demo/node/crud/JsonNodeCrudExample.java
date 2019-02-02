package com.example.demo.node.crud;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonNodeCrudExample 
{
	private static String targetString = "{\n" + 
			"  \"id\"   : 1,\n" + 
			"  \"name\" : {\n" + 
			"    \"first\" : \"Dae Hyeok\",\n" + 
			"    \"last\" : \"Kim\" \n" + 
			"  },\n" + 
			"  \"contact\" : [\n" + 
			"    { \"type\" : \"phone/home\", \"ref\" : \"111-111-1234\"},\n" + 
			"    { \"type\" : \"phone/work\", \"ref\" : \"222-222-2222\"}\n" + 
			"  ]\n" + 
			"}";
	
	public static void main(String[] args) throws Exception 
	{
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode root = mapper.readTree(targetString);
		((ObjectNode) root).put("id", 1000); // Update Id Field
		
		JsonNode nameNode = root.get("name");
		((ObjectNode) nameNode).put("nickname", "KDH"); // Add New Field
		((ObjectNode) nameNode).remove("last"); // Remove Field
		
		ObjectNode positionNode = mapper.createObjectNode(); // Add ObjectNode put method (put Method : Deprecated)
		positionNode.put("country", "korea");
		positionNode.put("city", "seoul");
		((ObjectNode) nameNode).put("position", positionNode);
		
		ObjectNode programNode = mapper.createObjectNode(); // Add ObjectNode set method
		programNode.put("java", "1.8");
		programNode.put("python", "2.7");
		((ObjectNode) root).set("program", programNode);
		
		ArrayNode gamesNode = mapper.createArrayNode(); // Add ArrayNode
		
		ObjectNode game1 = mapper.createObjectNode(); 
		game1.put("name", "Fail Out 4");
		game1.put("price", 49.9);
		
		ObjectNode game2 = mapper.createObjectNode();
		game2.put("name", "Dark Soul 3");
		game2.put("price", 59.9);
		
		gamesNode.add(game1);
		gamesNode.add(game2);
		
		((ObjectNode) root).set("games", gamesNode);
		
		ObjectNode email = mapper.createObjectNode(); // Append a new Node to ArrayNode
		email.put("type", "email");
		email.put("ref", "yeriel9159@gmail.com");
		
		JsonNode contactNode = root.get("contact");
		((ArrayNode) contactNode).add(email);
		
		System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root));
	}
}
