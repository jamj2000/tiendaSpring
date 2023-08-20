package com.example.controlador;

import com.example.modelo.Articulo;
import com.example.repositorio.ArticuloRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticuloControlador {

    @Autowired
    private ArticuloRepositorio repositorio;

    @GetMapping("/articulos")
    public String listaArticulos (Model model){
        model.addAttribute("articulos", repositorio.findAll());
        return "articulos";
    }

    @GetMapping("/articulos/{id}")
    public String vistaArticulo (Model model, @PathVariable Long id){
        model.addAttribute("articulo", repositorio.findById(id));
        return "articulo";
    }

    @GetMapping("/articulos/{id}/eliminar")
    public String borrarArticulo (@PathVariable Long id){
        if (repositorio.existsById(id))
            repositorio.deleteById(id);
        return "redirect:/articulos";
    }


    @GetMapping("/articulos/{id}/editar")
    public String editarArticulo (Model model, @PathVariable Long id){
//        repositorio.findById(id).ifPresent( articulo ->  model.addAttribute("articulo", articulo));
        model.addAttribute("articulo", repositorio.findById(id));
        return "articulo-formulario";
    }

    @GetMapping("/articulos/crear")
    public String formularioArticulo (Model model){
        model.addAttribute("articulo", new Articulo());
        return "articulo-formulario";
    }

    @PostMapping("/articulos")
    public String editarArticulo (@ModelAttribute Articulo articulo){
        repositorio.save(articulo);
        return "redirect:/articulos";
    }

}
