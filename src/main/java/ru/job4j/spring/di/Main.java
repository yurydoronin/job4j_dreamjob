package ru.job4j.spring.di;

public class Main {

    public static void main(String[] args) {
        Context context = new Context();
        context.reg(Store.class);
        context.reg(StartUI.class);

        StartUI ui = context.get(StartUI.class);

        ui.add("Yury Doronin");
        ui.add("Diana Volkova");
        ui.print();
    }
}
