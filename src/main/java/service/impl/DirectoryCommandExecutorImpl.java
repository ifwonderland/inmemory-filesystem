package service.impl;

import model.Constants;
import model.Directory;
import model.InMemoryFileSystemStorage;
import org.apache.commons.lang.StringUtils;
import service.DirectoryCommandExecutor;
import util.DirectoryUtils;

import java.io.InvalidObjectException;

/**
 * Created by Shaochen Huang on 12/2/14.
 */
public class DirectoryCommandExecutorImpl implements DirectoryCommandExecutor {
    @Override
    public Directory navigate(Directory workingDirectory, String toDirectory) throws IllegalArgumentException {
        if (workingDirectory == null || toDirectory == null)
            throw new IllegalArgumentException("Current working dir and toPath string cannot be null. ");

        //special case, empty space or . remains in current dir
        if (StringUtils.isBlank(toDirectory) || toDirectory.equals(Constants.CUR_DIR))
            return workingDirectory;

        if (toDirectory.equals(Constants.PARENT_DIR)) {
            Directory parent = workingDirectory.getParent();

            if (parent == null)
                throw new IllegalArgumentException("Trying to change directory to a parent directory, which is null, invalid operation. ");
            return parent;

        }

        if (toDirectory.equals(Constants.ROOT_DIR))
            return InMemoryFileSystemStorage.INSTANCE.getRootDirectory();

        return workingDirectory.getChild(toDirectory);
    }

    @Override
    public Directory makeDirectory(Directory workingDirectory, String dirName) throws IllegalArgumentException {
        //new dir with working dir as its parent
        Directory newDir = new Directory(dirName, workingDirectory);
        //add this new dir to the working directry children dir list
        workingDirectory.addChild(newDir);
        return newDir;
    }

    @Override
    public void removeDirectory(Directory directory) throws InvalidObjectException {
        if (directory == null || directory.getParent() == null)
            throw new InvalidObjectException("Cannot remove null directory or root directory. ");

        directory.getParent().removeChild(directory);
        directory.setParent(null);
    }

    @Override
    public String print(Directory directory) {
        return DirectoryUtils.getFullPath(directory);
    }

    @Override
    public String list(Directory directory) {
        if (directory == null)
            return null;

        if (directory.getChildren() == null)
            return null;

        StringBuilder sb = new StringBuilder();

        for (Directory child : directory.getChildren()) {
            sb.append(child.getName()).append(Constants.LIST_SEPERATOR);
        }

        return sb.toString().trim();
    }
}
