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
import br.com.agrosoftware.agrosoftware.models.Propriedade;
import br.com.agrosoftware.agrosoftware.models.Usuario;
import br.com.agrosoftware.agrosoftware.repositories.DadoClimaticoRepository;
import br.com.agrosoftware.agrosoftware.repositories.PropriedadeRepository;
import br.com.agrosoftware.agrosoftware.repositories.UsuarioRepository;

@Service
public class DBService {
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private PropriedadeRepository propriedadeRepository;
    @Autowired private DadoClimaticoRepository dadoClimaticoRepository;
    @Autowired BCryptPasswordEncoder pe;
    
    private static final int CABECALHO = 0;
    
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
    
    public void instantiateDatabase() {   
        // Usuários
        var usuarioDeivison = new Usuario("Deivison", "deivison@erlacher.com", pe.encode("112233")); 
        var usuarioVinicius = new Usuario("Vinicius", "vinicius@hotmil.com", pe.encode("123456")); 
        usuarioDeivison.getUsuLsFuncoes().add(Funcao.ADMIN);
        usuarioVinicius.getUsuLsFuncoes().add(Funcao.USUARIO);
        
        //Propriedades
        var propriedade1 = new Propriedade("Sítio Monte Belo", "Um sitio de merda", 1.5, Cultivo.MILHO, UF.ES); 
        var propriedade2 = new Propriedade("Sítio Boa Fé", "Um sitio de pouca merda", 3.2, Cultivo.CAFE, UF.ES);
        var propriedade3 = new Propriedade("Sítio Boa Viagem", "Um sitio de nenhuma merda", 5.0, Cultivo.CANA, UF.MG);
        propriedadeRepository.saveAll(List.of(propriedade1, propriedade2, propriedade3));
        
        //usuarioDeivison.setUsuCdPropriedade(propriedade1);
        //usuarioVinicius.setUsuCdPropriedade(propriedade2);
        usuarioRepository.saveAll(List.of(usuarioDeivison, usuarioVinicius)); 
        
        dadoClimaticoRepository.saveAll(lerDadosCsv());   
        
        
    }
    
	 public List<DadoClimatico> lerDadosCsv() {
		String arquivoCSV = "C:\\Projetos\\arquivo.csv";
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ",";
		List<DadoClimatico> retorno = new ArrayList<>();
		
		try {
			br = new BufferedReader(new FileReader(arquivoCSV));
			int i = 0;			
			while ((linha = br.readLine()) != null) {
				if (i > CABECALHO) {
					String[] csvDadoClimatico = linha.split(csvDivisor);			
					DadoClimatico objDadoClimatico = new DadoClimatico();
					objDadoClimatico.setData(LocalDate.parse(csvDadoClimatico[DATA], DateTimeFormatter.ofPattern("dd/MM/yyyy")));
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
					objDadoClimatico.setDirecaoVento(Double.parseDouble(csvDadoClimatico[DIRECAO_VENTO]));
					objDadoClimatico.setVelocidadeVento(Double.parseDouble(csvDadoClimatico[VELOCIDADE_VENTO]));
					objDadoClimatico.setVelocidadeRajadaVento(Double.parseDouble(csvDadoClimatico[VELOCIDADE_RAJADA_VENTO]));
					objDadoClimatico.setPrecipitacao(Double.parseDouble(csvDadoClimatico[PRECIPITACAO]));	
					objDadoClimatico.setEstacao(Estacao.AFONSOCLAUDIO);
					retorno.add(objDadoClimatico);
				}
				i++;
			}					
	
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		return retorno;
 }
    
}
