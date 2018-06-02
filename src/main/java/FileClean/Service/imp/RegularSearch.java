package FileClean.Service.imp;

import FileClean.Service.api.SearchFile;

import java.util.ArrayList;
import java.util.List;

public class RegularSearch implements SearchFile {
    public List<String> getFiles(String searchParam, final String rootPath) {
        return new ArrayList<String>();
    }
}
