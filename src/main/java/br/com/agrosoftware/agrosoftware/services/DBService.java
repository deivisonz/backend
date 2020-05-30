package br.com.agrosoftware.agrosoftware.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.agrosoftware.agrosoftware.enums.Cultivo;
import br.com.agrosoftware.agrosoftware.enums.Funcao;
import br.com.agrosoftware.agrosoftware.enums.UF;
import br.com.agrosoftware.agrosoftware.models.Cultura;
import br.com.agrosoftware.agrosoftware.models.Propriedade;
import br.com.agrosoftware.agrosoftware.models.Usuario;
import br.com.agrosoftware.agrosoftware.repositories.PropriedadeRepository;
import br.com.agrosoftware.agrosoftware.repositories.UsuarioRepository;
import weka.classifiers.evaluation.NumericPrediction;
import weka.classifiers.functions.GaussianProcesses;
import weka.classifiers.timeseries.WekaForecaster;
import weka.core.Instances;
import weka.filters.supervised.attribute.TSLagMaker;

@Service
public class DBService {
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private PropriedadeRepository propriedadeRepository;
    @Autowired BCryptPasswordEncoder pe;
    
    final String path = new File("src/main/java/br/com/agrosoftware/agrosoftware/").getAbsolutePath();
    	 
    public void instantiateDatabase() throws Exception {   
        var usuarioDeivison = new Usuario("Deivison", "deivison@erlacher.com", pe.encode("112233")); 
        var usuarioVinicius = new Usuario("Vinicius", "vinicius@hotmil.com", pe.encode("123456")); 
        usuarioDeivison.getUsuLsFuncoes().add(Funcao.ADMIN);
        usuarioVinicius.getUsuLsFuncoes().add(Funcao.USUARIO);
        
        var propriedade1 = new Propriedade("Sítio Monte Belo", "Um sitio de merda", 1.5, Cultivo.MILHO, UF.ES, 122); 
        var propriedade2 = new Propriedade("Sítio Boa Fé", "Um sitio de pouca merda", 3.2, Cultivo.CAFE, UF.ES, 50);
        var propriedade3 = new Propriedade("Sítio Boa Viagem", "Um sitio de nenhuma merda", 5.0, Cultivo.CANA, UF.MG, 70);
        
        propriedade1.setCulturas(Set.of(new Cultura("Café", 20, 22.0, 28.0, propriedade1)));
        propriedadeRepository.saveAll(List.of(propriedade1, propriedade2, propriedade3));
        
        usuarioRepository.saveAll(List.of(usuarioDeivison, usuarioVinicius));  
        
        predicaoClima();
    }
    
    public void predicaoClima() throws Exception {
      Instances dataset = new Instances(new BufferedReader(new FileReader(path + "/clima.arff")));
      
      WekaForecaster forecaster = new WekaForecaster();

      forecaster.setFieldsToForecast("precipitacao_total,temperatura_media"); //Campos a serem previstos   
      forecaster.setBaseForecaster(new GaussianProcesses()); //Define o tipo de algoritmo de predição a ser usado

      forecaster.getTSLagMaker().setTimeStampField("data"); // Nome do campo de data no arquivo csv
      forecaster.getTSLagMaker().setMinLag(1);
      forecaster.getTSLagMaker().setMaxLag(12); // Usado para se adequar os calculos dos dados que serão exibidos (12 meses)
      
      forecaster.getTSLagMaker().setAddMonthOfYear(true);
      forecaster.getTSLagMaker().setPeriodicity(TSLagMaker.Periodicity.MONTHLY); //periodicidade em que os dados serão preditos (diario, semanal, mensal...)
      
      forecaster.buildForecaster(dataset, System.out);
      forecaster.primeForecaster(dataset);
      
      List<List<NumericPrediction>> forecast = forecaster.forecast(12, System.out);      
      
     // String[] predicao;
      
      for (int i = 0; i < 12; i++) {
          List<NumericPrediction> predsAtStep = forecast.get(i);                  
          System.out.println("MÊS " + (i+1));
          for (int j = 0; j < 2; j++) {   	  
          	NumericPrediction predForTarget = predsAtStep.get(j);
          	if (j == 0) {
          		System.out.println("Previsão de chuva: " + predForTarget.predicted() + "mm");	
              } else if (j == 1) {
              	System.out.println("Temp. média Prevista: " + predForTarget.predicted());	
              }
          	            
          }
          System.out.println(forecaster.getTSLagMaker().getCurrentTimeStampValue());
      }
      
      
      
    }
               
}
