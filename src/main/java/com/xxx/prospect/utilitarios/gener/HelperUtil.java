package com.xxx.prospect.utilitarios.gener;

import java.util.Collection;
import java.util.Map;

public class HelperUtil {

	public static boolean isNullOrEmpty(final Object o) {
		return o == null;
	}

	public static boolean esMayorAZero(Long o) {
		if (o > 0) {
			return true;
		}
		return false;
	}

	public static boolean isNullOrEmpty(final Collection<?> c) {
		return c == null || c.isEmpty();
	}

	public static boolean isNullOrEmpty(final Map<?, ?> m) {
		return m == null || m.isEmpty();
	}

	/**
	 * Método para validar expresión regular
	 * @param variable, variable a comparar.
	 * @param pattern, expresión regular con la cual se va a comprar.
	 * @return true o false, según si cumple la validación o no.
	 */
	public static boolean isValidPattern(String variable, String pattern) {
		java.util.regex.Pattern patternVal = java.util.regex.Pattern.compile(pattern);
		return (patternVal.matcher(variable).matches()) ? true : false;
	}

}
