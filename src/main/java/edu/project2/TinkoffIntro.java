package edu.project1.project1;

@SuppressWarnings({"HideUtilityClassConstructor", "RegexpSinglelineJava", "MagicNumber", "LineLength"})
public class TinkoffIntro {
    public static void generateTinkoffIntro() {
        System.out.println("\n"
            + "\u001B[33m\n"
            + renderEmptySpace(60)
            + "  ████████╗██╗███╗   ██╗██╗  ██╗ ██████╗ ███████╗███████╗\n"
            + renderEmptySpace(60)
            + "  ╚══██╔══╝██║████╗  ██║██║ ██╔╝██╔═══██╗██╔════╝██╔════╝\n"
            + renderEmptySpace(60)
            + "     ██║   ██║██╔██╗ ██║█████╔╝ ██║   ██║█████╗  █████╗  \n"
            + renderEmptySpace(60)
            + "     ██║   ██║██║╚██╗██║██╔═██╗ ██║   ██║██╔══╝  ██╔══╝  \n"
            + renderEmptySpace(60)
            + "     ██║   ██║██║ ╚████║██║  ██╗╚██████╔╝██║     ██║     \n"
            + renderEmptySpace(60)
            + "     ╚═╝   ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝ ╚═════╝ ╚═╝     ╚═╝     "
            + "\u001B[0m\n"
            + "                                                         \n");

        System.out.println(renderEmptySpace(65)
            + "\u001B[3m"
            + "Мы покупаем ёлки в Норвегии и дрова в Финляндии...");
        System.out.println(renderEmptySpace(110)
            + "- Олег Тиньков "
            + "\uD83D\uDC68\u200D\uD83E\uDDB3"
            + "\u001B[0m");
        System.out.println("\n"
            + "    Олег Юрьевич Тиньков, ранее будучи владельцем банка Тинькофф, на одном из своих интревью признался,"
            + "\n"
            + "    что крайне обеспокоен ситуацией с закупкой Новогодних ёлок за границей. А я, будучи разработчиком, а не бизнесменом,"
            + "\n"
            + "    лишь вдохновился этой цитатой и решил, что должен помочь Олегу Юрьевичу найти свою ёлочку в сложнейшем лабиринте жизненных событий:"
            + "\n\n");
    }

    public static String renderEmptySpace(int count) {
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < count; i++) {
            spaces.append(" ");
        }
        return spaces.toString();
    }

}
