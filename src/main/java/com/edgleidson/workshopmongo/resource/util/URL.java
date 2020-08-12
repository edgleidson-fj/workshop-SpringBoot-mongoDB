// Classe utilitária com método para decodificar parâmetro da URL.
package com.edgleidson.workshopmongo.resource.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {

	public static String decodificarParametro(String texto) {
		try {
			return URLDecoder.decode(texto, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			return "";
		}
	}

	public static Date converterData(String textoData, Date textoDataPadrao) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			return sdf.parse(textoData);
		} catch (ParseException ex) {
			return textoDataPadrao;
		}
	}
}
