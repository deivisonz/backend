package br.com.agrosoftware.agrosoftware.utils;

public class UtilsNumber {
	
	public static double parseDouble(String s, double valorSeNulo) {
	    return s  == null || s.isBlank() ? valorSeNulo : Double.parseDouble(s);
	}
	
	public static int parseInt(String s, int valorSeNulo) {
	    return s == null || s.isBlank() ? valorSeNulo : Integer.parseInt(s);
	}
	
}
