/*
 * Copyright (c) 2014, David Silva
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *          * Redistributions of source code must retain the above copyright
 *              notice, this list of conditions and the following disclaimer.
 *          * Redistributions in binary form must reproduce the above copyright
 *              notice, this list of conditions and the following disclaimer in the
 *              documentation and/or other materials provided with the distribution.
 *          * Neither the name of the <organization> nor the
 *              names of its contributors may be used to endorse or promote products
 *              derived from this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package pt.davidafsilva.jtrakt.model.common;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * This defines the base class for the object model.
 * It provides an internal String representation for all of the model objects.
 *
 * @author David Silva
 */
public class BaseModel {

	// indentation
	private static final int INDENT_SPACES = 2;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return internalStringRepresentation(new HashMap<>(), 1);
	}

	/**
	 * <p>
	 * Constructs an internal String representation of the object being
	 * analyzed at runtime.
	 * </p>
	 *
	 * <p>
	 * The constructions relies on {@link java.lang.reflect} API in order
	 * to retrieve the values of each field in a arbitrary class.
	 * </p>
	 *
	 * <p>
	 * The String representation of some {@link java.lang.reflect.Field} is retrieved
	 * by calling {@link Object#toString()}, except for {@link java.util.Date} objects, which will
	 * be the {@link java.util.Date#getTime()} timestamp.
	 * </p>
	 *
	 * @param mapped
	 * 		the current mapped object (avoid circular-references problems)
	 * @param depth
	 * 		the indentation depth for formatting purposes.
	 * @return the internal String representation of the object
	 */
	private String internalStringRepresentation(Map<Object, String> mapped, int depth) {
		final StringBuilder result = new StringBuilder();
		final String newLine = System.getProperty("line.separator");
		final String indent = String.valueOf(new char[INDENT_SPACES * depth]).replace("\0", " ");
		final String finalIndent = String.valueOf(new char[INDENT_SPACES * (depth - 1)]).replace("\0", " ");

		final String representation = mapped.get(this);
		if (representation == null) {
			result.append(getClass().getName());
			result.append("@");
			result.append(Integer.toHexString(System.identityHashCode(this)));

			// add fields
			mapped.put(this, result.toString());

			result.append(" {");
			result.append(newLine);
			iterateOverClassFields(mapped, getClass(), result, indent, newLine, depth);

			if (!finalIndent.isEmpty()) {
				result.append(finalIndent);
			}
			result.append("}");
		} else {
			result.append(representation);
			result.append(" (ref.)");
		}

		return result.toString();
	}

	/**
	 * Iterates over the give class fields and injects the name-value pair for their fields
	 *
	 * @param mapped
	 * 		the current mapped object (avoid circular-references problems)
	 * @param clazz
	 * 		the class do run through
	 * @param result
	 * 		the result where to inject the name-value pairs
	 * @param indent
	 * 		the current indentation
	 * @param newLine
	 * 		the new line character
	 * @param depth
	 * 		the current depth
	 */
	private void iterateOverClassFields(Map<Object, String> mapped, Class<?> clazz, StringBuilder result,
	                                    String indent, String newLine, int depth) {
		// add field names paired with their values
		for (Field field : clazz.getDeclaredFields()) {
			// ignore field modifier
			field.setAccessible(true);

			result.append(indent);
			try {
				result.append(field.getName());
				result.append(": ");
				Object value = getObjectRepresentation(mapped, field.get(this), indent, newLine, depth);
				result.append(value);
			} catch (IllegalAccessException ex) {
				result.append(ex);
			}
			result.append(newLine);
		}

		// check super classes
		if (clazz.getSuperclass() != BaseModel.class) {
			iterateOverClassFields(mapped, clazz.getSuperclass(), result, indent, newLine, depth);
		}
	}

	/**
	 * Returns the actual object representation of an arbitrary object.
	 *
	 * @param mapped
	 * 		the current mapped object (avoid circular-references problems)
	 * @param value
	 * 		the object to be represented
	 * @param indent
	 * 		the current indentation
	 * @param newLine
	 * 		the newline system representation
	 * @param depth
	 * 		the current depth in indentation
	 * @return the object representation
	 */
	private Object getObjectRepresentation(Map<Object, String> mapped, Object value, String indent, String newLine,
	                                       int depth) {
		Object representation = value;
		if (value != null) {
			if (value.getClass() == Date.class) {
				representation = ((Date) value).getTime();
			} else if (value instanceof Collection) {
				final Collection<?> collectionValues = (Collection<?>) value;
				if (collectionValues.isEmpty()) {
					representation = "[]";
				} else {
					final int initialChars = 1 + newLine.length() + indent.length();
					final StringBuilder representationBuilder = new StringBuilder();
					representationBuilder.append("[");
					representationBuilder.append(newLine);
					representationBuilder.append(indent);
					for (Object item : collectionValues) {
						if (representationBuilder.length() > initialChars) {
							representationBuilder.append(", ");
						} else {
							representationBuilder.append(indent);
						}
						representationBuilder.append(getObjectRepresentation(mapped, item, indent, newLine,
						                                                     depth + 1));
					}
					representationBuilder.append(newLine);
					representationBuilder.append(indent);
					representationBuilder.append("]");
					representation = representationBuilder.toString();
				}
			} else if (value instanceof BaseModel) {
				representation = ((BaseModel) value).internalStringRepresentation(mapped, depth + 1);
			}
		}

		return representation;
	}
}
