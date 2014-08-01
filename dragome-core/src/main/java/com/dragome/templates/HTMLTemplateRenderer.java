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
package com.dragome.templates;

import org.w3c.dom.Element;

import com.dragome.render.canvas.interfaces.Canvas;
import com.dragome.render.canvas.interfaces.CanvasHelper;
import com.dragome.services.ServiceLocator;
import com.dragome.templates.interfaces.Content;
import com.dragome.templates.interfaces.Template;

public class HTMLTemplateRenderer
{
	public static final String INNER_HTML= "innerHTML";
	public static final String OUTER_HTML= "outerHTML";
	protected Template template;

	public Template getTemplate()
	{
		return template;
	}

	public void setTemplate(Template template)
	{
		this.template= template;
	}

	protected Canvas canvas;

	public HTMLTemplateRenderer()
	{
	}

	public HTMLTemplateRenderer(Template template)
	{
		this.template= template;
		init();
	}

	public void init()
	{
		Element templateContent= ((Content<Element>) template.getContent()).getValue();
		Element content= (Element) templateContent;

		CanvasHelper canvasHelper= ServiceLocator.getInstance().getTemplateManager().getCanvasFactory().getCanvasHelper();
		//	canvasHelper.hideTemplateIds(template);

		Element cloneElement= (Element) content.cloneNode(true);

		//	canvasHelper.restoreTemplateIds(template);

		canvas= ServiceLocator.getInstance().getTemplateManager().getCanvasFactory().createCanvas();

		//	if (template.isInner())
		//	    canvas.setContent(cloneElement.getAttribute(INNER_HTML));
		//	else
		canvas.setContent(cloneElement);
	}

	public void replaceTemplateElement(String anAlias, Canvas aCanvas)
	{
		canvas.replaceSection(anAlias, aCanvas);
	}

	public Canvas getResult()
	{
		return canvas;
	}
}