/*
 * Copyright (c) 2011-2014 Fernando Petrola
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dragome.javascript;


public final class ScriptHelper
{
    public static ScriptHelperInterface scriptHelperInterface;

    public static void put(String s, Object value, Object callerInstance)
    {
	scriptHelperInterface.put(s, value, callerInstance);
    }

    public static void put(String s, boolean value, Object callerInstance)
    {
	scriptHelperInterface.put(s, value, callerInstance);
    }

    public static void put(String s, double value, Object callerInstance)
    {
	scriptHelperInterface.put(s, value, callerInstance);
    }

    public static Object eval(String script, Object callerInstance)
    {
	return scriptHelperInterface.eval(script, callerInstance);
    }

    public static int evalInt(String jsCode, Object callerInstance)
    {
	return scriptHelperInterface.evalInt(jsCode, callerInstance);
    }

    public static long evalLong(String jsCode)
    {
	return scriptHelperInterface.evalLong(jsCode);
    }

    public static float evalFloat(String jsCode)
    {
	return scriptHelperInterface.evalFloat(jsCode);
    }

    public static double evalDouble(String jsCode)
    {
	return scriptHelperInterface.evalDouble(jsCode);
    }

    public static char evalChar(String jsCode)
    {
	return scriptHelperInterface.evalChar(jsCode);
    }

    public static boolean evalBoolean(String jsCode, Object callerInstance)
    {
	return scriptHelperInterface.evalBoolean(jsCode, callerInstance);
    }

    public static void evalNoResult(String script, Object callerInstance)
    {
	scriptHelperInterface.evalNoResult(script, callerInstance);
    }
}
