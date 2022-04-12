package jdbc;

import java.util.ArrayList;

import unidadesaude.UnidadeSaude;

public interface UnidadeSaudeDAO {

	public ArrayList<UnidadeSaude> getAll();
	
	public UnidadeSaude getPorId(int id);
	
	public boolean update(UnidadeSaude unidade);
	
	public void delete(int id);

	public boolean add(UnidadeSaude unidade);
}
