package users.view;

import post.Request;

import java.util.List;

import core.CoreSystem;
import core.Language;

public class DeanView extends EmployeeView {
    public DeanView() {
        super();
    }

    public void showSignedRequests(List<Request> signedRequests) {
        if (signedRequests == null || signedRequests.isEmpty()) {
            if (CoreSystem.getLanguageMode() == Language.RUS) {
                System.out.println("Нет подписанных запросов.");
            } else if (CoreSystem.getLanguageMode() == Language.ENG) {
                System.out.println("No signed requests available.");
            } else if (CoreSystem.getLanguageMode() == Language.KZ) {
                System.out.println("Қол қойылған сұраныстар жоқ.");
            }
        } else {
            if (CoreSystem.getLanguageMode() == Language.RUS) {
                System.out.println("Подписанные запросы:");
            } else if (CoreSystem.getLanguageMode() == Language.ENG) {
                System.out.println("Signed Requests:");
            } else if (CoreSystem.getLanguageMode() == Language.KZ) {
                System.out.println("Қол қойылған сұраныстар:");
            }

            for (Request request : signedRequests) {
                System.out.println(request);
            }
        }
    }

    public void showRejectedRequests(List<Request> rejectedRequests) {
        if (rejectedRequests == null || rejectedRequests.isEmpty()) {
            if (CoreSystem.getLanguageMode() == Language.RUS) {
                System.out.println("Нет отклонённых запросов.");
            } else if (CoreSystem.getLanguageMode() == Language.ENG) {
                System.out.println("No rejected requests available.");
            } else if (CoreSystem.getLanguageMode() == Language.KZ) {
                System.out.println("Қабылданбаған сұраныстар жоқ.");
            }
        } else {
            if (CoreSystem.getLanguageMode() == Language.RUS) {
                System.out.println("Отклонённые запросы:");
            } else if (CoreSystem.getLanguageMode() == Language.ENG) {
                System.out.println("Rejected Requests:");
            } else if (CoreSystem.getLanguageMode() == Language.KZ) {
                System.out.println("Қабылданбаған сұраныстар:");
            }

            for (Request request : rejectedRequests) {
                System.out.println(request);
            }
        }
    }
}
