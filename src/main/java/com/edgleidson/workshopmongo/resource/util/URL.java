// Classe utilitária com método para decodificar parâmetro da URL.
package com.edgleidson.workshopmongo.resource.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {

	public static String decodificarParametro(String texto) {
		try {
			return URLDecoder.decode(texto, "UTF-8");
		}
		catch (UnsupportedEncodingException ex) {
			return "";
		}
	}
}
