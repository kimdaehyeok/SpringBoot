package com.example.demo.node.convert;

import com.example.demo.node.convert.vo.ContactVo;
import com.example.demo.node.convert.vo.UserVo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertJsonToVo 
{
	private static String targetJson = "{\n" + 
			"  \"id\"   : 1,\n" + 
			"  \"name\" : {\n" + 
			"    \"first\" : \"Yong\",\n" + 
			"    \"last\" : \"Mook Kim\" \n" + 
			"  },\n" + 
			"  \"contact\" : [\n" + 
			"    { \"type\" : \"phone/home\", \"ref\" : \"111-111-1234\"},\n" + 
			"    { \"type\" : \"phone/work\", \"ref\" : \"222-222-2222\"}\n" + 
			"  ]\n" + 
			"}\n" + 
			"";
	
	public static void main(String[] args) throws Exception 
	{
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode root = mapper.readTree(targetJson);
		System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root));
		
		UserVo userVoFromJson = mapper.readValue(targetJson, UserVo.class);
		
		System.out.println();
		System.out.println(">>> Convert Json Print <<<");
		System.out.println("Id : " + userVoFromJson.getIdFieldMethod());
		System.out.println("Fist Name : " + userVoFromJson.getNameFieldMethod().getFirstFieldMethod());
		System.out.println("Last Name : " + userVoFromJson.getNameFieldMethod().getLastFieldMethod());
		
		for(ContactVo contact: userVoFromJson.getContactFieldListMethod())
		{
			System.out.println("Contact : " + contact.getTypeFieldMethod() +  " / " + contact.getRefFieldMethod());
		}
		
		System.out.println();
	
		System.out.println(">>> Convert Vo to Json String <<<");
		System.out.println(ConvertVoToJsonString.convertVoToJson(userVoFromJson));
	}
}
