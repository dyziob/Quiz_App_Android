package com.example.projekt.Repository;

import com.example.projekt.QuestionsList;

import java.util.ArrayList;
import java.util.List;

public class QuestionsBank {

    private static List<QuestionsList> javaQuestions(){

        final List<QuestionsList> questionsLists = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Który z typów danych w Javie jest uważany za typ prymitywny?", "Integer", "String", "int", "Double", "int", "");
        final QuestionsList question2 = new QuestionsList("Co oznacza 'Java Virtual Machine' (JVM)?", "Jest to kompilator Javy.", "Jest to zestaw narzędzi do programowania w Javie.", "Jest to środowisko wykonawcze, które wykonuje kod Javy.", "Jest to środowisko programistyczne dla Javy.", "Jest to środowisko wykonawcze, które wykonuje kod Javy.", "");
        final QuestionsList question3 = new QuestionsList("Które z poniższych słów kluczowych jest używane do definiowania klas w Javie?", "class", "type", "struct", "def", "class", "");
        final QuestionsList question4 = new QuestionsList("Która z poniższych metod jest wywoływana automatycznie podczas tworzenia obiektu klasy?", "start()", "run()", "init()", "constructor()", "constructor()", "");
        final QuestionsList question5 = new QuestionsList("Która instrukcja jest używana do ręcznego zgłaszania wyjątku w Javie?", "throw", "raise", "except", "try", "throw", "");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);

        return questionsLists;
    }

    private static List<QuestionsList> androidQuestions(){

        final List<QuestionsList> questionsLists = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Co to jest 'Activity' w Androidzie?", "Jednostka kodu reprezentująca ekran z interfejsem użytkownika.", "Plik konfiguracyjny aplikacji.", "Mechanizm debugowania.", "Komponent zarządzający bazami danych.", "Jednostka kodu reprezentująca ekran z interfejsem użytkownika.", "");
        final QuestionsList question2 = new QuestionsList("Co to jest 'Intent' w Androidzie?", "Mechanizm przesyłania danych między komponentami.", "Interfejs projektowania interfejsu użytkownika.", "Komponent do łączenia z zasobami internetowymi.", "Klasa zarządzająca bazami danych.", "Mechanizm przesyłania danych między komponentami.", "");
        final QuestionsList question3 = new QuestionsList("Co to jest 'Layout' w Androidzie?", "Struktura określająca układ i wygląd interfejsu użytkownika.", "Plik konfiguracyjny uprawnień aplikacji.", "Narzędzie do debugowania interfejsu.", "Zbiór klas i interfejsów do tworzenia interfejsu.", "Struktura określająca układ i wygląd interfejsu użytkownika.", "");
        final QuestionsList question4 = new QuestionsList("Co to jest 'Fragment' w Androidzie?", "Moduł aplikacji używany w różnych aktywnościach.", "Klasa reprezentująca ekran interfejsu użytkownika.", "Komponent do komunikacji z serwerami Firebase.", "Komponent przetwarzający dane.", "Moduł aplikacji używany w różnych aktywnościach.", "");
        final QuestionsList question5 = new QuestionsList("Co to jest 'Manifest' w Androidzie?", "Plik konfiguracyjny aplikacji zawierający informacje o aplikacji.", "Komponent wyświetlający komunikaty użytkownikowi.", "Narzędzie do debugowania aplikacji.", "Klasa zarządzająca sesją użytkownika.", "Plik konfiguracyjny aplikacji zawierający informacje o aplikacji.", "");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);

        return questionsLists;
    }

    private static List<QuestionsList> kotlinQuestions(){

        final List<QuestionsList> questionsLists = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Który z typów danych w Kotlinie jest uważany za typ prymitywny?", "Int", "String", "Integer", "Double", "Int", "");
        final QuestionsList question2 = new QuestionsList("Który operator jest używany do porównywania dwóch wartości w Kotlinie?", "==", "=", "!=", "<>", "==", "");
        final QuestionsList question3 = new QuestionsList("Która z poniższych kolekcji w Kotlinie zapewnia szybkie wstawianie i pobieranie elementów?", "ArrayList", "LinkedList", "HashSet", "HashMap", "ArrayList", "");
        final QuestionsList question4 = new QuestionsList("Która z poniższych metod jest używana do rozpoczęcia wykonywania nowego wątku w Kotlinie?", "startThread()", "runThread()", "begin()", "start()", "start()", "");
        final QuestionsList question5 = new QuestionsList("Który znak w Kotlinie jest używany do oznaczania opcjonalnych wartości (nullable)?", "?", "!", ".", ":", "?", "");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);

        return questionsLists;
    }

    private static List<QuestionsList> csharpQuestions(){

        final List<QuestionsList> questionsLists = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("Który z typów danych w C# jest uważany za typ prymitywny?", "int", "string", "Integer", "double", "int", "");
        final QuestionsList question2 = new QuestionsList("Który operator jest używany do porównywania dwóch wartości w C#?", "==", "=", "!=", "<>", "==", "");
        final QuestionsList question3 = new QuestionsList("Która z poniższych kolekcji w C# zapewnia szybkie wstawianie i pobieranie elementów?", "List", "LinkedList", "HashSet", "Dictionary", "List", "");
        final QuestionsList question4 = new QuestionsList("Która z poniższych metod jest używana do rozpoczęcia wykonywania nowego wątku w C#?", "startThread()", "runThread()", "begin()", "Start()", "Start()", "");
        final QuestionsList question5 = new QuestionsList("Która z poniższych struktur danych w C# jest niemutowalna?", "List", "ArrayList", "HashSet", "ImmutableList", "ImmutableList", "");

        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);

        return questionsLists;
    }

    public static List<QuestionsList> getQuestions(String selectedTopicName) {
        switch(selectedTopicName){
            case "Java":
                return javaQuestions();
            case "Android":
                return androidQuestions();
            case "Kotlin":
                return kotlinQuestions();
            default:
                return csharpQuestions();
        }
    }
}
