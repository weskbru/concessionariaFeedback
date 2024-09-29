package com.saga.concessionaria.service;

import java.util.Optional;

import com.saga.concessionaria.model.Consultor;
import com.saga.concessionaria.repository.ConsultorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ConsultorServiceImpl implements ConsultorService {

    @Autowired
    private ConsultorRepository consultorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Salva um novo consultor após verificar se o CPF já existe.
     * Se o CPF já estiver cadastrado, uma exceção é lançada.
     * A senha do consultor é criptografada antes de ser salva.
     *
     * @param consultor O consultor a ser salvo.
     * @return O consultor salvo.
     */
    @Override
    public Consultor salvarConsultor(Consultor consultor) {
        // Verifica se o CPF já existe antes de salvar
        System.out.println("Verificando se o CPF já existe: " + consultor.getCpf());
        if (cpfExiste(consultor.getCpf())) {
            System.out.println("CPF já cadastrado: " + consultor.getCpf()); // Log para depuração
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        // Criptografa a senha antes de salvar
        consultor.setSenha(passwordEncoder.encode(consultor.getSenha()));
        return consultorRepository.save(consultor);
    }

    /**
     * Verifica se um CPF já está cadastrado no sistema.
     *
     * @param cpf O CPF a ser verificado.
     * @return true se o CPF existir, false caso contrário.
     */
    @Override
    public boolean cpfExiste(String cpf) {
        return consultorRepository.findByCpf(cpf).isPresent();
    }

    /**
     * Busca um consultor pelo CPF.
     *
     * @param cpf O CPF do consultor a ser buscado.
     * @return Um Optional contendo o consultor se encontrado, ou vazio se não encontrado.
     */
    @Override
    public Optional<Consultor> findByCpf(String cpf) {
        return consultorRepository.findByCpf(cpf);
    }

    /**
     * Busca um consultor pelo CPF e senha.
     * Verifica se a senha fornecida corresponde à senha armazenada.
     *
     * @param cpf O CPF do consultor.
     * @param senha A senha fornecida para validação.
     * @return Um Optional contendo o consultor se CPF e senha forem válidos, ou vazio se não forem.
     */
    @Override
    public Optional<Consultor> findByCpfAndSenha(String cpf, String senha) {
        Optional<Consultor> consultor = consultorRepository.findByCpf(cpf);
        if (consultor.isPresent() && passwordEncoder.matches(senha, consultor.get().getSenha())) {
            return consultor;
        }
        return Optional.empty();
    }

    /**
     * Obtém o consultor que está atualmente logado.
     * Retorna null se o consultor não estiver autenticado.
     *
     * @return O consultor logado ou null se não estiver autenticado.
     */
    @Override
    public Consultor obterConsultorLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String cpf = authentication.getName(); // O CPF do consultor logado
            Optional<Consultor> consultorOpt = consultorRepository.findByCpf(cpf);
            return consultorOpt.orElse(null); // Retorna o consultor ou null se não encontrado
        }
        return null; // Retorna null se não estiver autenticado
    }
    
    // Método de atualização do consultor
    @Override
    public void atualizarConsultor(Consultor consultorAtualizado) {
        Optional<Consultor> consultorExistenteOpt = consultorRepository.findById(consultorAtualizado.getId());
        
        if (consultorExistenteOpt.isPresent()) {
            Consultor consultorExistente = consultorExistenteOpt.get();
            
            // Atualiza os campos que foram alterados
            consultorExistente.setNome(consultorAtualizado.getNome());
            consultorExistente.setEmail(consultorAtualizado.getEmail());
            consultorExistente.setTelefone(consultorAtualizado.getTelefone());

            // Verifica se uma nova senha foi fornecida
            if (consultorAtualizado.getSenha() != null && !consultorAtualizado.getSenha().isEmpty()) {
                // Criptografa a nova senha antes de salvar
                consultorExistente.setSenha(passwordEncoder.encode(consultorAtualizado.getSenha()));
            }

            // Salva o consultor atualizado
            consultorRepository.save(consultorExistente);
        } else {
            throw new IllegalArgumentException("Consultor não encontrado");
        }
    }



}
    

