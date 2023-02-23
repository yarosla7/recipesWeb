package net.recipes.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    @GetMapping("/")
    public String start(){
        return "Приложение запущено";
    }

    @GetMapping("/info")
    public String info(){
        return "Ученик: Ярослав." +
                "\n Название проекта: 'Книга рецептов'." +
                "\n Дата создания: 01.02.2023. " +
                "\nОписание проекта: Это будет книга с рецептами, где можно будет смотреть, и, возможно, добавлять,удалять новые рецепты.";
    }
}