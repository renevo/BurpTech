package burptech.lib;

import java.lang.reflect.Field;

import scala.Console;

public class ReflectionHelper 
{
	public static float getFloatFieldFromObject(Object obj, String fieldName)
	{
		float result = 0;
		Field field = null;
		try 
		{
			field = obj.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			result = field.getFloat(obj);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;
	}
}
