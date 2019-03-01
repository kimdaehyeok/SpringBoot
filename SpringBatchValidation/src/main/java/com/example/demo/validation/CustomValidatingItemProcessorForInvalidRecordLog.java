package com.example.demo.validation;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

public class CustomValidatingItemProcessorForInvalidRecordLog<T> implements ItemProcessor<T, T>, InitializingBean 
{
	private Validator<? super T> validator;
	
	private boolean invalidSkipFlag = false;
	
	public CustomValidatingItemProcessorForInvalidRecordLog(Validator<? super T> validator) 
	{
		this.validator = validator;
	}
	
	public void setInvalidFlag(boolean flag)
	{
		this.invalidSkipFlag = flag;
	}

	@Override
	public T process(T item) throws Exception 
	{
		// TODO Auto-generated method stub
		try 
		{
			validator.validate(item);
		}
		catch (ValidationException exception) 
		{
			if (invalidSkipFlag) 
			{
				System.out.println(">>>>>> Invalid Record (CustomValidatingItemProcessorForInvalidRecordLog.class) : " + ((T)item).toString());
				return null; 
			}
			
			else 
			{
				throw exception;
			}
		}
		return item;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception 
	{
		// TODO Auto-generated method stub
		Assert.notNull(validator, "Validator must not be null.");
	}
}
