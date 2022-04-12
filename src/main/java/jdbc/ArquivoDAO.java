package jdbc;

import java.util.List;


import vacinacao.Documento;

public interface ArquivoDAO {

	
	


		public void addArquivo(List<Documento> lista, int idPaciente);

		public List getAllArquivos(int i);
		
		public void updateArquivo(String url, int idPaciente);
		


		public void deleteArquivo(Documento d);

		
		public Documento getArquivo();
	
	
	
	
}
