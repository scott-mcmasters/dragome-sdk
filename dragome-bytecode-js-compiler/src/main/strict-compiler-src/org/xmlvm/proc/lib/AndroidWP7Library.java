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
/* Copyright (c) 2002-2011 by XMLVM.org
 *
 * Project Info:  http://www.xmlvm.org
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 */

package org.xmlvm.proc.lib;

import java.util.ArrayList;
import java.util.List;

import org.xmlvm.main.Targets;
import org.xmlvm.util.universalfile.UniversalFile;
import org.xmlvm.util.universalfile.UniversalFileCreator;

/**
 * Android compatibility library, which is mostly developed against the
 * Java-based cocoa library and is for use in iphone targets only.
 */
public class AndroidWP7Library extends Library
{
	private static final String ONE_JAR_LOCATION= "/lib/wp7-android-java.jar";
	private static final String FILE_SYSTEM_LOCATION= "bin-android";

	private static final String COMMON_FILE_SYSTEM_LOCATION= "bin-common";
	private static final String WP7_FILE_SYSTEM_LOCATION= "bin-common-wp7";

	public boolean isMonolithic()
	{
		return true;
	}

	protected UniversalFile[] getLibraryUncached()
	{
		UniversalFile android= UniversalFileCreator.createDirectory(ONE_JAR_LOCATION, prepareTempJar(FILE_SYSTEM_LOCATION, ""));
		UniversalFile common= UniversalFileCreator.createDirectory(ONE_JAR_LOCATION, prepareTempJar(COMMON_FILE_SYSTEM_LOCATION, ""));
		UniversalFile wp7= UniversalFileCreator.createDirectory(ONE_JAR_LOCATION, prepareTempJar(WP7_FILE_SYSTEM_LOCATION, ""));
		return new UniversalFile[] { android, common, wp7 };
	}

	protected List<Targets> includedTargets()
	{
		List<Targets> included= new ArrayList<Targets>();
		included.add(Targets.WP7ANDROID);
		return included;
	}

	protected List<Targets> excludedTargets()
	{
		return null;
	}
}