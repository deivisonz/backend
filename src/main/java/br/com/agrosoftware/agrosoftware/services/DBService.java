package br.com.agrosoftware.agrosoftware.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.agrosoftware.agrosoftware.enums.Cultivo;
import br.com.agrosoftware.agrosoftware.enums.Estacao;
import br.com.agrosoftware.agrosoftware.enums.Funcao;
import br.com.agrosoftware.agrosoftware.enums.UF;
import br.com.agrosoftware.agrosoftware.models.DadoClimatico;
import br.com.agrosoftware.agrosoftware.models.DadoClimaticoMensal;
import br.com.agrosoftware.agrosoftware.models.Propriedade;
import br.com.agrosoftware.agrosoftware.models.Usuario;
import br.com.agrosoftware.agrosoftware.repositories.DadoClimaticoMensalRepository;
import br.com.agrosoftware.agrosoftware.repositories.PropriedadeRepository;
import br.com.agrosoftware.agrosoftware.repositories.UsuarioRepository;
import br.com.agrosoftware.agrosoftware.utils.UtilsNumber;
import weka.classifiers.evaluation.NumericPrediction;
import weka.classifiers.functions.GaussianProcesses;
import weka.classifiers.timeseries.WekaForecaster;
import weka.core.Instances;
import weka.filters.supervised.attribute.TSLagMaker;

@Service
public class DBService {
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private PropriedadeRepository propriedadeRepository;
    @Autowired private DadoClimaticoMensalRepository dadoClimaticoMensalRepository;
    @Autowired BCryptPasswordEncoder pe;
    
    private static final int CABECALHO = 0;
    
    //CONSTANTES DADOS CLIMATICO
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
	 private static final int PRESSAO_MAX = 12;
	 private static final int PRESSAO_MIN = 13;	 
	 private static final int DIRECAO_VENTO = 14;
	 private static final int VELOCIDADE_VENTO = 15;
	 private static final int VELOCIDADE_RAJADA_VENTO = 16;
	 private static final int PRECIPITACAO = 18;
	 
	//CONSTANTES DADOS CLIMATICO MENSAL
	 private static final int M_DATA = 1;
	 private static final int M_DIRECAO_VENTO = 3;
	 private static final int M_VELOCIDADE_VENTO_MEDIA = 4;
	 private static final int M_VELOCIDADE_VENTO_MAX_MEDIA = 5;
	 private static final int M_EVAPORACAO_PICHE = 6;
	 private static final int M_EVAPORACAO_BH_POTENCIAL = 7;
	 private static final int M_EVAPORACAO_BH_REAL = 8;	 
	 private static final int M_INSOLACAO_TOTAL = 9;
	 private static final int M_NEBULOSIDADE_MEDIA = 10;
	 private static final int M_NUM_DIAS_PRECIPITACAO = 11; 
	 private static final int M_PRECIPITACAO_TOTAL = 12; 
	 private static final int M_PRESSAO_MEDIA = 14; 
	 private static final int M_TEMP_MAXIMA_MEDIA = 15; 
	 private static final int M_TEMP_COMPENSADA_MEDIA = 16; 
	 private static final int M_TEMP_MINIMA_MEDIA = 17; 
	 private static final int M_UMIDADE_RELATIVA_MEDIA = 18; 
	 
    public void instantiateDatabase() throws Exception {   
        // Usuários
        var usuarioDeivison = new Usuario("Deivison", "deivison@erlacher.com", pe.encode("112233")); 
        var usuarioVinicius = new Usuario("Vinicius", "vinicius@hotmil.com", pe.encode("123456")); 
        usuarioDeivison.getUsuLsFuncoes().add(Funcao.ADMIN);
        usuarioVinicius.getUsuLsFuncoes().add(Funcao.USUARIO);
        
        //Propriedades
        var propriedade1 = new Propriedade("Sítio Monte Belo", "Um sitio de merda", 1.5, Cultivo.MILHO, UF.ES, 122); 
        var propriedade2 = new Propriedade("Sítio Boa Fé", "Um sitio de pouca merda", 3.2, Cultivo.CAFE, UF.ES, 50);
        var propriedade3 = new Propriedade("Sítio Boa Viagem", "Um sitio de nenhuma merda", 5.0, Cultivo.CANA, UF.MG, 70);
        propriedadeRepository.saveAll(List.of(propriedade1, propriedade2, propriedade3));
        
        usuarioRepository.saveAll(List.of(usuarioDeivison, usuarioVinicius));         
        
        //Não é necessário por enquanto
        //lerDadosCsv();      

//        Instances dataset = new Instances(new BufferedReader(new FileReader("C:\\Projetos\\clima.arff")));
//
//        WekaForecaster forecaster = new WekaForecaster();
//
//        forecaster.setFieldsToForecast("precipitacao,temperatura");

        //Define o tipo de algoritmo de predição a ser usado
//        forecaster.setBaseForecaster(new GaussianProcesses());
//
//        forecaster.getTSLagMaker().setTimeStampField("data"); // nome do campo de data no arquivo csv
//        forecaster.getTSLagMaker().setMinLag(1);
//        forecaster.getTSLagMaker().setMaxLag(12); // usado para se adequar os calculos dos dados que serão exibidos (12 meses)
//
//        forecaster.getTSLagMaker().setAddMonthOfYear(true);
//        forecaster.getTSLagMaker().setPeriodicity(TSLagMaker.Periodicity.MONTHLY); //periodicidade em que os dados serão preditos (diario, semanal, mensal...)
//
//        forecaster.buildForecaster(dataset, System.out);
//
//        forecaster.primeForecaster(dataset);
//    
//        List<List<NumericPrediction>> forecast = forecaster.forecast(12, System.out);

//        for (int i = 0; i < 12; i++) {
//            List<NumericPrediction> predsAtStep = forecast.get(i);
//            System.out.println("MÊS " + (i+1));
//            for (int j = 0; j < 2; j++) {
//            	NumericPrediction predForTarget = predsAtStep.get(j);
//            	
//            	if (j == 0) {
//            		System.out.println("Previsão de chuva: " + predForTarget.predicted() + "mm");	
//                } else if (j == 1) {
//                	System.out.println("Temp. média Prevista: " + predForTarget.predicted());	
//                }
//            	            
//            }
//            System.out.println();
//        }

    }
        
	 public void lerDadosCsv() {
		String arquivoCSV = "C:\\Projetos\\arquivo.csv";
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ";";
		//List<DadoClimatico> dadoClimatico = new ArrayList<>();
		List<DadoClimaticoMensal> dadoClimaticoMensal = new ArrayList<>();
		
		try {
			br = new BufferedReader(new FileReader(arquivoCSV));
			int i = 0;			
			while ((linha = br.readLine()) != null) {
				if (i > CABECALHO) {
					String[] csvDadoClimatico = linha.split(csvDivisor, -1);	
					//dadoClimatico.add(csvToDadoClimatico(csvDadoClimatico));
					dadoClimaticoMensal.add(csvToDadoClimaticoMensal(csvDadoClimatico));
				}
				i++;
			}					
	
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		//dadoClimaticoRepository.saveAll(dadoClimatico);
		dadoClimaticoMensalRepository.saveAll(dadoClimaticoMensal);
		
 }
	 
	 public DadoClimatico csvToDadoClimatico(String[] csvDadoClimatico) {
		 	var dadoClimatico = new DadoClimatico();
			dadoClimatico.setData(LocalDate.parse(csvDadoClimatico[DATA], DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			dadoClimatico.setHora(Integer.parseInt(csvDadoClimatico[HORA]));
			dadoClimatico.setTemperaturaMedia(Double.parseDouble(csvDadoClimatico[TEMP_MEDIA]));
			dadoClimatico.setTemperaturaMaxima(Double.parseDouble(csvDadoClimatico[TEMP_MAX]));
			dadoClimatico.setTemperaturaMinima(Double.parseDouble(csvDadoClimatico[TEMP_MIN]));
			dadoClimatico.setUmidadeMedia(Double.parseDouble(csvDadoClimatico[UMIDADE_MEDIA]));
			dadoClimatico.setUmidadeMaxima(Double.parseDouble(csvDadoClimatico[UMIDADE_MAX]));
			dadoClimatico.setUmidadeMinima(Double.parseDouble(csvDadoClimatico[UMIDADE_MIN]));
			dadoClimatico.setPtoOrvalhoMedio(Double.parseDouble(csvDadoClimatico[ORVARLHO_MEDIO]));
			dadoClimatico.setPtoOrvalhoMaximo(Double.parseDouble(csvDadoClimatico[ORVARLHO_MAX]));
			dadoClimatico.setPtoOrvalhoMinimo(Double.parseDouble(csvDadoClimatico[ORVARLHO_MIN]));
			dadoClimatico.setPressaoMedia(Double.parseDouble(csvDadoClimatico[PRESSAO_MEDIA]));
			dadoClimatico.setPressaoMaxima(Double.parseDouble(csvDadoClimatico[PRESSAO_MAX]));
			dadoClimatico.setPressaoMinima(Double.parseDouble(csvDadoClimatico[PRESSAO_MIN]));
			dadoClimatico.setDirecaoVento(Double.parseDouble(csvDadoClimatico[DIRECAO_VENTO]));
			dadoClimatico.setVelocidadeVento(Double.parseDouble(csvDadoClimatico[VELOCIDADE_VENTO]));
			dadoClimatico.setVelocidadeRajadaVento(Double.parseDouble(csvDadoClimatico[VELOCIDADE_RAJADA_VENTO]));
			dadoClimatico.setPrecipitacao(Double.parseDouble(csvDadoClimatico[PRECIPITACAO]));	
			dadoClimatico.setEstacao(Estacao.VITORIA);
			
			return dadoClimatico;
	 }
	 
	 public DadoClimaticoMensal csvToDadoClimaticoMensal(String[] csvDadoClimatico) {
		 var dadoClimaticoMensal = new DadoClimaticoMensal();
		 
		 dadoClimaticoMensal.setEstacao(Estacao.VITORIA);
		 dadoClimaticoMensal.setData(LocalDate.parse(csvDadoClimatico[M_DATA], DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		 dadoClimaticoMensal.setDirecaoVento(UtilsNumber.parseDouble(csvDadoClimatico[M_DIRECAO_VENTO], 0));
		 dadoClimaticoMensal.setVelocidadeVento(UtilsNumber.parseDouble(csvDadoClimatico[M_VELOCIDADE_VENTO_MEDIA], 0));
		 dadoClimaticoMensal.setVelocidadeVentoMaximaMedia(UtilsNumber.parseDouble(csvDadoClimatico[M_VELOCIDADE_VENTO_MAX_MEDIA], 0));
		 dadoClimaticoMensal.setEvaporacaoPiche(UtilsNumber.parseDouble(csvDadoClimatico[M_EVAPORACAO_PICHE], 0));
		 dadoClimaticoMensal.setEvapoBHPotencial(UtilsNumber.parseDouble(csvDadoClimatico[M_EVAPORACAO_BH_POTENCIAL], 0));
		 dadoClimaticoMensal.setEvapoBHReal(UtilsNumber.parseDouble(csvDadoClimatico[M_EVAPORACAO_BH_REAL], 0));
		 dadoClimaticoMensal.setInsolacaoTotal(UtilsNumber.parseDouble(csvDadoClimatico[M_INSOLACAO_TOTAL], 0));
		 dadoClimaticoMensal.setNebulosidadeMedia(UtilsNumber.parseDouble(csvDadoClimatico[M_NEBULOSIDADE_MEDIA], 0));
		 dadoClimaticoMensal.setNumDiasPrecipitacao(UtilsNumber.parseInt(csvDadoClimatico[M_NUM_DIAS_PRECIPITACAO], 0));
		 dadoClimaticoMensal.setPrecipitacaoTotal(UtilsNumber.parseDouble(csvDadoClimatico[M_PRECIPITACAO_TOTAL], 0));
		 dadoClimaticoMensal.setPressaoMedia(UtilsNumber.parseDouble(csvDadoClimatico[M_PRESSAO_MEDIA], 0));
		 dadoClimaticoMensal.setTempMaximaMedia(UtilsNumber.parseDouble(csvDadoClimatico[M_TEMP_MAXIMA_MEDIA], 0));
		 dadoClimaticoMensal.setTempCompensadaMedia(UtilsNumber.parseDouble(csvDadoClimatico[M_TEMP_COMPENSADA_MEDIA], 0));
		 dadoClimaticoMensal.setTempMinimaMedia(UtilsNumber.parseDouble(csvDadoClimatico[M_TEMP_MINIMA_MEDIA], 0));
		 dadoClimaticoMensal.setUmidadeRelativaMedia(UtilsNumber.parseDouble(csvDadoClimatico[M_UMIDADE_RELATIVA_MEDIA], 0));
		 
		 return dadoClimaticoMensal;
	 }
    
}
