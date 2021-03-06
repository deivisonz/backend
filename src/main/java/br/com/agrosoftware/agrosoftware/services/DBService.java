package br.com.agrosoftware.agrosoftware.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.agrosoftware.agrosoftware.enums.Cultivo;
import br.com.agrosoftware.agrosoftware.enums.Funcao;
import br.com.agrosoftware.agrosoftware.enums.UF;
import br.com.agrosoftware.agrosoftware.models.Cultura;
import br.com.agrosoftware.agrosoftware.models.Predicao;
import br.com.agrosoftware.agrosoftware.models.Propriedade;
import br.com.agrosoftware.agrosoftware.models.Usuario;
import br.com.agrosoftware.agrosoftware.repositories.CulturaRepository;
import br.com.agrosoftware.agrosoftware.repositories.PropriedadeRepository;
import br.com.agrosoftware.agrosoftware.repositories.UsuarioRepository;
import weka.classifiers.evaluation.NumericPrediction;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.timeseries.WekaForecaster;
import weka.core.Instances;
import weka.filters.supervised.attribute.TSLagMaker;

@Service
public class DBService {
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private PropriedadeRepository propriedadeRepository;
    @Autowired private CulturaRepository culturaRepository;
    @Autowired BCryptPasswordEncoder pe;
    
    final static String PATH = new File("src/main/java/br/com/agrosoftware/agrosoftware/").getAbsolutePath();
    
    final static int PRECIPITACAO = 0;
    final static int TEMPERATURA_MEDIA = 1;
    final static int TEMPERATURA_MIN = 2;
    final static int TEMPERATURA_MAX = 3;
    final static int INSOLACAO = 4;
    
    final static int AMARELO = 0;
    final static int VERMELHO = 1;
    final static int VERDE = 2;
    
    
    public void instantiateDatabase() throws Exception {   
        var usuarioDeivison = new Usuario("Deivison", "deivison@erlacher.com", pe.encode("112233")); 
        var usuarioVinicius = new Usuario("Vinicius", "vinicius@hotmil.com", pe.encode("123456")); 
        usuarioDeivison.getUsuLsFuncoes().add(Funcao.ADMIN);
        usuarioVinicius.getUsuLsFuncoes().add(Funcao.USUARIO);
        
        var propriedade1 = new Propriedade("Sítio Monte Belo", "Um belo sitio em Monte Belo", 1.5, Cultivo.MILHO, UF.ES, 122); 
        var propriedade2 = new Propriedade("Sítio Boa Fé", "Um recanto em Santa Leopoldina", 3.2, Cultivo.CAFE, UF.ES, 50);
        var propriedade3 = new Propriedade("Sítio Boa Viagem", "Esse é o recanto boa Viagem", 5.0, Cultivo.CANA, UF.MG, 70);
        
        propriedade1.setCulturas(Set.of(new Cultura("Café", 120, 22.0, 28.0, propriedade1)));
        propriedadeRepository.saveAll(List.of(propriedade1, propriedade2, propriedade3));
        
        usuarioRepository.saveAll(List.of(usuarioDeivison, usuarioVinicius));  
        
        //predicaoClima(0);
    }
    
    public List<Predicao> predicaoClima(int idCultivo) throws Exception {
      Instances dataset = new Instances(new BufferedReader(new FileReader(PATH + "/clima.arff")));
      
      WekaForecaster forecaster = new WekaForecaster();

      forecaster.setFieldsToForecast("precipitacao_total,temperatura_media,temperatura_minima,temperatura_maxima,insolacao_total"); //Campos a serem previstos   
      forecaster.setBaseForecaster(new LinearRegression()); //Define o tipo de algoritmo de predição a ser usado

      forecaster.getTSLagMaker().setTimeStampField("data"); // Nome do campo de data no arquivo csv
      forecaster.getTSLagMaker().setMinLag(1);
      forecaster.getTSLagMaker().setMaxLag(12); // Usado para se adequar os calculos dos dados que serão exibidos (12 meses)
      
      forecaster.getTSLagMaker().setAddMonthOfYear(true);
      forecaster.getTSLagMaker().setPeriodicity(TSLagMaker.Periodicity.MONTHLY); //periodicidade em que os dados serão preditos (diario, semanal, mensal...)
      
      forecaster.buildForecaster(dataset, System.out);
      forecaster.primeForecaster(dataset);
      
      LocalDate data = Instant.ofEpochMilli((long) forecaster.getTSLagMaker().getCurrentTimeStampValue()).atZone(ZoneId.systemDefault()).toLocalDate();
      List<List<NumericPrediction>> forecast = forecaster.forecast(12, System.out);      
      
      List<Predicao> predicao = new ArrayList<Predicao>();
      Cultura cultura = culturaRepository.findById(idCultivo).orElse(null);            
      
      for (int mes = 0; mes < 12; mes++) {
          List<NumericPrediction> predsAtStep = forecast.get(mes);           
          var dadoPredicao = new Predicao(data.plusMonths(mes+1).format(DateTimeFormatter.ofPattern("MM/YYYY")));
          for (int info = 0; info <= 4; info ++) {   	  
        	  NumericPrediction predForTarget = predsAtStep.get(info);
        	  switch(info) {
        	  	case PRECIPITACAO:
	        		var precipitacao = BigDecimal.valueOf(predForTarget.predicted()).setScale(3, RoundingMode.FLOOR).doubleValue();
	        		dadoPredicao.setPreVlPrecipitacao(precipitacao);
	        		dadoPredicao.setPreTxObservacaoPrecipitacao("A previsão para o mês é de chuvas ");
	        		
	        		if (cultura != null && (cultura.getCulVlMmIdeal() - 20 <= precipitacao && cultura.getCulVlMmIdeal() + 20 >= precipitacao)) {
	        			dadoPredicao.setPreTxObservacaoPrecipitacao(dadoPredicao.getPreTxObservacaoPrecipitacao() + " ideais para o cultivo");
	        			dadoPredicao.setPrevlCorCard(VERDE);
	        		} else if(cultura != null && (cultura.getCulVlMmIdeal()) < precipitacao) {
	        			dadoPredicao.setPreTxObservacaoPrecipitacao(dadoPredicao.getPreTxObservacaoPrecipitacao() + " acima do esperado");
	        			dadoPredicao.setPrevlCorCard(VERMELHO);
	        		} else if (cultura != null && (cultura.getCulVlMmIdeal()) > precipitacao){       			
	        			dadoPredicao.setPrevlCorCard(AMARELO);
	        			dadoPredicao.setPreTxObservacaoPrecipitacao(dadoPredicao.getPreTxObservacaoPrecipitacao() + " abaixo do esperado");
	        		}
	        		
	        	break;
        	  	case TEMPERATURA_MEDIA:
        	  		var tempMedia = BigDecimal.valueOf(predForTarget.predicted()).setScale(3, RoundingMode.FLOOR).doubleValue();
        	  		dadoPredicao.setPreVlTemperaturaMedia(tempMedia);       	  		
        	  	break;        	  	
        		case TEMPERATURA_MIN:
        			dadoPredicao.setPreTxObservacaoPrecipitacao(dadoPredicao.getPreTxObservacaoPrecipitacao() + ", com temp. mínima de " + BigDecimal.valueOf(predForTarget.predicted()).setScale(2, RoundingMode.FLOOR).doubleValue() + " ºC");
            	break;
        		case TEMPERATURA_MAX:
        	  		dadoPredicao.setPreTxObservacaoPrecipitacao(dadoPredicao.getPreTxObservacaoPrecipitacao() + " e máxima de " + BigDecimal.valueOf(predForTarget.predicted()).setScale(2, RoundingMode.FLOOR).doubleValue() + " ºC.");
        	  	break;
        		case INSOLACAO:
        			dadoPredicao.setPreTxObservacaoTempMedia("A insolação esperada é de " + BigDecimal.valueOf(predForTarget.predicted()).setScale(1, RoundingMode.FLOOR).doubleValue() + " Hrs/mês.");
            	break;
        	  }
          }
               
          predicao.add(dadoPredicao);
          System.out.println(dadoPredicao.toString());
      }
      
      return predicao;
      
    }
               
}
