package model;

/**
 * A singleton instance providing access to underlying memory for file system.
 * <p/>
 * Created by Shaochen Huang on 12/2/14.
 */
public class InMemoryFileSystemStorage {

    //thread safe instantiation static time
    public static InMemoryFileSystemStorage INSTANCE = new InMemoryFileSystemStorage();

    //pointing to root directory "/", not thread safe
    private Directory root;


    /**
     * This is to avoid instantiation from outside
     */
    private InMemoryFileSystemStorage() {
        this.root = new Directory(Constants.ROOT_DIR); //root has no children
    }

    /**
     * Publish the reference to root is neccessary here, as this could be modified by
     * @return
     */
    public Directory getRootDirectory() {
        return root;
    }
}
