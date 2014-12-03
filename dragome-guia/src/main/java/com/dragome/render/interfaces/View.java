/*******************************************************************************
 * Copyright (c) 2011-2014 Fernando Petrola
 * 
 *  This file is part of Dragome SDK.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package com.dragome.render.interfaces;

import java.util.List;

import com.dragome.templates.interfaces.Template;

public interface View<T>
{
	public List<Template> getRequiredSubTemplates(Template template);

	public void composeView(T model, List<Template> templates);

	boolean match(T model, Template baseTemplate);
}