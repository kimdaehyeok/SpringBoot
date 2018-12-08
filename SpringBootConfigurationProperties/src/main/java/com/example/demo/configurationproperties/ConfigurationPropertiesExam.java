package com.example.demo.configurationproperties;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
public class ConfigurationPropertiesExam 
{
	private List<MenuVo> menus = new ArrayList();
	private CompilerVo compiler = new CompilerVo();
	private String error;
	private String success;
	
	public List<MenuVo> getMenus() 
	{
		return menus;
	}
	public void setMenus(List<MenuVo> menus) 
	{
		this.menus = menus;
	}
	public CompilerVo getCompiler() 
	{
		return compiler;
	}
	public void setCompiler(CompilerVo compiler) 
	{
		this.compiler = compiler;
	}
	public String getError() 
	{
		return error;
	}
	public void setError(String error) 
	{
		this.error = error;
	}
	public String getSuccess() 
	{
		return success;
	}
	public void setSuccess(String success) 
	{
		this.success = success;
	}
}
