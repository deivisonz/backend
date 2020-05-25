package br.com.agrosoftware.agrosoftware.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import br.com.agrosoftware.agrosoftware.models.DadoClimatico;

public class CsvReader {
	 private static final int DATA = 0;
	 private static final int HORA = 1;
	 private static final int TEMP_MEDIA = 2;
	 private static final int TEMP_MAX = 3;
	 private static final int TEMP_MIN = 4;
	 private static final int UMIDADE_MEDIA = 5;
	 private static final int UMIDADE_MAX = 6;
	 private static final int UMIDADE_MIN = 7;	 
	 private static final int ORVARLHO_MEDIO = 8;
	 private static final int ORVARLHO_MIN = 9;
	 private static final int ORVARLHO_MAX = 10; 
	 private static final int PRESSAO_MEDIA = 11;
	 private static final int PRESSAO_MIN = 12;
	 private static final int PRESSAO_MAX = 13;	 
	 private static final int DIRECAO_VENTO = 14;
	 private static final int VELOCIDADE_VENTO = 15;
	 private static final int VELOCIDADE_RAJADA_VENTO = 16;
	 
	 private static final int PRECIPITACAO = 18;
	
	 public List<DadoClimatico> lerDadosCsv() {
		String arquivoCSV = "C:\\Projetos\\arquivo.csv";
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ",";
		List<DadoClimatico> retorno = Collections.emptyList();
		
		try {
			br = new BufferedReader(new FileReader(arquivoCSV));
			while ((linha = br.readLine()) != null) {
				String[] csvDadoClimatico = linha.split(csvDivisor);
				DadoClimatico objDadoClimatico = new DadoClimatico();
				objDadoClimatico.setData(LocalDate.parse(csvDadoClimatico[DATA]));
				objDadoClimatico.setHora(Integer.parseInt(csvDadoClimatico[HORA]));
				objDadoClimatico.setTemperaturaMedia(Double.parseDouble(csvDadoClimatico[TEMP_MEDIA]));
				objDadoClimatico.setTemperaturaMaxima(Double.parseDouble(csvDadoClimatico[TEMP_MAX]));
				objDadoClimatico.setTemperaturaMinima(Double.parseDouble(csvDadoClimatico[TEMP_MIN]));
				objDadoClimatico.setUmidadeMedia(Double.parseDouble(csvDadoClimatico[UMIDADE_MEDIA]));
				objDadoClimatico.setUmidadeMaxima(Double.parseDouble(csvDadoClimatico[UMIDADE_MAX]));
				objDadoClimatico.setUmidadeMinima(Double.parseDouble(csvDadoClimatico[UMIDADE_MIN]));
				objDadoClimatico.setPtoOrvalhoMedio(Double.parseDouble(csvDadoClimatico[ORVARLHO_MEDIO]));
				objDadoClimatico.setPtoOrvalhoMaximo(Double.parseDouble(csvDadoClimatico[ORVARLHO_MAX]));
				objDadoClimatico.setPtoOrvalhoMinimo(Double.parseDouble(csvDadoClimatico[ORVARLHO_MIN]));
				objDadoClimatico.setPressaoMedia(Double.parseDouble(csvDadoClimatico[PRESSAO_MEDIA]));
				objDadoClimatico.setPressaoMaxima(Double.parseDouble(csvDadoClimatico[PRESSAO_MAX]));
				objDadoClimatico.setPressaoMinima(Double.parseDouble(csvDadoClimatico[PRESSAO_MIN]));
				objDadoClimatico.setDirecaoVento(Integer.parseInt(csvDadoClimatico[DIRECAO_VENTO]));
				objDadoClimatico.setVelocidadeVento(Integer.parseInt(csvDadoClimatico[VELOCIDADE_VENTO]));
				objDadoClimatico.setVelocidadeRajadaVento(Integer.parseInt(csvDadoClimatico[VELOCIDADE_RAJADA_VENTO]));
				objDadoClimatico.setPrecipitacao(Double.parseDouble(csvDadoClimatico[PRECIPITACAO]));			
				retorno.add(objDadoClimatico);
			}					
	
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		return retorno;
  }

}