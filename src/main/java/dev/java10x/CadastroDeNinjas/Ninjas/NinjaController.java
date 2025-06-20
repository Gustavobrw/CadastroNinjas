package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota";
    }

    //adicionar ninja (Create)
    @PostMapping("/criar")
    public NinjaModel criarNinja(@RequestBody NinjaModel ninja){
        return ninjaService.criarNinja(ninja);

    }
    //mostrar todos os ninjas (read)
    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas(){
        return ninjaService.listarNinjas();
    }
    //mostrar ninjas por id (read)
    @GetMapping("/listar/{id}")
    public NinjaModel listarNinjaPorId(@PathVariable Long id){
        return ninjaService.listarNinjasPorId(id);
    }

    //alterar dados dos ninjas(update)
    @PutMapping("/alterar/{id}")
    public NinjaModel atualizarNinja(@PathVariable Long id, @RequestBody NinjaModel ninjaAtualizado)
    {
        return ninjaService.atualizarNinja(id, ninjaAtualizado);
    }

    //deletar ninja (delete)
    @DeleteMapping("/deletar/{id}")
    public void deletarNinjaPorId(@PathVariable Long id){
        ninjaService.deletarNinjaPorId(id);
        System.out.println("Ninja deletado com sucesso");
    }


}
