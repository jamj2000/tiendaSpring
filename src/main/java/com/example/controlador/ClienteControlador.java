package com.example.controlador;

import com.example.modelo.Cliente;
import com.example.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClienteControlador {

    @Autowired
    private ClienteRepositorio repositorio;

    @GetMapping("/clientes")
    public String listaClientes (Model model){
        model.addAttribute("clientes", repositorio.findAll());
        return "clientes";
    }

    @GetMapping("/clientes/{id}")
    public String vistaCliente (Model model, @PathVariable Long id){
        model.addAttribute("cliente", repositorio.findById(id));
        return "cliente";
    }

    @GetMapping("/clientes/{id}/eliminar")
    public String borrarCliente (@PathVariable Long id){
        if (repositorio.existsById(id))
            repositorio.deleteById(id);
        return "redirect:/clientes";
    }


    @GetMapping("/clientes/{id}/editar")
    public String editarCliente (Model model, @PathVariable Long id){
//        repositorio.findById(id).ifPresent( cliente ->  model.addAttribute("cliente", cliente));
        model.addAttribute("cliente", repositorio.findById(id));
        return "cliente-formulario";
    }

    @GetMapping("/clientes/crear")
    public String formularioCliente (Model model){
        model.addAttribute("cliente", new Cliente());
        return "cliente-formulario";
    }

    @PostMapping("/clientes")
    public String editarCliente (@ModelAttribute Cliente cliente){
        repositorio.save(cliente);
        return "redirect:/clientes";
    }

}
