package FileClean.Service;

import FileClean.Service.api.NotifyLog;
import FileClean.Service.api.SearchFile;
import FileClean.Service.imp.ExtentionSearch;
import FileClean.Service.imp.FileNameSearch;
import FileClean.Service.imp.RegularSearch;
import FileClean.entry.CleanEntry;
import FileClean.entry.SearchType;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CleanService {

    private static CleanService instance;
    Map<Integer, SearchFile> searchFileMap = new HashMap<Integer, SearchFile>();
    private NotifyLog notifyLog;

    public static CleanService getInstance() {
        if (instance == null) {
            instance = new CleanService();
        }
        return instance;
    }

    public void setNotifyLog(NotifyLog notifyLog) {
        this.notifyLog = notifyLog;
    }

    public void StartClean(List<CleanEntry> search, String rootPath) {
        if (null == rootPath || "".equals(rootPath)) {
            showLog("Root path is null");
            return;
        }
        if (null == search || search.size() == 0) {
            showLog("search type is null");
            return;
        }
        List<String> all = new ArrayList<String>();
        for (CleanEntry c : search) {
            List<String> files = null;
            SearchFile searchFile = searchFileMap.get(c.getSearchType().getIndex());
            if (null == searchFile) {
                continue;
            }
            files = searchFile.getFiles(c.getSearcheValue(), rootPath);
            if (files != null) {
                all.addAll(files);
            }
        }


        for (String s : all) {
            delete(s);
        }
        showLog("delete over!");
    }


    public void init() {
        searchFileMap.put(SearchType.EXTENTION.getIndex(), new ExtentionSearch());
        searchFileMap.put(SearchType.NAME.getIndex(), new FileNameSearch());
        searchFileMap.put(SearchType.REGULAR.getIndex(), new RegularSearch());
    }


    private void showLog(String logInf) {
        if (notifyLog != null) {
            notifyLog.showLog(logInf);
        }
    }

    private void delete(String rootPath) {
        File file = new File(rootPath);
        if (false == file.exists()) {
            return;
        }
        if (file.isFile()) {
            deleteFile(file);
        } else {
            File[] files = file.listFiles();
            if (files.length == 0) {
                deleteFile(file);
            } else {
                for (File f : files) {
                    if (f.isFile()) {
                        deleteFile(f);
                    } else {
                        delete(f.getAbsolutePath());
                    }
                }
                deleteFile(file);
            }

        }
    }

    private void deleteFile(File file) {
        if (file.isFile() == false) {
            boolean delete = file.delete();
            showLog("Delete file is " + delete + " [" + file.getAbsolutePath() + "] ");
        } else {
            boolean delete = file.delete();
            showLog("Delete Directory is " + delete + " [" + file.getAbsolutePath() + "] ");
        }
    }

}
