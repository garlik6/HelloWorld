package ch5.dop;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ByteMover {
    private int bufferSize = 8192;
    private char[] buffer = new char[bufferSize];
    private int lastNRead = 0;
    public ByteMover() {
    }
    public ByteMover(int bufferSize) {
        this.bufferSize = bufferSize;
    }
    public void transactionalMove(Path to, Path from) throws FileTransferException {
        try (var reader = new BufferedReader(new FileReader(from.toFile()), bufferSize);
             var writer = new BufferedWriter(new FileWriter(to.toFile()), bufferSize)) {
            write(reader, writer);
//            throwException();
            Files.delete(from);
//            Files.delete(to);
//            throwException();
        } catch (IOException e) {
            try {
                rollback(to, from);
                throw new FileTransferException("Transaction Failed!", e);
            } catch (IOException rollbackException) {
                FileTransferException fileTransferException = new FileTransferException("Transaction Failed! Rollback Failed!", e);
                fileTransferException.addSuppressed(rollbackException);
                throw fileTransferException;
            }
        }
    }

    public void transactionalMoveRollback(Path initial, Path result) throws IOException {
        try (var reader = new BufferedReader(new FileReader(result.toFile()), bufferSize);
             var writer = new BufferedWriter(new FileWriter(initial.toFile()), bufferSize)) {
            write(reader, writer);
            Files.delete(result);
        } catch (IOException e) {
            throw new IOException();
        }
    }

    private void write(BufferedReader reader, BufferedWriter writer) throws IOException {
        while (reader.ready()) {
            int nRead;
            while ((nRead = reader.read(buffer, 0, bufferSize)) >= 0) {
                writer.write(buffer, 0, nRead);
                lastNRead = nRead;
            }
        }
    }

    private void rollback(Path result, Path initial) throws IOException {
        System.out.println("ROLLBACK__ATTEMPT");
        if (!Files.exists(initial) && Files.exists(result)) {
            transactionalMoveRollback(initial, result);
        }
        if (!Files.exists(initial) && !Files.exists(result)) {
            try (var writer = Files.newBufferedWriter(initial)) {
                writer.write(buffer, 0, lastNRead);
            }
        }
        if (Files.exists(result)) {
            Files.delete(result);
        }
//        throwException();
        System.out.println("ROLLBACK__SUCCESSFUL");
    }

    private void throwException() throws IOException {
        throw new IOException();
    }
}
