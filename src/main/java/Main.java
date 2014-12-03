import model.Constants;
import model.Directory;
import model.InMemoryFileSystemStorage;
import service.DirectoryCommandExecutor;
import service.impl.DirectoryCommandExecutorImpl;
import util.DirectoryUtils;

/**
 * Main class to be run
 * <p/>
 * Created by Shaochen Huang on 12/2/14.
 */
public class Main {

    private static DirectoryCommandExecutor executor = new DirectoryCommandExecutorImpl();


    /**
     * Run application
     *
     * @param args, single argument with file path
     */
    public static void main(String[] args) {

        try{
            String fileName = "command-sample.txt";
            String content = DirectoryUtils.getContent(fileName);
            //String content = DirectoryUtils.getContent(args[0]);
            String[] commands = content.split("\\r?\\n"); //split new line and empty lines
            Directory workingDir = InMemoryFileSystemStorage.INSTANCE.getRootDirectory();//start from root directory
            for(String command:commands){
                Constants.Commands curCommand = DirectoryUtils.getCommand(command);
                String curParam = DirectoryUtils.getParam(command);
                switch (curCommand){
                    case cd:
                        workingDir = executor.navigate(workingDir, curParam);
                        break;
                    case mkdir:
                        Directory newDir = executor.makeDirectory(workingDir, curParam);
                        System.out.println(DirectoryUtils.getFullPath(newDir));
                        break;
                    case rmdir:
                        Directory childDir = workingDir.getChild(curParam);
                        executor.removeDirectory(childDir);
                        break;
                    case pwd:
                        System.out.println(DirectoryUtils.getFullPath(workingDir));
                        break;
                    case ls:
                        System.out.println(executor.list(workingDir));
                        break;
                }

            }

        }catch (Exception e){
            System.err.println("Exception happened while executing command. "+e.getMessage());
        }



    }






}
