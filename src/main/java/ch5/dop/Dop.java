package ch5.dop;

import java.nio.file.Path;

//void transactionalMove(Path from, Path to)
//
//Метод побайтово (с буферизацией,
// но без чтения файла целиком в память)
// считывает из from, записывает в to.
// После записи всей информации в to уделяет from.
// В случае ошибок откатывает операцию,
// насколько это возможно
public class Dop {

    public static void main(String[] args) throws FileTransferException {
        ByteMover byteMover = new ByteMover();
        Path from = Path.of("src/main/java/ch5/dop/file1");
        Path to = Path.of("src/main/java/ch5/dop/file2");
        byteMover.transactionalMove(to, from);
    }

}
