package com.example.demo.node.list.json;

import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/* Target Json
 [
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
},
{
  "id"   : 2,
  "name" : {
    "first" : "Yong",
    "last" : "Zi Lap" 
  },
  "contact" : [
    { "type" : "phone/home", "ref" : "333-333-1234"},
    { "type" : "phone/work", "ref" : "444-444-4444"}
  ]
}
]
 * */
public class JsonNodeListExample 
{
	private static String targetJson = "[\n" + 
			" {\n" + 
			"  \"id\"   : \"first\",\n" + 
			"  \"name\" : {\n" + 
			"    \"first\" : \"Yong\",\n" + 
			"    \"last\" : \"Mook Kim\" \n" + 
			"  },\n" + 
			"  \"contact\" : [\n" + 
			"    { \"type\" : \"phone/home\", \"ref\" : \"111-111-1234\"},\n" + 
			"    { \"type\" : \"phone/work\", \"ref\" : \"222-222-2222\"}\n" + 
			"  ]\n" + 
			"},\n" + 
			"{\n" + 
			"  \"id\"   : \"second\",\n" + 
			"  \"name\" : {\n" + 
			"    \"first\" : \"Yong\",\n" + 
			"    \"last\" : \"Zi Lap\" \n" + 
			"  },\n" + 
			"  \"contact\" : [\n" + 
			"    { \"type\" : \"phone/home\", \"ref\" : \"333-333-1234\"},\n" + 
			"    { \"type\" : \"phone/work\", \"ref\" : \"444-444-4444\"}\n" + 
			"  ]\n" + 
			"}\n" + 
			"]";
	
	public static void main(String[] args) throws Exception 
	{
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode rootNode = mapper.readTree(targetJson);
		
		System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode));
		System.out.println();
		
		System.out.println(">>> Root Node : " + rootNode);
		System.out.println(">>> Is Root Node Array ? : " + rootNode.isArray());
		System.out.println(">>> Root Node Array Size : " + rootNode.size());
		System.out.println();

		JsonNode idFirstJsonNode = rootNode.get(0);
		System.out.println(">>> First Node : " + idFirstJsonNode);
		System.out.println();
		
		JsonNode idSecondJsonNode = rootNode.get(1);
		System.out.println(">>> Second Node : " + idSecondJsonNode);
	}
}
