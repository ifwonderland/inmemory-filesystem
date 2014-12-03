package model;

import java.util.*;

/**
 * Represents a directory in file system. A directory is similar to a tree node with parent node and list of children nodes.
 * <p/>
 * Created by Shaochen Huang on 12/2/14.
 */
public class Directory implements Comparable<Directory> {


    //parent directiory
    private Directory parent;

    //hashed name and children directory map
    private HashMap<String, Directory> children;

    //name of this directory
    private String name;


    /**
     * Default directory ctor
     */
    public Directory(String name) {
        this.parent = null;
        this.children = null;
        this.name = name;
    }

    /**
     * Directory with no children directories
     *
     * @param name
     * @param parent
     */
    public Directory(String name, Directory parent) {
        this.parent = parent;
        this.name = name;
    }

    public Directory getParent() {
        return parent;
    }


    /**
     * add new child directory
     *
     * @param child
     * @throws java.lang.IllegalArgumentException if child with same name already exists
     */
    public void addChild(Directory child) throws IllegalArgumentException {
        if (children == null)
            children = new HashMap<>();

        if (children.get(child.name) != null)
            throw new IllegalArgumentException("Child dir with same name already exists, name: " + child.name);

        children.put(child.name, child);
    }


    /**
     * remove child directory if it extis.
     *
     * @param child
     * @throws IllegalArgumentException if child dir does not exists
     */
    public void removeChild(Directory child) throws IllegalArgumentException {
        if (child == null)
            throw new IllegalArgumentException("Cannot remove a null dir");

        if (children.get(child.name) == null)
            throw new IllegalArgumentException("Child directory does not exists. name: " + child);

        children.remove(child.name);
    }


    public List<Directory> getChildren() {
        if (children == null)
            return null;

        return new ArrayList<Directory>(children.values());
    }

    /**
     * Get children folder by name
     *
     * @param name
     * @return
     * @throws java.lang.IllegalArgumentException if child cannot be found
     */
    public Directory getChild(String name) throws IllegalArgumentException {
        if (children == null || children.isEmpty())
            return null;

        Directory child = children.get(name);

        if (child == null)
            throw new IllegalArgumentException("child with name: " + name + ", does not exist. ");

        return child;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }


    public String getName() {
        return name;
    }


    @Override
    public int compareTo(Directory o) {
        return this.name.compareTo(o.name);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Directory)) return false;

        Directory directory = (Directory) o;

        if (!children.equals(directory.children)) return false;
        if (!name.equals(directory.name)) return false;
        if (!parent.equals(directory.parent)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parent.hashCode();
        result = 31 * result + children.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }


    @Override
    public String toString() {
        return name;
    }

}
