package ch5.dop;

import java.io.IOException;

public class FileTransferException extends Exception{
    public FileTransferException(String message, Throwable cause) {
        super(message, cause);
    }
}
