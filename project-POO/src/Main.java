import java.io.IOException;

import cli.Frontend;
import manager.Manager;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        
        Manager manager = new Manager();
        Frontend frontend = new Frontend(manager);
        frontend.start();
    }
}