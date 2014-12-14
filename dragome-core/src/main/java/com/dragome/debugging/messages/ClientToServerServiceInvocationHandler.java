/*******************************************************************************
 * Copyright (c) 2011-2014 Fernando Petrola
 * 
 * This file is part of Dragome SDK.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package com.dragome.debugging.messages;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dragome.debugging.CrossExecutionResultImpl;
import com.dragome.debugging.ServiceInvocationResult;
import com.dragome.debugging.interfaces.CrossExecutionResult;
import com.dragome.execution.ApplicationExecutor;
import com.dragome.services.ServiceInvocation;
import com.dragome.services.ServiceLocator;

public class ClientToServerServiceInvocationHandler implements InvocationHandler
{
	private Class<?> type;

	public ClientToServerServiceInvocationHandler(Class<?> type)
	{
		this.type= type;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		return invokeMethod2(type, method, args);
	}

	public Object invokeMethod2(Class type, Method method, Object[] args)
	{
		boolean serialized= false;

		String serialize= "";

		serialize= "{\"@id\":0,\"method\":\"${AE}.pushResult:1\",\"id\":\"16\",\"args\":[{\"@id\":1,\"listResult\":null,\"id\":\"${id}\",\"objectResult\":{\"@id\":2,\"result\":\"${result}\",\"class\":\"${CERI}\"},\"class\":\"${SIR}\"}],\"type\":{\"name\":\"${AE}\",\"class\":\"java.lang.Class\"},\"class\":\"${SI}\"}";
		//	serialize= "{\"@id\":0,\"method\":\"${AE}.pushResult:1\",\"id\":\"292\",\"args\":[{\"@id\":1,\"result\":{\"@id\":2,\"result\":\"${result}\",\"class\":\"${CERI}\"},\"id\":\"${id}\",\"class\":\"${SIR}\"}],\"type\":{\"name\":\"${AE}\",\"class\":\"java.lang.Class\"},\"class\":\"${SI}\"}";

		serialize= serialize.replace("${AE}", ApplicationExecutor.class.getName());
		serialize= serialize.replace("${CERI}", CrossExecutionResultImpl.class.getName());
		serialize= serialize.replace("${SIR}", ServiceInvocationResult.class.getName());
		serialize= serialize.replace("${SI}", ServiceInvocation.class.getName());

		if (method.getName().equals("pushResult") && args[0] instanceof ServiceInvocationResult)
		{
			ServiceInvocationResult serviceInvocationResult= (ServiceInvocationResult) args[0];
			if (serviceInvocationResult.getObjectResult() instanceof CrossExecutionResult)
			{
				CrossExecutionResult result= (CrossExecutionResult) serviceInvocationResult.getObjectResult();
				serialize= serialize.replace("${result}", (result.getResult() + "").replace("\"", "\\\"").replace("\n", "\\\\n").replace("\r", "\\\\r"));
				serialize= serialize.replace("${id}", serviceInvocationResult.getId());
				serialized= true;
			}
		}

		if (!serialized)
		{
			List<Object> arguments= args != null ? Arrays.asList(args) : new ArrayList<Object>();			
			ServiceInvocation serviceInvocation= new ServiceInvocation(type, method, arguments);
			serialize= ServiceLocator.getInstance().getSerializationService().serialize(serviceInvocation);
			//	    System.out.println(serialize);
		}

		Sender serverMessageChannel= ServiceLocator.getInstance().getClientToServerMessageChannel();
		serverMessageChannel.send(serialize);
		return null;
	}
}
