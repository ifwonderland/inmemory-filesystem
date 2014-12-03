package model;

/**
 * some invariants constants used across app
 *
 * Created by Shaochen Huang on 12/2/14.
 */
public class Constants {

    public final static String CUR_DIR = ".";

    public final static String ROOT_DIR = "/";

    public final static String DIR_SEPERATOR = "/";

    public final static String LIST_SEPERATOR = " ";

    public final static String PARENT_DIR  = "..";


    public enum Commands{
        cd, mkdir, rmdir, pwd, ls
    }


}
