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
    public String criarNinja(){
        return "Ninja Criado";
    }
    //mostrar todos os ninjas (read)
    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas(){
        return ninjaService.listarNinjas();
    }
    //mostrar ninjas por id (read)
    @GetMapping("/listar:id")
    public String mostratTodosOsNinjaPorId(){
        return "Mostrar Ninja por id";
    }

    //alterar dados dos ninjas(update)
    @PutMapping("/alterar:id")
    public String alterarNinjaPorId(){
        return "Alterar Ninja por id";
    }

    //deletar ninja (delete)
    @DeleteMapping("/deletar:id")
    public String deletarNinjaPorId(){
        return "Ninja deletado por id";
    }

}
