import bankSystems.Authorization;
import sqlite3engine.DatabaseHandler;
import bankSystems.Account;
import bankSystems.ApplicationMenu;
import commands.CommandRenderApplicationMenu;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        CommandRenderApplicationMenu command = new CommandRenderApplicationMenu(new ApplicationMenu());
        command.execute();
    }
}