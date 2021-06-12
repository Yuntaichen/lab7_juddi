package com.labs.client;


import com.labs.client.generated.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class WebServiceClient {

    public void serviceRequest(String accessPoint) throws MalformedURLException {
        URL url = new URL(accessPoint);
        CRUDService studentService = new CRUDService(url);

        // Консольный выбор CRUD метода
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose CRUD method (input CREATE, READ, UPDATE or DELETE), or input 'exit' for exit:");
        String chosenMethod;
        do {
            chosenMethod = scanner.nextLine();
            // проверим строку на наличие аргумента: если строка не является пустой и не состоит из пробелов, то
            // проверяем на наличие одной из возможных операций
            if (chosenMethod != null && !chosenMethod.trim().isEmpty()) {

                switch (chosenMethod) {
                    case ("CREATE"):
                        createStudentRow(studentService);
                        System.out.println("That's it! You can choose another CRUD method or input 'exit' for exit");
                        break;
                    case ("READ"):
                        readStudentRowsByFields(studentService);
                        System.out.println("That's it! You can choose another CRUD method or input 'exit' for exit");
                        break;
                    case ("UPDATE"):
                        updateStudentRowById(studentService);
                        System.out.println("That's it! You can choose another CRUD method or input 'exit' for exit");
                        break;
                    case ("DELETE"):
                        deleteStudentRowById(studentService);
                        System.out.println("That's it! You can choose another CRUD method or input 'exit' for exit");
                        break;
                    case ("exit"):
                        System.out.println("Bye-Bye!");
                        break;
                    default:
                        System.out.println("You can input just CREATE, READ, UPDATE or DELETE!");
                        System.out.println("Try again or use 'exit' for exit.");
                        break;
                }
            }
        } while (!Objects.equals(chosenMethod, "exit"));
        scanner.close();
    }

    private static void updateStudentRowById(CRUDService studentService) {

        String status = "Bad";

        // Консольный ввод аргументов
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input rowID (integer): ");
        String rowId = scanner.nextLine();


        System.out.println("What fields you want update for this row? \n" +
                    "Choose fields from 'name', 'surname', 'age', 'student_id', 'mark' and input it's below \n" +
                    " separated by comma without spaces");
        String updateRows = scanner.nextLine();

        // Преобразуем полученную строку в список аргументов
        String[] updateRowsList = updateRows.split(",", -1);

        Map<String, String> updateRowsMap = new HashMap<>();
        updateRowsMap.put("name", "");
        updateRowsMap.put("surname", "");
        updateRowsMap.put("age", "");
        updateRowsMap.put("student_id", "");
        updateRowsMap.put("mark", "");

        for (String row : updateRowsList) {
            switch (row) {
                case "name":
                    System.out.println("Input new value for 'name' field:");
                    String name = scanner.nextLine();
                    if (name != null && !name.trim().isEmpty()) {
                        updateRowsMap.put("name", name);
                    } else {
                        System.out.println("Field 'name' is incorrect and will not be updated!");
                    }
                    break;

                case "surname":
                    System.out.println("Input new value for 'surname' field:");
                    String surname = scanner.nextLine();
                    if (surname != null && !surname.trim().isEmpty()) {
                        updateRowsMap.put("surname", surname);
                    } else {
                        System.out.println("Field 'surname' is incorrect and will not be updated!");
                    }
                    break;

                case "age":
                    System.out.println("Input new value for 'age' field (integer):");
                    String age = scanner.nextLine();
                    if (!age.trim().isEmpty()) {
                        updateRowsMap.put("age", age);
                    } else {
                        System.out.println("Field 'age' is not an integer and will not be updated!");
                    }
                    break;

                case "student_id":
                    System.out.println("Input new value for 'student_id' field (integer):");
                    String student_id = scanner.nextLine();
                    if (!student_id.trim().isEmpty()) {
                        updateRowsMap.put("student_id", student_id);
                    } else {
                        System.out.println("Field 'student_id' is not an integer and will not be updated!");
                    }
                    break;

                case "mark":
                    System.out.println("Input new value for 'mark' field:");
                    String mark = scanner.nextLine();
                    if (mark != null && !mark.trim().isEmpty()) {
                        updateRowsMap.put("mark", mark);
                    } else {
                        System.out.println("Field 'mark' is incorrect and will not be updated!");
                    }
                    break;

                default:
                    System.out.println("Incorrect request. Try again!");
                    break;
                }
            }

            System.out.println("You input new values for row " + rowId + ":\n" + updateRowsMap);
            System.out.println("Do you really want to change this fields for row " + rowId + "? (y -> yes, other -> no)");
            String agree = scanner.nextLine();

            if (agree.equals("y")) {
                try {
                    status = studentService.getStudentWebServicePort().updateStudent(
                            rowId,
                            updateRowsMap.get("name"),
                            updateRowsMap.get("surname"),
                            updateRowsMap.get("age"),
                            updateRowsMap.get("student_id"),
                            updateRowsMap.get("mark"));
                } catch (CastToIntException | EmptyFieldException | FieldValueException | RowIsNotExistsException e) {
                    e.printStackTrace();
                }
            } else {
                    System.out.println("You just cancel your request. Try another request or exit.");
            }



        System.out.println("Status: " + status);
    }


    private static void deleteStudentRowById(CRUDService studentService) {
        String status = "0";

        // Консольный ввод аргументов
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input rowID (integer): ");
        String rowId = scanner.nextLine();
        try {
            status = studentService.getStudentWebServicePort().deleteStudent(rowId);
        }  catch (CastToIntException | RowIsNotExistsException ex) {
            Logger.getLogger(WebServiceClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Status: " + status);
    }


    private static void readStudentRowsByFields(CRUDService studentService) {
        List<Student> students = studentService.getStudentWebServicePort().getStudentsByFields(getArgsForSearch());
        for (Student student : students) {
            System.out.println("Student: " + "name=" + student.getName() + ", surname=" + student.getSurname() +
                    ", age=" + student.getAge() + ", student_id=" + student.getStudentId() + ", mark=" +
                    student.getMark() + ";");
        }
    }

    private static void createStudentRow(CRUDService studentService) {

        String status = "0";

        // Консольный ввод аргументов
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input name: ");
        String name = scanner.nextLine();
        System.out.print("Input surname: ");
        String surname = scanner.nextLine();
        System.out.print("Input age (integer): ");
        String age = scanner.nextLine();
        System.out.print("Input student_id (integer): ");
        String studentId = scanner.nextLine();
        System.out.print("Input mark: ");
        String mark = scanner.nextLine();

        // проверим ввод на наличие значений: строка не является пустой и не состоит из пробелов
        if ((name != null && !name.trim().isEmpty())  &&
                (surname != null && !surname.trim().isEmpty()) &&
                (age != null && !age.trim().isEmpty()) &&
                (studentId != null && !studentId.trim().isEmpty()) &&
                (mark != null && !mark.trim().isEmpty())) {

            try {
                status = studentService.getStudentWebServicePort().createStudent(name, surname, age, studentId, mark);
            } catch (CastToIntException | EmptyFieldException | FieldValueException e) {
                e.printStackTrace();
            }
            System.out.println("Status: " + status);
        }
        else {
            System.out.println("Your request is incorrect!");
        }
    }

    private static List<String> getArgsForSearch() {
        List<String> given_args = new ArrayList<>();

        // Консольный ввод аргументов
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input arguments below (one line = one argument, input 'complete' for get results): ");
        String given_arg;
        do {
            given_arg = scanner.nextLine();
            // проверим строку на наличие аргумента: если строка не является пустой и не состоит из пробелов, то
            // добавляем аргумент в массив
            if (given_arg != null && !given_arg.trim().isEmpty()) {
                given_args.add(given_arg);
            }
        } while (!Objects.equals(given_arg, "complete"));

        return given_args;
    }
}