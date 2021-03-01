package com.example.justaSIConverter;

import java.math.BigDecimal;
import java.math.MathContext;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class SIConverter {

	private double multiplicationFactor;
	private String unitName;
	
	public double getMultiplicationFactor() {
		return multiplicationFactor;
	}

	public String getUnitName() {
		return unitName;
	}
	
	public SIConverter(String units) {
		this.multiplicationFactor = unitsToMultFactor(units);
		this.unitName = unitsToUnitName(units);
	}
	
	/**
	 * @author Fernando
	 * Converte a entrada para a sua 
	 * respectiva expressão em SI.
	 */
	public String unitsToUnitName (String units) {
		
		units = units.replaceAll("arcsecond", "rad");
		units = units.replaceAll("arcminute", "rad");
		units = units.replaceAll("degree", "rad");
		units = units.replaceAll("hectare", "m²");
		units = units.replaceAll("litre", "m³");
		units = units.replaceAll("tonne", "kg");
		units = units.replaceAll("minute", "s");
		units = units.replaceAll("\"", "rad");
		units = units.replaceAll("hour", "s");
		units = units.replaceAll("min", "s");
		units = units.replaceAll("º", "rad");
		units = units.replaceAll("°", "rad");
		units = units.replaceAll("'", "rad");
		units = units.replaceAll("ha", "m²");
		units = units.replaceAll("L", "m³");
		units = units.replaceAll("t", "kg");
		units = units.replaceAll("h", "s");
		
		return units;
	}
	
	/**
	 * @author Fernando
	 * Utiliza a entrada para obter o 
	 * fator multiplicativo da expressão.
	 */
	public double unitsToMultFactor(String units) {
				
		units  = units.replaceAll("minute", "min");
		units  = units.replaceAll("hour", "h");
		units  = units.replaceAll("day", "d");
		units  = units.replaceAll("º", "degree");
		units  = units.replaceAll("°", "degree");
		units  = units.replaceAll("'", "arcminute");
		units  = units.replaceAll("\"", "arcsecond");
		units  = units.replaceAll("hectare", "ha");
		units  = units.replaceAll("litre", "L");
		units  = units.replaceAll("tonne", "t");
		units  = units.replaceAll("m³", "m3");
		units  = units.replaceAll("m²", "m2");
		
		/* Converte a String em uma Expressão a ser avaliada */
		Expression e = new ExpressionBuilder(units)
			    .variables(
			    		"min", "h", "d",
			    		"degree", "arcminute", "arcsecond",
			    		"ha", "L", "t",
			    		"s","rad","m2","m3","kg"
			    		)
			    .build()
			    .setVariable("min", 60)
			    .setVariable("h", 3600)
			    .setVariable("d", 86400)
			    .setVariable("degree", Math.PI/180)
			    .setVariable("arcminute", Math.PI/10800)
			    .setVariable("arcsecond", Math.PI/648000)
			    .setVariable("ha", 10000)
			    .setVariable("L", 0.001)
			    .setVariable("t", 1000)
			    .setVariable("s", 1)
			    .setVariable("rad", 1)
			    .setVariable("m2", 1)
			    .setVariable("m3", 1)
			    .setVariable("kg", 1)
			    ;
			double result = e.evaluate();
			
			/* Obtém os 14 dígitos mais significativos */
			BigDecimal bd = new BigDecimal(result);
			bd = bd.round(new MathContext(14));
			result = bd.doubleValue();
			
		return result;
	}
	
}
