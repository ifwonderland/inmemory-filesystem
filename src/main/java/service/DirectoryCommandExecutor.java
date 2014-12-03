package service;

import model.Directory;

import java.io.InvalidObjectException;

/**
 * Main service class that executes commands sequentially. Assuming there is only one thread executing.
 * <p/>
 * Created by Shaochen Huang on 12/2/14.
 */
public interface DirectoryCommandExecutor {


    /**
     * Changes the current working directory. The working directory begins at '/'. The special characters '.' should not modify the current working directory and '..' should move the current working directory to the parent.
     *
     * @param workingDirectory
     * @param toDirectory
     * @return
     * @throws IllegalArgumentException if target directory cannot be found
     */
    Directory navigate(final Directory workingDirectory, final String toDirectory) throws IllegalArgumentException;


    /**
     * Creates a new directory with the current working directory as the parent.
     *
     * @param workingDirectory
     * @param dirName
     * @return newly created directory
     * @throws IllegalArgumentException if dir name is invalid
     */
    Directory makeDirectory(final Directory workingDirectory, final String dirName) throws IllegalArgumentException;


    /**
     * Removes a directory from the current working directories's children. This is only allowed if the directory you are removing contains no children.
     *
     * @param directory: cannot be null
     * @throws java.io.InvalidObjectException if directory to be removed has children, or if directory passed in is null
     */
    void removeDirectory(final Directory directory) throws InvalidObjectException;


    /**
     * Prints the current working directory's path from the root. Example: /school/homework
     *
     * @param directory
     * @return string represents directory path
     */
    String print(final Directory directory);


    /**
     * Lists the children of the current working directory. These are printed on a single line with a single space character separating them. Example: math history spanish
     *
     * @param directory
     * @return empty string is no children, otherwise return single space separated child directory names
     */
    String list(final Directory directory);


}
