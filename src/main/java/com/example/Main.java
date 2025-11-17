package com.example;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int choice;
        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        do {
            System.out.print("""
                \n
                Menu:
                1. Adicionar usuário
                2. Atualizar usuário
                3. Deletar usuário
                4. Listar todos os usuários
                5. Sair
                Escolha uma opção:\s
                """);

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addUser(userDAO, scanner);
                case 2 -> {
                    listUsers(userDAO);
                    updateUser(userDAO, scanner);
                }
                case 3 -> deleteUser(userDAO, scanner);
                case 4 -> listUsers(userDAO);
                case 5 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");

            }
        } while (choice != 5);
    }

    private static void addUser(UserDAO userDAO, Scanner scanner) {
        System.out.println("Nome: ");
        String name = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();

        User newUser = new User(name.trim(), email.trim());

        try {
            userDAO.insertUser(newUser);
        } catch (Exception exception) {
            exception.getMessage();
        }
    }

    private static void updateUser(UserDAO userDAO, Scanner scanner) {
        System.out.println("ID do usuário que será atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        User existingUser = userDAO.selectUser(id);

        if (existingUser == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.println("Nome: ");
        String name = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();

        existingUser.setName(name.trim());
        existingUser.setEmail(email.trim());

        try {
            userDAO.updateUser(existingUser);
            System.out.println("Usuário atualizado com sucesso.");
        } catch (Exception exception) {
            exception.getMessage();
        }
    }

    private static void deleteUser(UserDAO userDAO, Scanner scanner) {
        System.out.println("ID do usuário para deletar: ");
        int id = scanner.nextInt();

        scanner.nextLine();

        try {
            if (userDAO.deleteUser(id)) {
                System.out.println("Usuário deletado com sucesso!");
            } else {
                System.out.println("Usuário não encontrado");
            }
        } catch (SQLException exception) {
            exception.getMessage();
        }
    }

    private static void listUsers(UserDAO userDAO) {
        List<User> users = userDAO.selectAllUsers();

        for (User user : users) {
            System.out.println(user.getId() + " - " + user.getName() + " - " + user.getEmail());
        }
    }
}