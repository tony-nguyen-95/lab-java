package services;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OptionsService {
    private String name;
    private String[] options;
    private Scanner scanner;
    private int selectedOption = -1;
    private int levelOptions = 0;

    public OptionsService(String name, String[] options, int level) {
        this.scanner = new Scanner(System.in);
        this.name = name;
        this.options = options;
        this.levelOptions = level;
        printOptionsAndSelectOption();
    }

    public int getSelectedOption() {
        return this.selectedOption;
    }

    private void printOptionsAndSelectOption() {
        System.out.println(renderSpaceLevel(this.levelOptions) + "** " + this.name + " **");

        for (int i = 1; i < this.options.length; i++) {
            System.out.println(renderSpaceLevel(this.levelOptions) + "| " + i + ". " + this.options[i]);
        }

        System.out.println(renderSpaceLevel(this.levelOptions) + "| 0. " + this.options[0]);

        while (this.selectedOption < 0 || this.selectedOption >= this.options.length) {
            System.out.print(renderSpaceLevel(this.levelOptions) + "-> Chọn " + this.name.toLowerCase() + ": ");
            try {
                this.selectedOption = scanner.nextInt();
                if (this.selectedOption < 0 || this.selectedOption >= this.options.length) {
                    System.out.println(renderSpaceLevel(this.levelOptions) + "| " + this.name
                            + " không tồn tại, vui lòng nhập lại!");
                }
            } catch (InputMismatchException e) {
                String allowedOptions = "";
                for (int i = this.options.length - 1; i >= 0; i--) {
                    allowedOptions += i + (i != 0 ? ", " : "");
                }
                System.out.println(
                        renderSpaceLevel(this.levelOptions) + "* Error: Chức năng là các số " + allowedOptions + "!");
                scanner.nextLine();
            }
        }
    }

    private String renderSpaceLevel(int level) {
        String spaceLevel = "";
        for (int i = 0; i < this.levelOptions; i++) {
            spaceLevel += "  ";
        }
        return spaceLevel;
    }

}
