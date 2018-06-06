package FileClean.Service.imp;

import FileClean.Service.api.SearchFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileNameSearch implements SearchFile {
    public List<String> getFiles(String searchParam, final String rootPath) {
        List<String> files = new ArrayList<String>();
        addFile(files, searchParam, rootPath);
        return files;
    }

    private void addFile(List<String> files, String extention, String rootPath) {

        if (rootPath.endsWith(File.separator) == false) {
            rootPath += File.separator;
        }
        File file = new File(rootPath);

        File[] files1 = file.listFiles();
        if (null != files1) {
            for (File f : files1) {
                if (f.getName().equals(extention)) {
                    files.add(f.getAbsolutePath());
                } else if (f.isDirectory()) {
                    addFile(files, extention, f.getAbsolutePath());
                }
            }
        }
    }
}
