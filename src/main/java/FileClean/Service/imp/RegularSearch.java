package FileClean.Service.imp;

import FileClean.Service.api.SearchFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RegularSearch implements SearchFile {
    public List<String> getFiles(String searchParam, final String rootPath) {
        List<String> lst = new ArrayList<>();
        addFile(lst, searchParam, rootPath);
        return lst;
    }

    private void addFile(List<String> files, String regular, String rootPath) {

        if (!rootPath.endsWith(File.separator)) {
            rootPath += File.separator;
        }
        File file = new File(rootPath);

        File[] files1 = file.listFiles();
        if (null != files1) {
            for (File f : files1) {
                try {
                    if (Pattern.matches(regular, f.getName())) {
                        files.add(f.getAbsolutePath());
                    } else if (f.isDirectory()) {
                        addFile(files, regular, f.getAbsolutePath());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
