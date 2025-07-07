package dev.java10x.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary="Mensagem de boas vindas", description = "Essa rota da uma mensagem de boas vindas para quem acessa ela")
    public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota";
    }

    //adicionar ninja (Create)
    @PostMapping("/criar")
    @Operation(summary = "Cria um novo ninja", description = "Rota cria um novo ninja e insere no banco de dados")

    @ApiResponses(value={
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do ninja")
    })
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
    @Operation(summary = "Lista o ninja por Id", description = "Rota lista um ninja pelo seu id")

    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
    public ResponseEntity<?> listarNinjaPorId(@PathVariable Long id){

        NinjaDTO ninjas = ninjaService.listarNinjasPorId(id);
        if(ninjas != null){
            return ResponseEntity.ok(ninjas);
        }return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com o id: " + id + " Não existe");
    }

    //alterar dados dos ninjas(update)
    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera o ninja por Id", description = "Rota altera um ninja pelo seu id")

    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado, não foi possivel alterar")
    })
    public ResponseEntity<?> atualizarNinja(
            @Parameter(description = "Usuario manda o id no caminho da requisição")
            @PathVariable Long id,
            @Parameter(description = "Usuario manda os dados do ninja a ser atualizado no corpo da requisição")
            @RequestBody NinjaDTO ninjaAtualizado) {

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
