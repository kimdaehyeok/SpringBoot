package com.example.demo.node.convert;

import com.example.demo.node.convert.vo.UserVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertVoToJsonString 
{
	public static String convertVoToJson(UserVo targetVo) throws JsonProcessingException
	{
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.writeValueAsString(targetVo);
	}
}
