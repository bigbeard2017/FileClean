package FileClean.Service.api;

import java.util.List;

public interface SearchFile {
    List<String> getFiles(String searchParam, String rootPath);
}
