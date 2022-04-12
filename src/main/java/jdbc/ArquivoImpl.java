package jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import paciente.Paciente;
import vacinacao.Documento;

public class ArquivoImpl implements ArquivoDAO {
	private  List<Documento> listaDeDocumentos = new ArrayList<Documento>();
	
	
	@Override
	public void addArquivo(List<Documento> lista, int idPaciente) {

		ConexaoComBancoDeDados conexao =  new ConexaoComBancoDeDados();
		
		
		try {
			for(int i =0; i < lista.size();i++) {
			System.out.println("LISTA DE URL BANCO DE DADOS: "+lista.get(i).getUrl());
			PreparedStatement ps = conexao.getConnection().prepareStatement("INSERT INTO tb_arquivo (id_paciente, url, dataupload, tamanho, nome) VALUES (?,?,?,?,?)");
			ps.setInt(1, idPaciente);
			ps.setString(2, lista.get(i).getUrl());
			ps.setString(3, lista.get(i).getDataDeUpload());
			ps.setLong(4, (int)lista.get(i).getTamanho());
			ps.setString(5, lista.get(i).getNome());
			ps.execute();
			ps.close();
			}
			conexao.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List getAllArquivos(int idDoc) {
		ConexaoComBancoDeDados conexao =  new ConexaoComBancoDeDados();
		
		
	
			try {
				
				PreparedStatement ps = conexao.getConnection().prepareStatement("SELECT * FROM tb_arquivo WHERE id_paciente =  ?");
				ps.setInt(1, idDoc);
				ResultSet rs = ps.executeQuery();
				
				
				while(rs.next()) {
	
					
					String url  =rs.getString("url");
					String nome = rs.getString("nome");
					String dataUpload = rs.getString("dataupload");
					Documento doc = new Documento();
					int id = rs.getInt("id");
					doc.setUrl(url);
					doc.setNome(nome);
					doc.setDataDeUpload(dataUpload);
					doc.setId(id);

					listaDeDocumentos.add(doc);
					
					for(int i =0; i < listaDeDocumentos.size(); i++) {
						System.out.println(listaDeDocumentos.get(i).getId());
					}
					

				}
				
				
			rs.close();	
			conexao.getConnection().close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return listaDeDocumentos;	
		
		
		
		
		
	}

	@Override
	public void updateArquivo(String url, int idPaciente) {

		ConexaoComBancoDeDados conexao =  new ConexaoComBancoDeDados();
		
		try {
			
			
			PreparedStatement ps = conexao.getConnection().prepareStatement("BEGIN; UPDATE tb_arquivo SET url = ? WHERE id_paciente = ?; COMMIT");
				
			ps.setString(1, url);
			ps.setInt(2, idPaciente);
			ps.executeUpdate();
			
			ps.close();
			conexao.getConnection().close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	@Override
	public void deleteArquivo(Documento d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Documento getArquivo() {
		// TODO Auto-generated method stub
		return null;
	}

}
