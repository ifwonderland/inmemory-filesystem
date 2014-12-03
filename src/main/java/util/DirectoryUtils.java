package util;

import model.Constants;
import model.Directory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

/**
 * Util methods.
 * <p/>
 * Created by Shaochen Huang on 12/2/14.
 */
public class DirectoryUtils {

    /**
     * Get full path to the directory given
     *
     * @param directory
     * @return
     */
    public static String getFullPath(Directory directory) {
        if (directory == null)
            return null;

        StringBuilder sb = new StringBuilder();

        Stack<String> dirNames = new Stack<>();

        Directory curDir = directory;

        while (curDir != null) {
            dirNames.push(curDir.getName());
            curDir = curDir.getParent();
        }

        while (!dirNames.isEmpty()) {
            String curDirName = dirNames.pop();
            if (sb.toString().endsWith(Constants.DIR_SEPERATOR)||curDirName.equals(Constants.ROOT_DIR))
                sb.append(curDirName);
            else
                sb.append(Constants.DIR_SEPERATOR).append(curDirName);
        }

        return sb.toString();
    }


    /**
     * Read string from file
     *
     * @param fileName
     * @return
     */
    public static String getContent(String fileName) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(fileName));
        return new String(encoded);
    }


    public static Constants.Commands getCommand(String command) {
        for (Constants.Commands cur : Constants.Commands.values()) {
            if (command.contains(cur.name()))
                return cur;
        }

        return null;
    }

    public static String getParam(String command) {
        String[] comArg = command.split(Constants.LIST_SEPERATOR);
        if (comArg.length == 2)
            return comArg[1];
        else
            return null;
    }


}
