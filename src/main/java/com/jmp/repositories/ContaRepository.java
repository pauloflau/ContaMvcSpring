package com.jmp.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jmp.entities.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

    @Query("SELECT c FROM Conta c WHERE c.usuario.id = :idUsuario AND c.data BETWEEN :dataInicial AND :dataFinal ORDER BY c.data ASC")
    List<Conta> findContasByUsuarioIdAndDataRange(Integer idUsuario, Date dataInicial, Date dataFinal);
    
    @Query("SELECT c FROM Conta c WHERE c.usuario.id = :idUsuario AND MONTH(c.data) = :mes AND YEAR(c.data) = :ano")
    List<Conta> findContasByUsuarioIdAndMesAno(Integer idUsuario, Integer mes, Integer ano);

}
