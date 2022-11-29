package commands;

import bankSystems.ApplicationMenu;

public class CommandRenderApplicationMenu extends Command {
    private ApplicationMenu menu;
    public CommandRenderApplicationMenu(ApplicationMenu menu) {
        this.menu = menu;
    }
    @Override
    public boolean execute() {
        menu.auth();
        return true;
    }

}
