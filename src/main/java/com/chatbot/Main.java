package com.chatbot;

import com.chatbot.dao.*;
import com.chatbot.model.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final CategoriaDAO categoriaDAO = new CategoriaDAO();
    private static final FaqDAO faqDAO = new FaqDAO();
    private static final LogAcessoDAO logDAO = new LogAcessoDAO();
    private static final HistoricoMensagemDAO histDAO = new HistoricoMensagemDAO();
    private static final AvaliacaoDAO avalDAO = new AvaliacaoDAO();

    public static void main(String[] args) {

        System.out.println("EXPERT CONSULTING");

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\nMenu: \n1)Categorias \n2)Faq \n3)Logs \n4)Historico \n5)Avaliacoes \n6)Extras \n0)Sair");
            System.out.print("Escolha: ");
            int opt = Integer.parseInt(sc.nextLine());
            try {
                if (opt == 0) { System.out.println("Saindo..."); break; }
                switch (opt) {
                    case 1: menuCategorias(sc); break;
                    case 2: menuFaq(sc); break;
                    case 3: menuLogs(sc); break;
                    case 4: menuHistorico(sc); break;
                    case 5: menuAvaliacoes(sc); break;
                    case 6: menuExtras(sc); break;
                    default: System.out.println("Opcao invalida"); break;
                }
            } catch (SQLException e) {
                System.err.println("Erro SQL: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }
        sc.close();
    }

    // Category menu
    private static void menuCategorias(Scanner sc) throws SQLException {
        System.out.println("Categorias: \n1)Criar \n2)Ler todas \n3)Atualizar \n4)Deletar");
        int o = Integer.parseInt(sc.nextLine());
        switch (o) {
            case 1:
                System.out.print("Nome: "); String nome = sc.nextLine();
                System.out.print("Descricao: "); String desc = sc.nextLine();
                categoriaDAO.create(new Categoria(null, nome, desc));
                System.out.println("Criado.");
                break;
            case 2:
                List<Categoria> cats = categoriaDAO.readAll();
                cats.forEach(System.out::println);
                break;
            case 3:
                System.out.print("ID: "); int id = Integer.parseInt(sc.nextLine());
                System.out.print("Novo nome: "); String nn = sc.nextLine();
                System.out.print("Nova descricao: "); String nd = sc.nextLine();
                categoriaDAO.update(new Categoria(id, nn, nd));
                System.out.println("Atualizado.");
                break;
            case 4:
                System.out.print("ID para deletar: "); int did = Integer.parseInt(sc.nextLine());
                categoriaDAO.delete(did);
                System.out.println("Deletado.");
                break;
            default: System.out.println("Opcao invalida"); break;
        }
    }

    // FAQ menu
    private static void menuFaq(Scanner sc) throws SQLException {
        System.out.println("Faq: \n1)Criar \n2)Ler todas \n3)Atualizar \n4)Deletar \n5)Por categoria");
        int o = Integer.parseInt(sc.nextLine());
        switch (o) {
            case 1:
                System.out.print("Pergunta: "); String p = sc.nextLine();
                System.out.print("Resposta: "); String r = sc.nextLine();
                System.out.print("IdCategoria: "); int ic = Integer.parseInt(sc.nextLine());
                faqDAO.create(new Faq(null, p, r, ic));
                System.out.println("Criado.");
                break;
            case 2:
                List<Faq> faqs = faqDAO.readAll();
                faqs.forEach(System.out::println);
                break;
            case 3:
                System.out.print("ID: "); int id = Integer.parseInt(sc.nextLine());
                System.out.print("Nova pergunta: "); String np = sc.nextLine();
                System.out.print("Nova resposta: "); String nr = sc.nextLine();
                System.out.print("Novo idCategoria: "); int nic = Integer.parseInt(sc.nextLine());
                faqDAO.update(new Faq(id, np, nr, nic));
                System.out.println("Atualizado.");
                break;
            case 4:
                System.out.print("ID para deletar: "); int did = Integer.parseInt(sc.nextLine());
                faqDAO.delete(did);
                System.out.println("Deletado.");
                break;
            case 5:
                System.out.print("IdCategoria: "); int idc = Integer.parseInt(sc.nextLine());
                List<Faq> byCat = faqDAO.findByCategoria(idc);
                byCat.forEach(System.out::println);
                break;
            default: System.out.println("Opcao invalida"); break;
        }
    }

    // Logs menu
    private static void menuLogs(Scanner sc) throws SQLException {
        System.out.println("Logs: \n1)Criar \n2)Ler todas \n3)Atualizar \n4)Deletar");
        int o = Integer.parseInt(sc.nextLine());
        switch (o) {
            case 1:
                System.out.print("DataHoraInicio (YYYY-MM-DDTHH:MM): "); LocalDateTime di = LocalDateTime.parse(sc.nextLine());
                System.out.print("DataHoraFim (YYYY-MM-DDTHH:MM) ou vazio: "); String sfi = sc.nextLine();
                LocalDateTime df = sfi.isBlank() ? null : LocalDateTime.parse(sfi);
                System.out.print("Duracao (minutos) ou vazio: "); String sd = sc.nextLine();
                Integer dur = sd.isBlank() ? null : Integer.parseInt(sd);
                logDAO.create(new LogAcesso(null, di, df, dur));
                System.out.println("Criado.");
                break;
            case 2:
                logDAO.readAll().forEach(System.out::println);
                break;
            case 3:
                System.out.print("ID: "); int id = Integer.parseInt(sc.nextLine());
                System.out.print("DataHoraInicio (YYYY-MM-DDTHH:MM): "); LocalDateTime ndi = LocalDateTime.parse(sc.nextLine());
                System.out.print("DataHoraFim (YYYY-MM-DDTHH:MM) ou vazio: "); String nfi = sc.nextLine();
                LocalDateTime ndf = nfi.isBlank() ? null : LocalDateTime.parse(nfi);
                System.out.print("Duracao (minutos) ou vazio: "); String ndur = sc.nextLine();
                Integer nduration = ndur.isBlank() ? null : Integer.parseInt(ndur);
                logDAO.update(new LogAcesso(id, ndi, ndf, nduration));
                System.out.println("Atualizado.");
                break;
            case 4:
                System.out.print("ID para deletar: "); int did = Integer.parseInt(sc.nextLine());
                logDAO.delete(did);
                System.out.println("Deletado.");
                break;
            default: System.out.println("Opcao invalida"); break;
        }
    }

    // Historico menu
    private static void menuHistorico(Scanner sc) throws SQLException {
        System.out.println("Historico: \n1)Criar \n2)Ler todas \n3)Atualizar \n4)Deletar \n5)Por log");
        int o = Integer.parseInt(sc.nextLine());
        switch (o) {
            case 1:
                System.out.print("IdLog: "); int il = Integer.parseInt(sc.nextLine());
                System.out.print("Origem (CHATBOT|USUARIO): "); String orig = sc.nextLine();
                System.out.print("Conteudo: "); String cont = sc.nextLine();
                System.out.print("DataHora (YYYY-MM-DDTHH:MM): "); LocalDateTime dt = LocalDateTime.parse(sc.nextLine());
                histDAO.create(new HistoricoMensagem(null, il, orig, cont, dt));
                System.out.println("Criado.");
                break;
            case 2:
                histDAO.readAll().forEach(System.out::println);
                break;
            case 3:
                System.out.print("ID: "); int id = Integer.parseInt(sc.nextLine());
                System.out.print("IdLog: "); int iln = Integer.parseInt(sc.nextLine());
                System.out.print("Origem: "); String o2 = sc.nextLine();
                System.out.print("Conteudo: "); String c2 = sc.nextLine();
                System.out.print("DataHora (YYYY-MM-DDTHH:MM): "); LocalDateTime dt2 = LocalDateTime.parse(sc.nextLine());
                histDAO.update(new HistoricoMensagem(id, iln, o2, c2, dt2));
                System.out.println("Atualizado.");
                break;
            case 4:
                System.out.print("ID para deletar: "); int did = Integer.parseInt(sc.nextLine());
                histDAO.delete(did);
                System.out.println("Deletado.");
                break;
            case 5:
                System.out.print("IdLog: "); int idl = Integer.parseInt(sc.nextLine());
                histDAO.findByLog(idl).forEach(System.out::println);
                break;
            default: System.out.println("Opcao invalida"); break;
        }
    }

    // Avaliacoes menu
    private static void menuAvaliacoes(Scanner sc) throws SQLException {
        System.out.println("Avaliacoes: \n1)Criar \n2)Ler todas \n3)Atualizar \n4)Deletar \n5)Contar por nota \n6)Media");
        int o = Integer.parseInt(sc.nextLine());
        switch (o) {
            case 1:
                System.out.print("IdLog: "); int il = Integer.parseInt(sc.nextLine());
                System.out.print("Nota (1-5): "); int nota = Integer.parseInt(sc.nextLine());
                System.out.print("Comentario: "); String c = sc.nextLine();
                avalDAO.create(new Avaliacao(null, il, nota, c));
                System.out.println("Criado.");
                break;
            case 2:
                avalDAO.readAll().forEach(System.out::println);
                break;
            case 3:
                System.out.print("ID: "); int id = Integer.parseInt(sc.nextLine());
                System.out.print("IdLog: "); int iln = Integer.parseInt(sc.nextLine());
                System.out.print("Nota: "); int nn = Integer.parseInt(sc.nextLine());
                System.out.print("Comentario: "); String nc = sc.nextLine();
                avalDAO.update(new Avaliacao(id, iln, nn, nc));
                System.out.println("Atualizado.");
                break;
            case 4:
                System.out.print("ID para deletar: "); int did = Integer.parseInt(sc.nextLine());
                avalDAO.delete(did);
                System.out.println("Deletado.");
                break;
            case 5:
                System.out.print("Nota para contar: "); int qn = Integer.parseInt(sc.nextLine());
                int count = avalDAO.countByNota(qn);
                System.out.println("Total com nota " + qn + ": " + count);
                break;
            case 6:
                double avg = avalDAO.averageNota();
                System.out.println("Media das notas: " + avg);
                break;
            default: System.out.println("Opcao invalida"); break;
        }
    }

    private static void menuExtras(Scanner sc) throws SQLException {
        System.out.println("Extras: \n1)Buscar Faq por categoria \n2)Contar avaliacoes por nota \n3)Media avaliacoes \n4)Listar historico por log");
        int o = Integer.parseInt(sc.nextLine());
        switch (o) {
            case 1:
                System.out.print("IdCategoria: "); int ic = Integer.parseInt(sc.nextLine());
                faqDAO.findByCategoria(ic).forEach(System.out::println);
                break;
            case 2:
                System.out.print("Nota: "); int n = Integer.parseInt(sc.nextLine());
                System.out.println("Count: " + avalDAO.countByNota(n));
                break;
            case 3:
                System.out.println("Media: " + avalDAO.averageNota());
                break;
            case 4:
                System.out.print("IdLog: "); int il = Integer.parseInt(sc.nextLine());
                histDAO.findByLog(il).forEach(System.out::println);
                break;
            default: System.out.println("Opcao invalida"); break;
        }
    }
}