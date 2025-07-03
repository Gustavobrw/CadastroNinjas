package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota";
    }

    //adicionar ninja (Create)
    @PostMapping("/criar")
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja){

            NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Ninja Criado com sucesso: " + novoNinja.getNome() + "(ID): " + novoNinja.getId());
    }
    //mostrar todos os ninjas (read)
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas(){
       List<NinjaDTO> ninjas =  ninjaService.listarNinjas();
       return ResponseEntity.ok(ninjas);
    }

    //mostrar ninjas por id (read)
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjaPorId(@PathVariable Long id){

        NinjaDTO ninjas = ninjaService.listarNinjasPorId(id);
        if(ninjas != null){
            return ResponseEntity.ok(ninjas);
        }return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com o id: " + id + " Não existe");
    }

    //alterar dados dos ninjas(update)
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> atualizarNinja(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado) {

            NinjaDTO ninja = ninjaService.atualizarNinja(id, ninjaAtualizado);
           if (ninja != null){
               return ResponseEntity.ok(ninja);
           }return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com ID "+id+" não encontrado");
    }

    //deletar ninja (delete)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id){
       if (ninjaService.listarNinjasPorId(id) != null) {
           ninjaService.deletarNinjaPorId(id);
           return ResponseEntity.ok().body("Ninja com o ID " + id + " Deletado com sucesso!");
       } return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("O ninja com o id "+id+" Não encontrado");
    }


}
