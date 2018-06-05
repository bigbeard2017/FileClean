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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CleanService {

    private static CleanService instance;
    Map<Integer, SearchFile> searchFileMap = new HashMap<Integer, SearchFile>();
    private NotifyLog notifyLog;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static CleanService getInstance() {
        if (instance == null) {
            instance = new CleanService();
        }
        return instance;
    }

    public CleanService() {
        init();
    }

    public void setNotifyLog(NotifyLog notifyLog) {
        this.notifyLog = notifyLog;
    }

    public void startClean(List<CleanEntry> search, String rootPath) {
        executorService.submit(() -> {
            List<String> all = findFilesOrDirector(search, rootPath);
            if (all == null) {
                return;
            }
            showLog("==============start to delete files or directories================");
            for (String s : all) {
                delete(s);
            }
            showLog("==============================delete over==========================");
        });
    }


    public void startFind(List<CleanEntry> search, String rootPath) {
        executorService.submit(() -> {
            List<String> all = findFilesOrDirector(search, rootPath);
            if (all == null) {
                return;
            }
            for (String s : all) {
                showLog(s);
            }
        });

    }

    private List<String> findFilesOrDirector(List<CleanEntry> search, String rootPath) {
        if (null == rootPath || "".equals(rootPath)) {
            showLog("Root path is null");
            return null;
        }
        if (null == search || search.size() == 0) {
            showLog("search type is null");
            return null;
        }
        List<String> all = new ArrayList<String>();
        showLog("==============start to find files or directories================");
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
        showLog("==============end to  find files or directories================");
        return all;
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
