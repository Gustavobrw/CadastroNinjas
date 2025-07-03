package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("missoes")
public class MissoesController {
    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    //GET -- Mandar uma requisição para mostrar as missoes
    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarMissoes(){
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarMissoesPorId(@PathVariable Long id){
        MissoesDTO missao = missoesService.listarMissoesPorId(id);
        if(missao != null){
            return ResponseEntity.ok(missao);
        }return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Missao com ID "+id+" não encontrada");
    }

    //Post -- Mandar uma requisição para criar as missoes
    @PostMapping("/criar")
    public ResponseEntity<String> criarMissao (@RequestBody MissoesDTO missao){
        missoesService.criarMissao(missao);
        return ResponseEntity.status(HttpStatus.CREATED).body("Missão "+ missao.getNome() + ". Criada com sucesso");
    }

    //PUT -- Mandar uma requisição para alterar as missoes
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarMissao(@PathVariable Long id,@RequestBody MissoesDTO missaoAtualizada) {
        MissoesDTO missao =  missoesService.atualizarMissao(id, missaoAtualizada);
        if(missao != null){
            return ResponseEntity.ok(missao);
        }return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Missao com ID "+id+" não encontrada");
    }

    //Delete -- Mandar uma requisição para deletar as missoes
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissao(@PathVariable Long id){
        if(missoesService.listarMissoesPorId(id) != null) {
            missoesService.deletarMissao(id);
            return ResponseEntity.ok().body("Missao com ID "+id + " deletada com sucesso!");
        }return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missão com ID "+id+ " não existe!");
    }
}
