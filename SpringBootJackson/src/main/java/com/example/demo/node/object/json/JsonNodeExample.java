package com.example.demo.node.object.json;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/* Target Json
	 {
	  "id"   : 1,
	  "name" : {
	    "first" : "Yong",
	    "last" : "Mook Kim" 
	  },
	  "contact" : [
	    { "type" : "phone/home", "ref" : "111-111-1234"},
	    { "type" : "phone/work", "ref" : "222-222-2222"}
	  ]
	}
 * */
public class JsonNodeExample 
{
	private static String targetJson = "{\n" + 
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
		
		
		JsonNode rootNode = mapper.readTree(targetJson);
		
		System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode));
		System.out.println();
		System.out.println(">>> Root Node : " + rootNode);
		System.out.println();
		
		JsonNode idNode = rootNode.get("id");
		System.out.println(">>> Id Node : " + idNode);
		System.out.println();
		
		JsonNode nameNode = rootNode.get("name");
		System.out.println(">>> Name Node : " + nameNode);
		System.out.println();
		
		JsonNode contactNode = rootNode.get("contact");
		System.out.println(">>> Contact : " + contactNode);
		System.out.println(">>> Is Contact Node Array ? : " + contactNode.isArray());
		System.out.println();
		
		for(JsonNode node : contactNode)
		{
			System.out.println(">>> Contact Array Item type : " + node.get("type"));
			System.out.println(">>> Contract Array ref : " + node.path("ref"));
			System.out.println();
		}
	}
}
