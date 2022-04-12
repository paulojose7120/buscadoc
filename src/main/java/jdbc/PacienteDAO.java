package jdbc;

import java.util.ArrayList;
import java.util.List;

import paciente.Paciente;

public interface PacienteDAO {

	
	
	public int addPaciente(Paciente p);
	public Paciente getPaciente(String cpf, String nome);
	public List<Paciente> getListaDePacientes(String cpf);
	public List<Paciente> getListaDePacientesPorNome(String nome);
	public void editarPaciente(Paciente p);
	
	ArrayList<Paciente> getListaDePacientePorAgente(int idDoAgente);
}
