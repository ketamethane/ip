package seedu.duke;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Scanner;

/**
 * Represents a Parser object that takes in a user command.
 */
public class Parser {


    static void validate(String str, String type) throws DukeException {
        if (!type.equals("todo") && !type.equals("deadline") && !type.equals("event")) {
            throw new DukeException("");
        }
    }

    /**
     * Takes in a user input and informs Duke what to do.
     * @param command The main action of Duke.
     * @param input The whole user input.
     * @param taskList tasks attribute of Duke.
     * @param isOpen ArrayList containing a boolean to check whether Duke is open or closed
     * @param scanner Scanner object.
     */
    public static void parse(String command, String input, TaskList taskList, ArrayList<Boolean> isOpen,
                             Scanner scanner) {
        String[] inputArr = input.split(" ");
//        String command = inputArr[0];
        System.out.println("____________________________________________________");

        switch (command) {
        case "list":
            taskList.printList();
            break;
        case "mark":
            int taskNum = Integer.parseInt(inputArr[1]);
            try {
                taskList.markTask(taskNum);
                Storage.rewriteTasks(taskList);
            } catch (IndexOutOfBoundsException | IOException e) {
                System.out.println("Task does not exist!");
            }
            String output = String.format("Nice! I've marked this task as done:\n%s",
                    taskList.taskList.get(taskNum - 1));
            System.out.println(output);
            break;
        case "unmark":
            int taskNum2 = Integer.parseInt(inputArr[1]);
            try {
                taskList.unmarkTask(taskNum2);
                Storage.rewriteTasks(taskList);
            } catch (IndexOutOfBoundsException | IOException e) {
                System.out.println("Task does not exist!");
            }
            String output2 = String.format("OK, I've marked this task as not done yet:\n%s",
                    taskList.taskList.get(taskNum2 - 1));
            System.out.println(output2);
            break;
        case "delete":
            try {
                int taskNum3 = Integer.parseInt(inputArr[1]);
                taskList.deleteTask(taskNum3);
                Storage.rewriteTasks(taskList);
            } catch (IOException e) {

            }
            break;
        case "todo":
            try {
                TaskList.addTask("todo", input);
                Storage.rewriteTasks(taskList);
            } catch (IndexOutOfBoundsException | DukeException | IOException e) {
                System.out.println(e.getMessage());
                String output3 = String.format("Oops!! The description of a %s cannot be empty", inputArr[0]);
                System.out.println(output3);
            }
            break;
        case "deadline":
            try {
                TaskList.addTask("deadline", input);
                Storage.rewriteTasks(taskList);
            } catch (IndexOutOfBoundsException | DukeException | IOException e) {
                System.out.println(e.getMessage());
                String output3 = String.format("Oops!! The description of a %s cannot be empty", inputArr[0]);
                System.out.println(output3);
            }
            break;
        case "event":
            try {
                TaskList.addTask("event", input);
                Storage.rewriteTasks(taskList);
            } catch (IndexOutOfBoundsException | DukeException | IOException e) {
                System.out.println(e.getMessage());
                String output3 = String.format("Oops!! The description of a %s cannot be empty", inputArr[0]);
                System.out.println(output3);
            }
            break;
        case "find":
            String keyword = inputArr[1];
            ArrayList<Task> tasks = taskList.find(keyword);
            Ui.showMatchingTasks(tasks);
            break;
        case "bye":
            isOpen.set(0, false);
            Ui.showGoodbyeMessage();
            scanner.close();
            break;
        default:
            try {
                validate(input, inputArr[0]);
            } catch (DukeException e) {
                System.out.println("Oh no!! I'm sorry, but I don't know what that means :(");
            }

        }
        }
    }