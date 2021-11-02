package com.devsuperior.uri2990.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.entities.Empregado;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {

	@Query(nativeQuery = true, value = "SELECT empregados.cpf, empregados.enome, departamentos.dnome "
			+ "FROM empregados "
			+ "INNER JOIN departamentos ON empregados.dnumero = departamentos.dnumero "
			+ "LEFT JOIN trabalha ON empregados.cpf = trabalha.cpf_emp "
			+ "WHERE trabalha.cpf_emp IS NULL "
			+ "ORDER BY empregados.cpf")
	List<EmpregadoDeptProjection> seartch1();
	
	@Query("SELECT new com.devsuperior.uri2990.dto.EmpregadoDeptDTO(obj.cpf, obj.enome, obj.departamento.dnome) "
			+ "FROM Empregado obj "
			+ "LEFT JOIN obj.projetosOndeTrabalha obj2 "
			+ "WHERE obj2 IS NULL "
			+ "ORDER BY obj.cpf")
	List<EmpregadoDeptDTO> seartch2();
}
